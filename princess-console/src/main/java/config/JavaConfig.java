package config;

import commands.CommandDispatcher;
import commands.CommandRegistry;
import conveyor.ConveyorState;
import java.sql.Connection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import readers.ConsoleReader;
import repository.DatabaseConnection;
import repository.PrincessRepository;
import repository.PrincessRepositoryDB;

@Configuration
public class JavaConfig {
    @Bean
    public Connection connection() {
        return DatabaseConnection.getConnection();
    }

    @Bean
    public PrincessRepository repository(Connection connection) {
        return new PrincessRepositoryDB(connection);
    }

    @Bean
    public ConsoleReader reader() {
        return new ConsoleReader();
    }

    @Bean
    public ConveyorState conveyorState() {
        return new ConveyorState();
    }

    @Bean
    public CommandRegistry commandRegistry(PrincessRepository repository,
            ConveyorState conveyorState) {
        return new CommandRegistry(repository, conveyorState);
    }

    @Bean
    public CommandDispatcher commandDispatcher(CommandRegistry commandRegistry) {
        return new CommandDispatcher(commandRegistry);
    }
}