package server.campaign.model;

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
public class Campaign extends AuditModel {

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CampaignType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CampaignStatus status;

    @Column(nullable = false)
    private String brandId;

    @Column(nullable = false)
    private String businessId;
}
