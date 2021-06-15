package hotelsolution.guestserviceaxon.command.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GuestEditRequest {


  @NotBlank(message = "Guest id is required.")
  private String id;

  @NotBlank(message = "Name is required.")
  private String name;

  @Size(min = 8, max = 12)
  @PositiveOrZero(message = "Only allow digits")
  private String phoneNumber;

}