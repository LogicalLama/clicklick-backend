package server.game.controller;

import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.game.request.GamePlayRequest;
import server.game.response.GamePlayResponse;
import server.game.response.GameResponse;

import java.util.Arrays;

@RestController
@RequestMapping("/game")
@Slf4j
@Timed
public class GameController {

    @GetMapping("")
    public ResponseEntity<GameResponse> getGame(@RequestParam String gameId) {
        if (gameId == null || gameId.isEmpty()) {
            throw new RuntimeException("gameId is required");
        }
        if (!gameId.equals("6a9c7d01-e717-432a-8856-e8bb7de4eaa1")) {
            throw new RuntimeException("invalid gameId.");
        }

        GameResponse.RewardConfig.Quadrant quadrant1 = GameResponse.RewardConfig.Quadrant.builder()
                .index(1)
                .displayMessage("Win a Car")
                .color("#F5F5DC")
                .build();

        GameResponse.RewardConfig.Quadrant quadrant2 = GameResponse.RewardConfig.Quadrant.builder()
                .index(2)
                .displayMessage("Win a Bike")
                .color("#E1C16E")
                .build();

        GameResponse.RewardConfig.Quadrant quadrant3 = GameResponse.RewardConfig.Quadrant.builder()
                .index(3)
                .displayMessage("Win a Trip")
                .color("#FFEA00")
                .build();

        GameResponse.RewardConfig.Quadrant quadrant4 = GameResponse.RewardConfig.Quadrant.builder()
                .index(4)
                .displayMessage("Win a 100 Dollars")
                .color("#FDDA0D")
                .build();

        GameResponse.RewardConfig.Quadrant quadrant5 = GameResponse.RewardConfig.Quadrant.builder()
                .index(5)
                .displayMessage("Win a 1000 Dollars")
                .color("#FFFF8F")
                .build();

        GameResponse.RewardConfig rewardConfig = GameResponse.RewardConfig.builder()
                .quadrants(Arrays.asList(quadrant1, quadrant2, quadrant3, quadrant4, quadrant5))
                .build();

        GameResponse response = GameResponse.builder()
                .type("SpinWheel")
                .gameName("Lucky Spin")
                .rewardConfig(rewardConfig)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/play")
    public ResponseEntity<GamePlayResponse> playGame(@RequestBody GamePlayRequest request) {
        if (request.getGameToken() == null || request.getGameToken().isEmpty()) {
            throw new RuntimeException("gameToken is required");
        }
        if (!request.getGameToken().equals("4d00c056-68cc-4222-a952-ff3712bcd331")) {
            throw new RuntimeException("invalid game token.");
        }
        // Perform your logic for processing the game play here
        // For this example, we will return a dummy response

        GamePlayResponse.Reward reward = GamePlayResponse.Reward.builder()
                .index(4)
                .type("Cash")
                .value("100 USD")
                .build();

        GamePlayResponse response = GamePlayResponse.builder()
                .reward(reward)
                .displayMessage("Congratulations! You have won 100 USD.")
                .build();

        return ResponseEntity.ok(response);
    }
}
