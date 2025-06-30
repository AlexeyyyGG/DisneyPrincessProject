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

    public static Connection getConnection() throws SQLException, IOException {
        Properties properties = new Properties();
        try (InputStream input = DatabaseConnection.class.getResourceAsStream(PROPERTIES_PATH)) {
            if (input == null) {
                throw new IOException(FILE_NOT_FOUND_MESSAGE);
            }
            properties.load(input);
        }
        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");
        return DriverManager.getConnection(url, user, password);
    }
}
