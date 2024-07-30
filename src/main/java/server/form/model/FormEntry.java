package server.form.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormEntry {

    @Id
    private String id;

    @Column(nullable = false)
    private String formId;

    @Column(length = 50000)
    private String entry;
}
