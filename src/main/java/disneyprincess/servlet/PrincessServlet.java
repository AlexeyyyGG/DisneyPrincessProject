package disneyprincess.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import disneyprincess.model.Princess;
import disneyprincess.repository.DatabaseConnection;
import disneyprincess.repository.PrincessRepository;
import disneyprincess.repository.PrincessRepositoryDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

public class PrincessServlet extends HttpServlet {
    private PrincessRepository repository;
    private static final String FAILED_TO_INIT_MESSAGE = "Failed to initialize repository";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DatabaseConnection.getConnection();
            this.repository = new PrincessRepositoryDB(connection);
        } catch (Exception e) {
            throw new ServletException(FAILED_TO_INIT_MESSAGE, e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter pw = response.getWriter()) {
            List<Princess> princesses = repository.list();
            String json = objectMapper.writeValueAsString(princesses);
            pw.write(json);
        }
    }
}