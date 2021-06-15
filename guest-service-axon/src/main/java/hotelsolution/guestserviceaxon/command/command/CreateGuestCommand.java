package hotelsolution.guestserviceaxon.command.command;

import lombok.Getter;

@Getter
//@Builder
public class CreateGuestCommand extends BaseCommand<String> {

  private final String userName;
  private final String password;
  private final String name;
  private final String phoneNumber;

  public CreateGuestCommand(String id, String userName, String password, String name,
      String phoneNumber) {
    super(id);
    this.userName = userName;
    this.password = password;
    this.name = name;
    this.phoneNumber = phoneNumber;
  }
}
