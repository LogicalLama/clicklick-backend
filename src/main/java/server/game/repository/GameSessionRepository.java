package server.game.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.game.model.GameSession;

import java.util.Optional;

@Repository
public interface GameSessionRepository extends JpaRepository<GameSession, String> {
    Optional<GameSession> findByGameToken(String gameToken);
}
