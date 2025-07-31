package servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Princess;
import repository.DatabaseConnection;
import repository.PrincessRepository;
import repository.PrincessRepositoryDB;
import service.PrincessService;
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
    private static final String MISSING_ID_MESSAGE = "Missing id parameter";

    @Override
    public void init() {
        Connection connection = DatabaseConnection.getConnection();
        PrincessRepository repository = new PrincessRepositoryDB(connection);
        this.princessService = new PrincessService(repository);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter pw = response.getWriter();
        try {
            Princess princess = objectMapper.readValue(request.getInputStream(), Princess.class);
            princessService.addPrincess(princess);
            pw.println(objectMapper.writeValueAsString(princess));
        } catch (JsonProcessingException | IllegalArgumentException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            pw.println(e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter pw = response.getWriter();
        String idParam = request.getParameter(ID_PARAM);
        if (idParam != null) {
            try {
                int id = parseIdParam(idParam);
                Princess princess = princessService.getPrincess(id);
                pw.println(objectMapper.writeValueAsString(princess));
            } catch (IllegalArgumentException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                pw.println(e.getMessage());
            }
        } else {
            List<Princess> princesses = princessService.getAllPrincess();
            pw.println(objectMapper.writeValueAsString(princesses));
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter pw = response.getWriter();
        try {
            Princess princess = objectMapper.readValue(request.getInputStream(), Princess.class);
            princessService.updatePrincess(princess);
            pw.println(objectMapper.writeValueAsString(princess));
        } catch (JsonProcessingException | IllegalArgumentException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            pw.println(e.getMessage());
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idParam = request.getParameter(ID_PARAM);
        try {
            int id = parseIdParam(idParam);
            princessService.deletePrincess(id);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println(e.getMessage());
        }
    }

    private int parseIdParam(String idParam) {
        if (idParam == null) {
            throw new IllegalArgumentException(MISSING_ID_MESSAGE);
        }
        try {
            return Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_ID_MESSAGE);
        }
    }
}
