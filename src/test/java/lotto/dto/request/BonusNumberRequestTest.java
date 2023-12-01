package lotto.dto.request;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BonusNumberRequestTest {
    List<Integer> winningNumber = List.of(1, 2, 3, 4, 5, 6);

    @DisplayName("보너스 번호의 범위 초과에 대한 예외 발생 확인")
    @ParameterizedTest
    @ValueSource(ints = {0, -1, 46})
    void constructor_WithInvalidRange_ThrowsException(int bonusNumber) {
        assertThatThrownBy(() -> new BonusNumberRequest(winningNumber, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("범위");
    }

    @DisplayName("보너스 번호와 당첨 번호의 중복에 대한 예외 발생 확인")
    @ParameterizedTest(name = "당첨 번호 1,2,3,4,5,6 / 보너스 번호 {0}")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    void constructor_WithDuplicate_ThrowsException(int bonusNumber) {
        assertThatThrownBy(() -> new BonusNumberRequest(winningNumber, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복");
    }
}