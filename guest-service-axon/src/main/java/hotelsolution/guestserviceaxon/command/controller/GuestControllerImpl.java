package hotelsolution.guestserviceaxon.command.controller;

import hotelsolution.guestserviceaxon.command.dto.GuestCreateRequest;
import hotelsolution.guestserviceaxon.command.dto.GuestEditRequest;
import hotelsolution.guestserviceaxon.command.service.GuestCommandService;
import java.util.concurrent.CompletableFuture;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/guestAxon")
@Slf4j(topic = "[GuestControllerImpl]")
@RestController
public class GuestControllerImpl {

  private GuestCommandService guestCommandService;

  @Autowired
  public GuestControllerImpl(GuestCommandService guestCommandService) {
    this.guestCommandService = guestCommandService;
  }

  @PostMapping
  public ResponseEntity<String> createGuest(@Valid @RequestBody GuestCreateRequest request) {
    try {
      CompletableFuture<String> response = guestCommandService.createGuest(request);

      return new ResponseEntity<>(response.get(), HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>("Error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping
  public ResponseEntity<String> editGuest(@Valid @RequestBody GuestEditRequest request) {
    try {
      CompletableFuture<String> response = guestCommandService.editGuest(request);

      return new ResponseEntity<>(response.get(), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping
  public ResponseEntity<String> deleteGuest(@Valid String id) {
    try {
      CompletableFuture<String> response = guestCommandService.deleteGuest(id);

      return new ResponseEntity<>(response.get(), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
