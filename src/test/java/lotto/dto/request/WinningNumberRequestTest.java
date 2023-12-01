package lotto.dto.request;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningNumberRequestTest {
    @DisplayName("범위를 벗어나는 숫자에 대한 예외 발생 확인")
    @Test
    void constructor_WithInvalidRange_ThrowsException() {
        List<Integer> winningNumber = List.of(1, 2, 3, 4, 5, 46);
        assertThatThrownBy(() -> new WinningNumberRequest(winningNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("범위");
    }

    @DisplayName("중복되는 숫자에 대한 예외 발생 확인")
    @Test
    void constructor_WithDuplicateNumber_ThrowsException() {
        List<Integer> winningNumber = List.of(1, 1, 3, 4, 5, 6);
        assertThatThrownBy(() -> new WinningNumberRequest(winningNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복");
    }
}