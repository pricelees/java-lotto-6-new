package lotto.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.EnumMap;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoWinningResult;
import lotto.domain.LottoWinningType;
import lotto.domain.UserLotto;
import lotto.domain.WinningLotto;
import lotto.dto.response.WinningResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoCompareServiceTest {
    LottoCompareService lottoCompareService;

    @BeforeEach
    void setUp() {
        lottoCompareService = new LottoCompareService();
    }

    @DisplayName("당첨 로또와 구입한 로또를 정확히 비교하는지 확인")
    @Test
    void compare() {
        UserLotto userLotto = new UserLotto(List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 3, 4, 5, 45)),
                new Lotto(List.of(1, 2, 3, 4, 5, 44)),
                new Lotto(List.of(1, 2, 3, 4, 44, 45)),
                new Lotto(List.of(1, 2, 3, 43, 44, 45))
        ));
        WinningLotto winningLotto = WinningLotto.valueOf(List.of(1, 2, 3, 4, 5, 6), 45);
        EnumMap<LottoWinningType, Integer> expected = new EnumMap<>(LottoWinningType.class);
        expected.put(LottoWinningType.THREE_NUMBER_MATCHED, 1);
        expected.put(LottoWinningType.FOUR_NUMBER_MATCHED, 1);
        expected.put(LottoWinningType.FIVE_NUMBER_MATCHED, 1);
        expected.put(LottoWinningType.FIVE_AND_BONUS_MATCHED, 1);
        expected.put(LottoWinningType.SIX_NUMBER_MATCHED, 1);

        assertThat(lottoCompareService.compare(userLotto, winningLotto)).isEqualTo(new LottoWinningResult(expected));
    }

    @DisplayName("당첨 로또와의 비교 결과를 정확히 얻어내는지 확인")
    @Test
    void receiveResults() {
        EnumMap<LottoWinningType, Integer> allTypesCount = new EnumMap<>(LottoWinningType.class);
        allTypesCount.put(LottoWinningType.THREE_NUMBER_MATCHED, 1);
        allTypesCount.put(LottoWinningType.FOUR_NUMBER_MATCHED, 1);
        allTypesCount.put(LottoWinningType.FIVE_NUMBER_MATCHED, 1);
        allTypesCount.put(LottoWinningType.FIVE_AND_BONUS_MATCHED, 1);
        allTypesCount.put(LottoWinningType.SIX_NUMBER_MATCHED, 1);

        LottoWinningResult lottoWinningResult = new LottoWinningResult(allTypesCount);
        long purchaseAmount = 5_000L;

        WinningResponse expected = new WinningResponse(List.of(1, 1, 1, 1, 1), 40631100.0d);
        
        assertThat(lottoCompareService.receiveResults(lottoWinningResult, purchaseAmount))
                .isEqualTo(expected);
    }
}