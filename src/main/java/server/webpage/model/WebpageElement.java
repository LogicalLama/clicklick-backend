package server.webpage.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(WebpageElementId.class)
public class WebpageElement {

    @Id
    @Column(nullable = false)
    private String webpageId;

    @Id
    @Column(nullable = false)
    private String elementId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ElementType type;
}

