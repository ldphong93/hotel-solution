package hotelsolution.guestserviceaxon.query.controller;

import hotelsolution.guestserviceaxon.query.entity.Guest;
import hotelsolution.guestserviceaxon.query.query.FindGuestByIdQuery;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/guestAxon")
public class ManageGuestController {

  private final QueryGateway queryGateway;

  public ManageGuestController(QueryGateway queryGateway) {
    this.queryGateway = queryGateway;
  }

  @GetMapping
  public ResponseEntity<Guest> getAccount(@RequestParam String id) {

    Guest guest = queryGateway.query(new FindGuestByIdQuery(id), Guest.class).join();

    if (guest == null) {
      return new ResponseEntity<>(Guest.builder().name("Guest Profile not found").build(),
          HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(guest, HttpStatus.OK);
  }

}
