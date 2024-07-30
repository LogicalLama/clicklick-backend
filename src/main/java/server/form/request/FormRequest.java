package server.form.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormRequest {
    private String name;
    private String gameId; // Added gameId field
    private Map<String, FormPropertyDto> properties;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class FormPropertyDto {
        private String type;
        private String description;
        private String pattern;
        private String patternError;
        private boolean isRequired;
    }
}
