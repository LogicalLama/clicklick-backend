package server.game.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RewardConfig {
    private List<Quadrant> quadrants;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Quadrant {
        private int index;
        private String displayMessage;
        private String color;
        private BigDecimal probability; // Probability of the quadrant
        private RewardType rewardType;  // Enum COUPON, CASHBACK
        private String rewardValue;     // Value of the reward
    }

    public enum RewardType {
        COUPON,
        CASHBACK
    }
}
