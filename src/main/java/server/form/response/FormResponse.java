package server.form.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import server.form.model.FormProperty;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormResponse {
    private String id;
    private String name;
    private String gameId; // Added gameId field
    private FormSchema formSchema;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class FormSchema {
        private Map<String, FormProperty> properties;
    }
}
