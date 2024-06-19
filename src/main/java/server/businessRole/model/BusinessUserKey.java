package server.businessRole.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BusinessUserKey implements Serializable {

    @Column(name = "business_id")
    private String businessId;

    @Column(name = "user_id")
    private String userId;
}