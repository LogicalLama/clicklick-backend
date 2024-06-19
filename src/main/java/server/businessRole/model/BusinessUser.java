package server.businessRole.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import server.business.model.Business;
import server.user.model.UserDetails;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BusinessUser {
    @EmbeddedId
    private BusinessUserKey id;

    @ManyToOne
    @MapsId("businessId")
    @JoinColumn(name = "business_id")
    private Business business;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private UserDetails user;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private BusinessRole role;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private BusinessUserStatus status;
}