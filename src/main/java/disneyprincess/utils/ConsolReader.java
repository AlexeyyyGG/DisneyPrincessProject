package disneyprincess.utils;

import java.util.Scanner;

public class ConsolReader {
  private String[] args;
  private String command;
  private final Scanner scanner;

  public ConsolReader() {
    this.scanner = new Scanner(System.in);
  }

  public void read() {
    System.out.println("Enter the command: add/update/delete/get/list/exit");
    String input = scanner.nextLine();
    String[] parts = input.split("\\s+");
    this.command = parts[0].toLowerCase();

    if (parts.length > 1) {
      this.args = new String[parts.length - 1];
      System.arraycopy(parts, 1, this.args, 0, parts.length - 1);
    } else {
      this.args = new String[0];
    }
  }

  public String[] getArgs() {
    return args;
  }

  public String getCommand() {
    return command;
  }
}
