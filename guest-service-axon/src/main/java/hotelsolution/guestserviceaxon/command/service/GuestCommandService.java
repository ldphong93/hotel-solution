package hotelsolution.guestserviceaxon.command.service;

import hotelsolution.guestserviceaxon.command.command.CreateGuestCommand;
import hotelsolution.guestserviceaxon.command.command.DeleteGuestCommand;
import hotelsolution.guestserviceaxon.command.command.EditGuestCommand;
import hotelsolution.guestserviceaxon.command.dto.GuestCreateRequest;
import hotelsolution.guestserviceaxon.command.dto.GuestEditRequest;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

@Service
@Slf4j(topic = "[GuestCommandService]")
public class GuestCommandService {

  private final CommandGateway commandGateway;

  public GuestCommandService(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  public CompletableFuture<String> createGuest(GuestCreateRequest request) {
    log.info("CreateGuest command received.");
    return commandGateway
        .send(new CreateGuestCommand(UUID.randomUUID().toString(), request.getUserName(),
            request.getPassword(), request.getName(), request.getPhoneNumber()));
  }

  public CompletableFuture<String> editGuest(GuestEditRequest request) {
    return commandGateway
        .send(new EditGuestCommand(request.getId(), request.getName(), request.getPhoneNumber()));
  }

  public CompletableFuture<String> deleteGuest(String id) {
    return commandGateway
        .send(new DeleteGuestCommand(id));
  }
}
