package hotelsolution.guestserviceaxon.command.command;

import lombok.Getter;

@Getter
//@Builder
public class DeleteGuestCommand extends BaseCommand<String> {

  public DeleteGuestCommand(String id) {
    super(id);
  }
}