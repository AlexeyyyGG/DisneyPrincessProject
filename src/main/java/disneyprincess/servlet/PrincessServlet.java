package disneyprincess.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import disneyprincess.model.Princess;
import disneyprincess.repository.DatabaseConnection;
import disneyprincess.repository.PrincessRepository;
import disneyprincess.repository.PrincessRepositoryDB;
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

    @Override
    public void init() {
        Connection connection = DatabaseConnection.getConnection();
        this.repository = new PrincessRepositoryDB(connection);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType(CONTENT_TYPE);
        try (PrintWriter pw = response.getWriter()) {
            if (request.getParameter("id") != null) {
                int id = Integer.parseInt(request.getParameter("id"));
                Princess princess = repository.get(id);
                pw.println(objectMapper.writeValueAsString(princess));
            } else {
                List<Princess> princesses = repository.list();
                pw.println(objectMapper.writeValueAsString(princesses));
            }
        }
    }
}