package config;

import java.sql.Connection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import repository.DatabaseConnection;
import repository.PrincessRepository;
import repository.PrincessRepositoryDB;

@org.springframework.context.annotation.Configuration
@ComponentScan(basePackages = {"repository", "commands", "conveyor", "readers"})
public class Config {
    @Bean
    public Connection connection() {
        return DatabaseConnection.getConnection();
    }

    @Bean
    public PrincessRepository repository(Connection connection) {
        return new PrincessRepositoryDB(connection);
    }
}