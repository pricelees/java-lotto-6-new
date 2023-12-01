package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.EnumMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoWinningResultTest {
    @DisplayName("모든 경우에 1개씩 당첨된 경우의 당첨 금액 확인")
    @Test
    void calculateTotalWinningAmount_WithWinAllWinningTypes() {
        EnumMap<LottoWinningType, Integer> allTypesCount = new EnumMap<>(LottoWinningType.class);
        Arrays.stream(LottoWinningType.values())
                .filter(LottoWinningType::isWin)
                .forEach(type -> allTypesCount.put(type, 1));

        long expectedAmount = Arrays.stream(LottoWinningType.values())
                .mapToLong(LottoWinningType::getWinningAmount)
                .sum();

        LottoWinningResult lottoWinningResult = new LottoWinningResult(allTypesCount);
        assertThat(lottoWinningResult.calculateTotalWinningAmount())
                .isEqualTo(expectedAmount);
    }
}