package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BonusNumberTest {
    @DisplayName("유효하지 않은 숫자 범위에 대한 예외 발생 확인")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46, 47})
    void constructor_WithInvalidNumber_ThrowsException(int number) {
        assertThatThrownBy(() -> new BonusNumber(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("범위");
    }
}