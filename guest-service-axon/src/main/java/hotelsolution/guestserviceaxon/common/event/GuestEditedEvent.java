package hotelsolution.guestserviceaxon.common.event;

import lombok.Getter;

@Getter
public class GuestEditedEvent extends BaseEvent<String> {

  private final String name;
  private final String phoneNumber;

  public GuestEditedEvent(String id, String name, String phoneNumber) {
    super(id);
    this.name = name;
    this.phoneNumber = phoneNumber;
  }

}
