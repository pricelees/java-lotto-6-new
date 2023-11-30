package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoWinningTypeTest {
    @DisplayName("당첨 갯수와 보너스 번호 매칭 여부를 이용하여 당첨 타입을 정확하게 얻어내는지 확인")
    @ParameterizedTest(name = "{2}")
    @MethodSource("provideArguments")
    void from(int matchedCount, boolean isBonusNumberMatched, LottoWinningType expectedResult) {
        assertThat(LottoWinningType.from(matchedCount, isBonusNumberMatched))
                .isEqualTo(expectedResult);
    }

    static Stream<Arguments> provideArguments() {
        return Stream.of(
                arguments(3, false, LottoWinningType.THREE_NUMBER_MATCHED),
                arguments(4, false, LottoWinningType.FOUR_NUMBER_MATCHED),
                arguments(5, false, LottoWinningType.FIVE_NUMBER_MATCHED),
                arguments(5, true, LottoWinningType.FIVE_AND_BONUS_MATCHED),
                arguments(6, false, LottoWinningType.SIX_NUMBER_MATCHED)
        );
    }
}