package leets.enhance.domain.item.entity;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum SuccessProbability {

    MIN(0, 0.9),    // 파괴됐을 경우
    LEVEL_1(1, 0.9),
    LEVEL_2(2, 0.8),
    LEVEL_3(3, 0.7),
    LEVEL_4(4, 0.5),
    LEVEL_5(5, 0.3),
    LEVEL_6(6, 0.1),
    MAX(7, 0.03);

    private final int currentLevel;
    private final double probability;

    SuccessProbability(int currentLevel, double probability) {
        this.currentLevel = currentLevel;
        this.probability = probability;
    }

    public static double getProbability(int level) {
        return Arrays.stream(values())
                .filter(probability -> probability.getCurrentLevel() == level)
                .map(SuccessProbability::getProbability)
                .findAny()
                .orElse(MAX.probability);
    }
}
