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
    @NotBlank
    private String name;

    @NotBlank
    private String email;

    private String phoneNumber;

    @NotBlank
    private String password;
}
