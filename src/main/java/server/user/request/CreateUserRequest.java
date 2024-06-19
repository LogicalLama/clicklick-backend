package server.user.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserRequest {
    private String internalUserID;
    @NotBlank
    private String name;
    private String email;
    private String phoneNumber;
    private String orgID;
}
