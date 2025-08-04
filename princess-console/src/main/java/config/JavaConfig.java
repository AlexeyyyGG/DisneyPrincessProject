package config;

import java.sql.Connection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import repository.DatabaseConnection;

@Configuration
@ComponentScan(basePackages = {"repository", "commands", "conveyor", "readers"})
public class JavaConfig {
    @Bean
    public Connection connection() {
        return DatabaseConnection.getConnection();
    }
}