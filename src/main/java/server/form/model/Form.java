package server.form.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Form {
    @Id
    private String id;
    private String name;
    private FormSchema formSchema;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class FormSchema {
        private Map<String, Property> properties;
    }
}