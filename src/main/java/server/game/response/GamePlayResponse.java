package server.game.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GamePlayResponse {
    private Reward reward;
    private String displayMessage;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Reward {
        private int index;
        private String type;
        private String value;
    }
}
