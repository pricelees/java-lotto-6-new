package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserLottoTest {
    @DisplayName("구입한 로또의 갯수로 구입 금액을 정확히 계산하는지 확인")
    @Test
    void getPurchasedAmountToBuy() {
        UserLotto userLotto = new UserLotto(List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 3, 4, 5, 45)),
                new Lotto(List.of(1, 2, 3, 4, 44, 45))
        ));

        long expectedPurchasedAmount = 3_000L;
        assertThat(userLotto.getPurchasedAmountToBuy())
                .isEqualTo(expectedPurchasedAmount);
    }
}