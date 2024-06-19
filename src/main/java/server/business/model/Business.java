package server.business.model;

import jakarta.persistence.*;
import lombok.*;
import server.AuditModel;
import server.businessRole.model.BusinessUser;

import java.util.Set;

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

    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL)
    private Set<BusinessUser> roles;

    public Business(String businessID, String businessName, BusinessStatus status) {
        this.id = businessID;
        this.name = businessName;
        this.status = status;
    }

    public Business(String businessID, String businessName, BusinessStatus status, String address, String pincode,
                    String website, String base64logo) {
        this.id = businessID;
        this.name = businessName;
        this.status = status;
        this.address = address;
        this.pincode = pincode;
        this.website = website;
        this.base64logo = base64logo;
    }
}
