package leets.enhance.domain.item.entity;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum DestroyProbability {

    MIN(0, 0.05),   // 파괴됐을 경우
    LEVEL_1(1, 0.05),
    LEVEL_2(2, 0.05),
    LEVEL_3(3, 0.05),
    LEVEL_4(4, 0.1),
    LEVEL_5(5, 0.15),
    LEVEL_6(6, 0.2),
    LEVEL_7(7, 0.25),
    LEVEL_8(8, 0.3),
    LEVEL_9(9, 0.35),
    LEVEL_10(10, 0.4),
    LEVEL_11(11, 0.45),
    MAX(12, 0.5);

    private final int currentLevel;
    private final double probability;

    DestroyProbability(int currentLevel, double probability) {
        this.currentLevel = currentLevel;
        this.probability = probability;
    }

    public static double getProbability(int level) {
        return Arrays.stream(values())
                .filter(probability -> probability.getCurrentLevel() == level)
                .map(DestroyProbability::getProbability)
                .findAny()
                .orElse(MAX.probability);
    }
}
