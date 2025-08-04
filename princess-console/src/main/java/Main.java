import config.JavaConfig;
import conveyor.ConveyorCommand;
import conveyor.ConveyorState;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repository.PrincessRepository;
import commands.CommandDispatcher;
import readers.ConsoleReader;
import model.Princess;
import readers.PrincessFileReader;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                JavaConfig.class);
        PrincessRepository repository = context.getBean(PrincessRepository.class);
        ConsoleReader reader = context.getBean(ConsoleReader.class);
        ConveyorState conveyorState = context.getBean(ConveyorState.class);
        CommandDispatcher dispatcher = context.getBean(CommandDispatcher.class);
        String filename = "disneyPrincesses";
        List<Princess> princessesList = PrincessFileReader.readPrincessesFromFile(filename);
        repository.addAll(princessesList);
        ConveyorCommand conveyor = new ConveyorCommand(reader, dispatcher, conveyorState);
        conveyor.startConveyor();
    }
}