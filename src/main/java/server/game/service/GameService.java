package server.game.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.game.model.Game;
import server.game.model.RewardConfig;
import server.game.repository.GameRepository;
import server.game.model.GameSession;
import server.game.model.GameSessionStatus;
import server.game.repository.GameSessionRepository;
import server.game.response.GamePlayResponse;
import server.game.response.GameResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameSessionRepository gameSessionRepository;

    @Autowired
    private ObjectMapper objectMapper; // Used for JSON conversion


    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Optional<Game> getGameById(String id) {
        return gameRepository.findById(id);
    }

    public Game createGame(Game game) {
        return gameRepository.save(game);
    }

    public Game updateGame(String id, Game gameDetails) {
        return gameRepository.findById(id).map(game -> {
            game.setType(gameDetails.getType());
            game.setGameName(gameDetails.getGameName());
            game.setRewardConfig(gameDetails.getRewardConfig());
            return gameRepository.save(game);
        }).orElseThrow(() -> new RuntimeException("Game not found with id " + id));
    }

    public void deleteGame(String id) {
        gameRepository.deleteById(id);
    }

    public String serializeRewardConfig(RewardConfig rewardConfig) throws JsonProcessingException {
        return objectMapper.writeValueAsString(rewardConfig);
    }

    public RewardConfig deserializeRewardConfig(String rewardConfig) throws JsonProcessingException {
        return objectMapper.readValue(rewardConfig, RewardConfig.class);
    }

    public GameResponse.RewardConfigResponse deserializeRewardConfigResponse(String rewardConfig) throws JsonProcessingException {
        return objectMapper.readValue(rewardConfig, GameResponse.RewardConfigResponse.class);
    }

    public GameSession initiateGameSession(String gameId, String formEntryId) {
        String gameToken = RandomStringUtils.randomAlphanumeric(20);
        GameSession gameSession = GameSession.builder()
                .id(UUID.randomUUID().toString())
                .gameToken(gameToken)
                .gameId(gameId)
                .status(GameSessionStatus.INITIATED)
                .formEntryId(formEntryId)
                .build();
        return gameSessionRepository.save(gameSession);
    }

    public GamePlayResponse playGame(String gameToken) throws JsonProcessingException {
        Optional<GameSession> gameSessionOptional = gameSessionRepository.findByGameToken(gameToken);
        if (!gameSessionOptional.isPresent() || gameSessionOptional.get().getStatus() != GameSessionStatus.INITIATED) {
            throw new RuntimeException("Invalid or already played game session.");
        }

        GameSession gameSession = gameSessionOptional.get();
        Game game = gameRepository.findById(gameSession.getGameId()).orElseThrow(() -> new RuntimeException("Game not found"));

        RewardConfig rewardConfig = deserializeRewardConfig(game.getRewardConfig());

        // Calculate the winning quadrant based on probabilities
        RewardConfig.Quadrant winningQuadrant = getWinningQuadrant(rewardConfig.getQuadrants());

        // Update the game session status to REWARD_GENERATED
        gameSession.setStatus(GameSessionStatus.REWARD_GENERATED);
        gameSessionRepository.save(gameSession);

        return GamePlayResponse.builder()
                .reward(GamePlayResponse.Reward.builder()
                        .index(winningQuadrant.getIndex())
                        .type(winningQuadrant.getRewardType().toString())
                        .value(winningQuadrant.getRewardValue())
                        .build())
                .displayMessage(winningQuadrant.getDisplayMessage())
                .build();
    }

    private RewardConfig.Quadrant getWinningQuadrant(List<RewardConfig.Quadrant> quadrants) {
        BigDecimal randomValue = BigDecimal.valueOf(Math.random());
        BigDecimal cumulativeProbability = BigDecimal.ZERO;
        for (RewardConfig.Quadrant quadrant : quadrants) {
            cumulativeProbability = cumulativeProbability.add(quadrant.getProbability());
            if (randomValue.compareTo(cumulativeProbability) <= 0) {
                return quadrant;
            }
        }
        return quadrants.get(quadrants.size() - 1); // In case of rounding errors
    }
}
