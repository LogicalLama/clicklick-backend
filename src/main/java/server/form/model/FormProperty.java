package server.form.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.IdClass;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(FormPropertyId.class)
public class FormProperty {

    @Id
    @Column(nullable = false)
    private String formId;

    @Id
    @Column(nullable = false)
    private String key;

    @Column(nullable = false)
    private String type;

    private String description;
    private String pattern;
    private String patternError;
    private boolean isRequired;
}
