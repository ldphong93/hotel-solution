package hotelsolution.guestserviceaxon.common.event;

import lombok.Getter;

@Getter
public class GuestDeletedEvent extends BaseEvent<String> {
  public GuestDeletedEvent(String id) {
    super(id);
  }
}
