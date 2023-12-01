package lotto.dto.request;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoPurchaseRequestTest {
    @DisplayName("유효하지 않은 입력 금액에 대한 예외 발생 확인")
    @ParameterizedTest(name = "{1}원, {0}")
    @CsvSource(value = {"1000원 미만 / 999", "1000원 단위 / 1001"}, delimiter = '/')
    void createLottoByAmount(String expectedContainedMessage, int purchaseAmount) {
        assertThatThrownBy(() -> new LottoPurchaseRequest(purchaseAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(expectedContainedMessage);
    }
}