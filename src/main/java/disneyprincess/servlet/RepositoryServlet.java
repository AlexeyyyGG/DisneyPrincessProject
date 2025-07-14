package disneyprincess.servlet;

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

public class RepositoryServlet extends HttpServlet {
    private PrincessRepository repository;
    private static final String FAILED_TO_INIT_MESSAGE = "Failed to initialize repository";

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DatabaseConnection.getConnection();
            this.repository = new PrincessRepositoryDB(connection);
        } catch (Exception e) {
            throw new ServletException(FAILED_TO_INIT_MESSAGE, e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try(PrintWriter pw = response.getWriter()){
            List<Princess> princesses = repository.list();
            pw.println("<html>");
            pw.println("<head><title>Princess List</title></head>");
            pw.println("<body>");
            pw.println("<h1>Princess List</h1>");

            if (princesses.isEmpty()) {
                pw.println("<h1>No princesses found</h1>");
            } else {
                pw.println("<table border='1'>");
                pw.println("<tr>");
                pw.println("<th>Id</th>");
                pw.println("<th>Name</th>");
                pw.println("<th>Age</th>");
                pw.println("<th>Hair Color</th>");
                pw.println("<th>Eye Color</th>");
                pw.println("</tr>");
                for (Princess princess : princesses) {
                    pw.println("<tr>");
                    pw.println("<td>" + princess.getId() + "</td>");
                    pw.println("<td>" + princess.getName() + "</td>");
                    pw.println("<td>" + princess.getAge() + "</td>");
                    pw.println("<td>" + princess.getHairColor() + "</td>");
                    pw.println("<td>" + princess.getEyeColor() + "</td>");
                    pw.println("</tr>");
                }
                pw.println("</table>");
            }
            pw.println("</body>");
            pw.println("</html>");
        }
    }
}