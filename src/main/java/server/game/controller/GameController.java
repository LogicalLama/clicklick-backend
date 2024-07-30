package server.game.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.game.model.Game;
import server.game.request.GamePlayRequest;
import server.game.response.GamePlayResponse;
import server.game.response.GameResponse;
import server.game.service.GameService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        return ResponseEntity.ok(gameService.getAllGames());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameResponse> getGameById(@PathVariable String id) {
        Optional<Game> gameOptional = gameService.getGameById(id);
        if (!gameOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Game game = gameOptional.get();
        GameResponse.RewardConfigResponse rewardConfig;
        try {
            rewardConfig = gameService.deserializeRewardConfigResponse(game.getRewardConfig());
            GameResponse gameResponse = GameResponse.builder()
                    .type(game.getType())
                    .gameName(game.getGameName())
                    .rewardConfig(rewardConfig)
                    .build();

            return ResponseEntity.ok(gameResponse);
        } catch (JsonProcessingException e) {
            log.error("Error deserializing reward config: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game) throws JsonProcessingException {
        String rewardConfigJson = gameService.serializeRewardConfig(gameService.deserializeRewardConfig(game.getRewardConfig()));
        game.setRewardConfig(rewardConfigJson);
        game.setId(UUID.randomUUID().toString());
        return ResponseEntity.ok(gameService.createGame(game));
    }

    @PostMapping("/{id}/update")
    public ResponseEntity<Game> updateGame(@PathVariable String id, @RequestBody Game gameDetails) throws JsonProcessingException {
        String rewardConfigJson = gameService.serializeRewardConfig(gameService.deserializeRewardConfig(gameDetails.getRewardConfig()));
        gameDetails.setRewardConfig(rewardConfigJson);
        return ResponseEntity.ok(gameService.updateGame(id, gameDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable String id) {
        gameService.deleteGame(id);
        return ResponseEntity.ok().build();
    }


//    @PostMapping("/play")
//    public ResponseEntity<GamePlayResponse> playGame(@RequestBody GamePlayRequest gamePlayRequest) {
//        try {
//            GamePlayResponse gamePlayResponse = gameService.playGame(gamePlayRequest.getGameToken());
//            return ResponseEntity.ok(gamePlayResponse);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(null);
//        }
//    }

    @PostMapping("/play")
    public ResponseEntity<GamePlayResponse> playGame(@RequestBody GamePlayRequest gamePlayRequest) {
        try {
            log.info("Received play request with gameToken: " + gamePlayRequest.getGameToken());
            GamePlayResponse gamePlayResponse = gameService.playGame(gamePlayRequest.getGameToken());
            return ResponseEntity.ok(gamePlayResponse);
        } catch (Exception e) {
            log.error("Error occurred while processing play request: " + e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }
}
