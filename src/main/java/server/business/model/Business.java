package server.business.model;

import jakarta.persistence.*;
import lombok.*;
import server.AuditModel;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Business extends AuditModel {
    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BusinessStatus status;

    private String address;

    private String pincode;

    private String website;

    @Column(length = 500000)
    private String base64logo;

    public Business(String businessID, String businessName, BusinessStatus status) {
        this.id = businessID;
        this.name = businessName;
        this.status = status;
    }
}
