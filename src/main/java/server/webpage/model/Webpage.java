package server.webpage.model;

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
public class Webpage {

    @Id
    private String id;

    @Column(nullable = false)
    private String heading;

    @Column(nullable = false)
    private String subheading;

    @Column(nullable = true, length = 5000000)
    private String displayImage;

    @Column(nullable = false, unique = true)
    private String campaignId;
}
