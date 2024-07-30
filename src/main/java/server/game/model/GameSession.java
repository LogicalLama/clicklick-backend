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
public class GameSession {

    @Id
    private String id;

    @Column(nullable = false)
    private String gameId;

    @Column(nullable = false, length = 20)
    private String gameToken;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GameSessionStatus status;

    @Column(nullable = true)
    private String formEntryId; // Nullable field for form entry ID
}
