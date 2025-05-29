package disneyprincess.utils;

import disneyprincess.commands.AddCommand;
import disneyprincess.controller.Controller;
import disneyprincess.commands.DeleteCommand;
import disneyprincess.commands.GetCommand;
import disneyprincess.commands.ListCommand;
import disneyprincess.commands.UpdateCommand;
import disneyprincess.model.EyeColor;
import disneyprincess.model.HairColor;
import disneyprincess.model.Princess;
import disneyprincess.repository.PrincessRepository;
import java.util.Scanner;

public class ConsolReader {
  public static void start(PrincessRepository repository) {
    Scanner scanner = new Scanner(System.in);
    Controller controller = new Controller();

    while (true) {
      System.out.println(
          "Enter the command: add/update/delete/get/list/exit");
      String input = scanner.nextLine();
      String[] parts = input.split(" ");
      String action = parts[0];

      switch (action.toLowerCase()) {
        case "add":
          int id = Integer.parseInt(parts[1]);
          String name = parts[2];
          int age = Integer.parseInt(parts[3]);
          HairColor hairColor = HairColor.fromString(parts[4]);
          EyeColor eyeColor = EyeColor.fromString(parts[5]);
          AddCommand addCommand = new AddCommand(repository,
              new Princess(id, name, age, hairColor, eyeColor));
          controller.setCommand(addCommand);
          controller.executeCommand();
          break;
        case "update":
          int updateId = Integer.parseInt(parts[1]);
          String updateName = parts[2];
          int updateAge = Integer.parseInt(parts[3]);
          HairColor updateHairColor = HairColor.fromString(parts[4]);
          EyeColor updateEyeColor = EyeColor.fromString(parts[5]);
          UpdateCommand updateCommand = new UpdateCommand(updateId, updateName, updateAge,
              updateHairColor, updateEyeColor, repository);
          controller.setCommand(updateCommand);
          controller.executeCommand();
          break;
        case "delete":
          int deleteId = Integer.parseInt(parts[1]);
          DeleteCommand deleteCommand = new DeleteCommand(repository, deleteId);
          controller.setCommand(deleteCommand);
          controller.executeCommand();
          break;
        case "get":
          int getID = Integer.parseInt(parts[1]);
          GetCommand getCommand = new GetCommand(repository, getID);
          controller.setCommand(getCommand);
          controller.executeCommand();
          break;
        case "list":
          ListCommand listCommand = new ListCommand(repository);
          controller.setCommand(listCommand);
          controller.executeCommand();
          break;
        case "exit":
          return;
        default:
          System.out.println("Unknown command");
      }
    }
  }
}
