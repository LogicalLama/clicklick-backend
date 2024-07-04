package server.form.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Property {
    private String type;
    private String description;
    private String pattern;
    private String patternError;
    private boolean isRequired;
}