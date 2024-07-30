package server.game.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Game {

    @Id
    private String id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String gameName;

    @Column(length = 50000)
    private String rewardConfig; // JSON string representing RewardConfig
}
