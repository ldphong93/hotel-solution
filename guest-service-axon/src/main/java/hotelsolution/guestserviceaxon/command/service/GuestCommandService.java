package hotelsolution.guestserviceaxon.command.service;

import hotelsolution.guestserviceaxon.command.command.CreateGuestCommand;
import hotelsolution.guestserviceaxon.command.dto.GuestCreateRequest;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import org.axonframework.commandhandling.gateway.CommandGateway;

public class GuestCommandService {

  private final CommandGateway commandGateway;

  public GuestCommandService(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  public CompletableFuture<String> createGuest(GuestCreateRequest request) {
    return commandGateway
        .send(new CreateGuestCommand(UUID.randomUUID().toString(), request.getUserName(),
            request.getPassword(), request.getName(), request.getPhoneNumber()));
  }
}
