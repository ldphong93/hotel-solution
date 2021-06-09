package hotelsolution.guestserviceaxon.common.event;

import lombok.Getter;

@Getter
public class GuestCreatedEvent extends BaseEvent<String> {

  private final String userName;
  private final String password;
  private final String name;
  private final String phoneNumber;

  public GuestCreatedEvent(String id, String userName, String password, String name,
      String phoneNumber) {
    super(id);
    this.userName = userName;
    this.password = password;
    this.name = name;
    this.phoneNumber = phoneNumber;
  }

}
