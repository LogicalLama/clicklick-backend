package server.game.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameResponse {
    private String type;
    private String gameName;
    private RewardConfigResponse rewardConfig;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class RewardConfigResponse {
        private List<Quadrant> quadrants;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        @Builder
        public static class Quadrant {
            private int index;
            private String displayMessage;
            private String color;
        }
    }
}
