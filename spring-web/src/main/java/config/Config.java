package config;

import java.sql.Connection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import repository.DatabaseConnection;
import repository.PrincessRepository;
import repository.PrincessRepositoryDB;
import service.PrincessService;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"service", "controller", "handler"})
public class Config implements WebMvcConfigurer {
    @Bean
    public Connection getConnection() {
        return DatabaseConnection.getConnection();
    }

    @Bean
    public PrincessRepository repository(Connection connection) {
        return new PrincessRepositoryDB(connection);
    }

    @Bean
    public PrincessService service(PrincessRepository repository) {
        return new PrincessService(repository);
    }
}