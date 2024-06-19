package server.user.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserRequest {
    @NotBlank
    private String internalUserID;
    private String name;
    private String email;
    private String phoneNumber;
}
