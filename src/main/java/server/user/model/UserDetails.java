package server.user.model;

import jakarta.persistence.*;
import lombok.*;
import server.AuditModel;
import server.businessRole.model.BusinessUser;

import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class UserDetails extends AuditModel {
    @Id
    @Column(name = "id")
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String phoneNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<BusinessUser> roles;

    public UserDetails(String id, String name, String email, String phoneNumber, UserStatus status, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.password = password;
    }
}
