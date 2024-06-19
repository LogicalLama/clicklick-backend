package server.business.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBusinessRequest {
    @NotBlank
    private String name;

    private String address;

    private String pincode;

    private String website;

    private String base64logo;
}
