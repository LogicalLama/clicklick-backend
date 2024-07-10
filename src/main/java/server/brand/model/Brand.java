package server.brand.model;

import jakarta.persistence.*;
import lombok.*;
import server.AuditModel;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Brand extends AuditModel {

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private BrandStatus status;

    private String website;

    @Column(length = 500000)
    private String base64logo;

    @Column(nullable = false)
    private String businessId;
}
