package server.form.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormSubmitRequest {
    @NotBlank
    private String formId;
    @NonNull
    private Map<String, String> response;
}
