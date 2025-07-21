package disneyprincess.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import disneyprincess.model.Princess;
import disneyprincess.repository.DatabaseConnection;
import disneyprincess.repository.PrincessRepository;
import disneyprincess.repository.PrincessRepositoryDB;
import disneyprincess.utils.PrincessService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

public class PrincessServlet extends HttpServlet {
    private PrincessRepository repository;
    private PrincessService princessService;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";
    private static final String ID_PARAM = "id";
    private static final String INVALID_ID_MESSAGE = "Invalid id format";

    @Override
    public void init() {
        Connection connection = DatabaseConnection.getConnection();
        this.repository = new PrincessRepositoryDB(connection);
        this.princessService = new PrincessService(repository);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType(CONTENT_TYPE);
        try {
            Princess princess = objectMapper.readValue(request.getInputStream(), Princess.class);
            princessService.addPrincess(princess);
            response.getWriter().println(objectMapper.writeValueAsString(princess));
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println(e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter pw = response.getWriter();
        Integer id = getIdParam(request, response);
        if (id != null) {
            if (repository.exist(id)) {
                Princess princess = repository.get(id);
                pw.println(objectMapper.writeValueAsString(princess));
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            List<Princess> princesses = repository.list();
            pw.println(objectMapper.writeValueAsString(princesses));
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType(CONTENT_TYPE);
        try {
            Princess princess = objectMapper.readValue(request.getInputStream(), Princess.class);
            princessService.updatePrincess(princess);
            response.getWriter().println(objectMapper.writeValueAsString(princess));
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println(e.getMessage());
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = getIdParam(request, response);
        if (id == null) {
            return;
        }
        if (repository.exist(id)) {
            repository.delete(id);
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private Integer getIdParam(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idParam = request.getParameter(ID_PARAM);
        if (idParam == null) {
            return null;
        }
        try {
            return Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println(INVALID_ID_MESSAGE);
            return null;
        }
    }
}