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
public class User extends AuditModel {
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

    @OneToMany(mappedBy = "user")
    Set<BusinessUser> roles;
}
