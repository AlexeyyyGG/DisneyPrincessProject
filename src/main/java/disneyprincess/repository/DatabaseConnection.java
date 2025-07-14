package disneyprincess.repository;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static final String PROPERTIES_PATH = "/db/db.properties";
    private static final String DB_URL = "db.url";
    private static final String DB_USER = "db.user";
    private static final String DB_PASSWORD = "db.password";
    private static final Properties properties = new Properties();
    private static final String FAILED_TO_LOAD_MESSAGE = "Failed to load database configuration";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
        try (InputStream input = DatabaseConnection.class.getResourceAsStream(PROPERTIES_PATH)) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException(FAILED_TO_LOAD_MESSAGE, e);
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = properties.getProperty(DB_URL);
        String user = properties.getProperty(DB_USER);
        String password = properties.getProperty(DB_PASSWORD);
        return DriverManager.getConnection(url, user, password);
    }
}
