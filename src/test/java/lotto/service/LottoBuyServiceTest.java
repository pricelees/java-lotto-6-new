package lotto.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mockStatic;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.Arrays;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.UserLotto;
import lotto.dto.request.LottoPurchaseRequest;
import lotto.dto.response.PurchasedLottoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

class LottoBuyServiceTest {
    LottoBuyService lottoBuyService;

    @BeforeEach
    void setUp() {
        lottoBuyService = new LottoBuyService();
    }

    @DisplayName("UserLotto 객체를 정확히 생성하는지 확인")
    @Test
    void createUserLotto() {
        LottoPurchaseRequest lottoPurchaseRequest = new LottoPurchaseRequest(5_000L);
        UserLotto actual = createActual(lottoPurchaseRequest,
                List.of(1, 2, 3, 4, 5, 6),
                List.of(1, 2, 3, 4, 5, 45),
                List.of(1, 2, 3, 4, 44, 45),
                List.of(1, 2, 3, 43, 44, 45),
                List.of(1, 2, 42, 43, 44, 45)
        );
        UserLotto expected = new UserLotto(List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 3, 4, 5, 45)),
                new Lotto(List.of(1, 2, 3, 4, 44, 45)),
                new Lotto(List.of(1, 2, 3, 43, 44, 45)),
                new Lotto(List.of(1, 2, 42, 43, 44, 45))
        ));
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("구입한 로또를 List<PurchasedLottoResponse>로 정확히 매핑하는지 확인")
    @Test
    void showPurchasedLottos() {
        UserLotto userLotto = new UserLotto(List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 3, 4, 5, 45)),
                new Lotto(List.of(1, 2, 3, 4, 44, 45)),
                new Lotto(List.of(1, 2, 3, 43, 44, 45)),
                new Lotto(List.of(1, 2, 42, 43, 44, 45))
        ));
        List<PurchasedLottoResponse> expected = List.of(
                new PurchasedLottoResponse(List.of(1, 2, 3, 4, 5, 6)),
                new PurchasedLottoResponse(List.of(1, 2, 3, 4, 5, 45)),
                new PurchasedLottoResponse(List.of(1, 2, 3, 4, 44, 45)),
                new PurchasedLottoResponse(List.of(1, 2, 3, 43, 44, 45)),
                new PurchasedLottoResponse(List.of(1, 2, 42, 43, 44, 45))
        );

        assertThat(lottoBuyService.showPurchasedLottos(userLotto)).isEqualTo(expected);
    }

    @SafeVarargs
    private UserLotto createActual(LottoPurchaseRequest lottoPurchaseRequest, List<Integer> value,
                                   List<Integer>... values) {
        try (final MockedStatic<Randoms> mock = mockStatic(Randoms.class)) {
            mock.when(() -> Randoms.pickUniqueNumbersInRange(1, 45, 6))
                    .thenReturn(value, Arrays.stream(values).toArray());
            return lottoBuyService.createUserLotto(lottoPurchaseRequest);
        }
    }
}