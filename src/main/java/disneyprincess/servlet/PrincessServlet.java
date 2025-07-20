package disneyprincess.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import disneyprincess.model.Princess;
import disneyprincess.repository.DatabaseConnection;
import disneyprincess.repository.PrincessRepository;
import disneyprincess.repository.PrincessRepositoryDB;
import disneyprincess.utils.PrincessValidator;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

public class PrincessServlet extends HttpServlet {
    private PrincessRepository repository;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";
    private static final String ID_PARAM = "id";

    @Override
    public void init() {
        Connection connection = DatabaseConnection.getConnection();
        this.repository = new PrincessRepositoryDB(connection);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType(CONTENT_TYPE);
        Princess princess = objectMapper.readValue(request.getInputStream(), Princess.class);
        PrincessValidator.validateId(princess.getId());
        PrincessValidator.validateName(princess.getName());
        PrincessValidator.validateAge(princess.getAge());
        repository.add(princess);
        response.getWriter().println(objectMapper.writeValueAsString(princess));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter pw = response.getWriter();
        String idParam = request.getParameter(ID_PARAM);
        if (idParam != null) {
            int id;
            try {
                id = Integer.parseInt(idParam);
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
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
        Princess princess = objectMapper.readValue(request.getInputStream(), Princess.class);
        int id = princess.getId();
        PrincessValidator.validateId(id);
        boolean existingPrincess = repository.exist(id);
        if (existingPrincess) {
            PrincessValidator.validateName(princess.getName());
            PrincessValidator.validateAge(princess.getAge());
            repository.update(princess);
            response.getWriter().println(objectMapper.writeValueAsString(princess));
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        String idParam = request.getParameter(ID_PARAM);
        if (idParam == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        try {
            int id = Integer.parseInt(idParam);
            if (repository.exist(id)) {
                repository.delete(id);
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}