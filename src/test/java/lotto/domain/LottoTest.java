package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {
    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    @Test
    void createLottoByOverSize() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLottoByDuplicatedNumber() {
        // TODO: 이 테스트가 통과할 수 있게 구현 코드 작성
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호 중 범위를 초과하는 숫자가 존재하면 예외가 발생한다.")
    @Test
    void createLottoByNumberOverRange() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 46)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("두 로또를 비교한 뒤 같은 숫자의 갯수 계산 확인")
    @Test
    void calculateMatchedCount() {
        Lotto user = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto winning = new Lotto(List.of(1, 2, 3, 4, 5, 45));

        int expectedMatchedCount = 5;
        assertThat(user.calculateMatchedCount(winning)).isEqualTo(expectedMatchedCount);
    }
}