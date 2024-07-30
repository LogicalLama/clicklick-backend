package server.form.model;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormPropertyId implements Serializable {
    private String formId;
    private String key;
}
