package server.business.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBusinessRequest {
    @NotBlank
    private String id;

    private String name;

    private String address;

    private String pincode;

    private String website;

    private String base64logo;
}
