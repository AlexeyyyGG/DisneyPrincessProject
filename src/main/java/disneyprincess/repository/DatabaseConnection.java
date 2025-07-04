package disneyprincess.repository;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static final String PROPERTIES_PATH = "/db/db.properties";
    private static final String FILE_NOT_FOUND_MESSAGE = "Properties file not found";
    private static String url;
    private static String user;
    private static String password;
    private static Properties properties = new Properties();
    private static final String FAILED_TO_LOAD_MESSAGE = "Failed to load database configuration";

    static {
        try (InputStream input = DatabaseConnection.class.getResourceAsStream(PROPERTIES_PATH)) {
            if (input == null) {
                throw new IOException(FILE_NOT_FOUND_MESSAGE);
            }
            properties.load(input);
            url = properties.getProperty("db.url");
            user = properties.getProperty("db.user");
            password = properties.getProperty("db.password");
        } catch (IOException e) {
            throw new RuntimeException(FAILED_TO_LOAD_MESSAGE, e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
