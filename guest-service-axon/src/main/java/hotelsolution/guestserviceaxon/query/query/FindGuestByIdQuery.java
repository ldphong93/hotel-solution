package hotelsolution.guestserviceaxon.query.query;

import lombok.Data;

@Data
public class FindGuestByIdQuery {

  private String id;

  public FindGuestByIdQuery(String id) {
    this.id = id;
  }
}
