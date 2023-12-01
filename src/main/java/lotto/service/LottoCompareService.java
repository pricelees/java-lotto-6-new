package lotto.service;

import java.util.EnumMap;
import java.util.List;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.LottoWinningResult;
import lotto.domain.LottoWinningType;
import lotto.domain.UserLotto;
import lotto.domain.WinningLotto;
import lotto.dto.request.BonusNumberRequest;
import lotto.dto.request.WinningNumberRequest;
import lotto.dto.response.WinningResponse;

public class LottoCompareService {
    private static final int COUNT_UNIT = 1;
    private static final int HUNDRED = 100;

    public WinningLotto createWinningLotto(WinningNumberRequest winningNumberRequest,
                                           BonusNumberRequest bonusNumberRequest) {
        return WinningLotto.valueOf(
                winningNumberRequest.winningNumber(),
                bonusNumberRequest.bonusNumber()
        );
    }

    public LottoWinningResult compare(UserLotto userLotto, WinningLotto winningLotto) {
        return new LottoWinningResult(compare(
                userLotto.getLottos(), winningLotto.getNumbers(), winningLotto.getBonusNumber()
        ));
    }

    private EnumMap<LottoWinningType, Integer> compare(List<Lotto> lottos, Lotto winningLotto,
                                                       BonusNumber bonusNumber) {
        EnumMap<LottoWinningType, Integer> eachResultsCount = LottoWinningType.createInitCountsMap();
        lottos.stream()
                .map(lotto -> {
                    int matchCount = lotto.calculateMatchedCount(winningLotto);
                    boolean hasBonusNumber = lotto.isContain(bonusNumber.getBonusNumber());
                    return LottoWinningType.from(matchCount, hasBonusNumber);
                })
                .filter(LottoWinningType::isWin)
                .forEach(lottoWinningType -> eachResultsCount.merge(lottoWinningType, COUNT_UNIT, Integer::sum));

        return eachResultsCount;
    }

    public WinningResponse receiveResults(LottoWinningResult lottoWinningResult, long purchaseAmount) {
        double earningRate = calculateEarningRate(purchaseAmount, lottoWinningResult.calculateTotalWinningAmount());
        return new WinningResponse(lottoWinningResult.getMatchCount(), earningRate);
    }

    private double calculateEarningRate(long purchaseAmount, long winningAmount) {
        return ((double) winningAmount / purchaseAmount) * HUNDRED;
    }
}
