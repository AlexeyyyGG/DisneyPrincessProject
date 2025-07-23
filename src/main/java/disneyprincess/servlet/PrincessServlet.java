package disneyprincess.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import disneyprincess.model.Princess;
import disneyprincess.repository.DatabaseConnection;
import disneyprincess.repository.PrincessRepository;
import disneyprincess.repository.PrincessRepositoryDB;
import disneyprincess.service.PrincessService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

public class PrincessServlet extends HttpServlet {
    private PrincessService princessService;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";
    private static final String ID_PARAM = "id";
    private static final String INVALID_ID_MESSAGE = "Invalid id format";
    private static final String INVALID_JSON_MESSAGE = "Invalid JSON format";

    @Override
    public void init() {
        Connection connection = DatabaseConnection.getConnection();
        PrincessRepository repository = new PrincessRepositoryDB(connection);
        this.princessService = new PrincessService(repository);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType(CONTENT_TYPE);
        try {
            Princess princess = objectMapper.readValue(request.getInputStream(), Princess.class);
            princessService.addPrincess(princess);
            response.getWriter().println(objectMapper.writeValueAsString(princess));
        } catch (JsonProcessingException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println(INVALID_JSON_MESSAGE);
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println(e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter pw = response.getWriter();
        Integer id = getIdParam(request, false);
        try {
            if (id == null) {
                List<Princess> princesses = princessService.getAllPrincess();
                pw.println(objectMapper.writeValueAsString(princesses));
            } else {
                Princess princess = princessService.getPrincess(id);
                pw.println(objectMapper.writeValueAsString(princess));
            }
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println(e.getMessage());
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType(CONTENT_TYPE);
        try {
            Princess princess = objectMapper.readValue(request.getInputStream(), Princess.class);
            princessService.updatePrincess(princess);
            response.getWriter().println(objectMapper.writeValueAsString(princess));
        } catch (JsonProcessingException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println(INVALID_JSON_MESSAGE);
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println(e.getMessage());
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Integer id = getIdParam(request, true);
            if (id == null) {
                return;
            }
            princessService.deletePrincess(id);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println(e.getMessage());
        }
    }

    private Integer getIdParam(HttpServletRequest request, boolean isRequired) {
        String idParam = request.getParameter(ID_PARAM);
        if (idParam == null) {
            if (isRequired) {
                throw new IllegalArgumentException(INVALID_ID_MESSAGE);
            } else {
                return null;
            }
        }
        try {
            return Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_ID_MESSAGE);
        }
    }
}
