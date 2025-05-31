package disneyprincess.utils;

import disneyprincess.commands.AddCommand;
import disneyprincess.commands.DeleteCommand;
import disneyprincess.commands.GetCommand;
import disneyprincess.commands.ListCommand;
import disneyprincess.commands.UpdateCommand;
import disneyprincess.controller.Controller;
import disneyprincess.model.EyeColor;
import disneyprincess.model.HairColor;
import disneyprincess.model.Princess;
import disneyprincess.repository.PrincessRepository;

public class CommandDispatcher {
  public static void dispatch(String command, String[] args, PrincessRepository repository,
      Controller controller) {

    switch (command) {
      case "add":
        int id = Integer.parseInt(args[0]);
        String name = args[1];
        int age = Integer.parseInt(args[2]);
        HairColor hairColor = HairColor.fromString(args[3]);
        EyeColor eyeColor = EyeColor.fromString(args[4]);
        AddCommand addCommand = new AddCommand(repository,
            new Princess(id, name, age, hairColor, eyeColor));
        controller.setCommand(addCommand);
        controller.executeCommand();
        break;
      case "update":
        int updateId = Integer.parseInt(args[0]);
        String updateName = args[1];
        int updateAge = Integer.parseInt(args[2]);
        HairColor updateHairColor = HairColor.fromString(args[3]);
        EyeColor updateEyeColor = EyeColor.fromString(args[4]);
        UpdateCommand updateCommand = new UpdateCommand(updateId, updateName, updateAge,
            updateHairColor, updateEyeColor, repository);
        controller.setCommand(updateCommand);
        controller.executeCommand();
        break;
      case "delete":
        int deleteId = Integer.parseInt(args[0]);
        DeleteCommand deleteCommand = new DeleteCommand(repository, deleteId);
        controller.setCommand(deleteCommand);
        controller.executeCommand();
        break;
      case "get":
        int getID = Integer.parseInt(args[0]);
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
