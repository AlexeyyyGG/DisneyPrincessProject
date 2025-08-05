import config.Configuration;
import conveyor.ConveyorCommand;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repository.PrincessRepository;
import model.Princess;
import readers.PrincessFileReader;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                Configuration.class);
        PrincessRepository repository = context.getBean(PrincessRepository.class);
        String filename = "disneyPrincesses";
        List<Princess> princessesList = PrincessFileReader.readPrincessesFromFile(filename);
        repository.addAll(princessesList);
        ConveyorCommand conveyor = context.getBean(ConveyorCommand.class);
        conveyor.startConveyor();
    }
}