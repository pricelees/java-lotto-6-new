package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningLottoTest {
    @DisplayName("당첨 번호와 보너스 번호가 중복될 때의 예외 발생 확인")
    @Test
    void constructor_WithDuplicateNumber_ThrowsException() {
        assertThatThrownBy(() -> WinningLotto.valueOf(List.of(1, 2, 3, 4, 5, 6), 6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복");

    }
}