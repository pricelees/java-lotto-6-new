package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoCreatorTest {
    @DisplayName("유효하지 않은 입력 금액에 대한 예외 발생 확인")
    @ParameterizedTest(name = "{1}원, {0}")
    @CsvSource(value = {"1000원 미만인 경우 / 999", "1000원 단위가 아닌 경우 / 1001"}, delimiter = '/')
    void createLottoByAmount(String testName, int purchaseAmount) {
        assertThatThrownBy(() -> LottoCreator.createLottoByAmount(purchaseAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1000원");
    }
}