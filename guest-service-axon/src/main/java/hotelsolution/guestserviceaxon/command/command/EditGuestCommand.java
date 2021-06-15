package hotelsolution.guestserviceaxon.command.command;

import lombok.Getter;

@Getter
//@Builder
public class EditGuestCommand extends BaseCommand<String> {

  private final String name;
  private final String phoneNumber;

  public EditGuestCommand(String id, String name, String phoneNumber) {
    super(id);
    this.name = name;
    this.phoneNumber = phoneNumber;
  }
}
