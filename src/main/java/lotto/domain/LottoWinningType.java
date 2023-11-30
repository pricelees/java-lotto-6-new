package lotto.domain;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.function.BiPredicate;
import lotto.constant.LottoConstants;

public enum LottoWinningType {
    THREE_NUMBER_MATCHED(
            5_000L,
            (matchCount, hasBonusNumber) -> matchCount == 3
    ),
    FOUR_NUMBER_MATCHED(
            50_000L,
            (matchCount, hasBonusNumber) -> matchCount == 4
    ),
    FIVE_NUMBER_MATCHED(
            1_500_000L,
            (matchCount, hasBonusNumber) -> matchCount == 5 && !hasBonusNumber
    ),
    FIVE_AND_BONUS_MATCHED(
            30_000_000L,
            (matchCount, hasBonusNumber) -> matchCount == 5 && hasBonusNumber
    ),
    SIX_NUMBER_MATCHED(
            2_000_000_000L,
            (matchCount, hasBonusNumber) -> matchCount == 6
    ),
    NONE(
            0L,
            (matchCount, hasBonusNumber) -> matchCount < 3
    );

    private final long winningAmount;
    private final BiPredicate<Integer, Boolean> condition;

    LottoWinningType(long winningAmount, BiPredicate<Integer, Boolean> condition) {
        this.winningAmount = winningAmount;
        this.condition = condition;
    }

    public static boolean isWin(LottoWinningType lottoWinningType) {
        return lottoWinningType != NONE;
    }

    public static LottoWinningType from(int matchCount, boolean hasBonusNumber) {
        return Arrays.stream(LottoWinningType.values())
                .filter(result -> result.condition.test(matchCount, hasBonusNumber))
                .findFirst()
                .orElse(NONE);
    }

    public static EnumMap<LottoWinningType, Integer> createInitCountsMap() {
        EnumMap<LottoWinningType, Integer> result = new EnumMap<>(LottoWinningType.class);
        Arrays.stream(LottoWinningType.values())
                .filter(LottoWinningType::isWin)
                .forEach(type -> result.put(type, LottoConstants.ZERO));
        return result;
    }

    public long getWinningAmount() {
        return winningAmount;
    }
}
