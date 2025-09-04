package com.example.config;

import java.sql.Connection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import repository.DatabaseConnection;
import repository.PrincessRepository;
import repository.PrincessRepositoryDB;

@Configuration
@EnableWebMvc
@ComponentScan("com.example")
public class Config implements WebMvcConfigurer {

    @Bean
    public Connection getConnection() {
        return DatabaseConnection.getConnection();
    }

    @Bean
    public PrincessRepository repository(Connection connection) {
        return new PrincessRepositoryDB(connection);
    }
}
