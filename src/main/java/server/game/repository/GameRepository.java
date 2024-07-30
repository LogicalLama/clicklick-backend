package server.game.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.game.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, String> {
}
