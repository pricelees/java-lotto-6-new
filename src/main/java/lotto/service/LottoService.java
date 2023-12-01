package lotto.service;

import java.util.List;
import lotto.domain.LottoWinningResult;
import lotto.domain.UserLotto;
import lotto.domain.WinningLotto;
import lotto.dto.request.BonusNumberRequest;
import lotto.dto.request.LottoPurchaseRequest;
import lotto.dto.request.WinningNumberRequest;
import lotto.dto.response.PurchasedLottoResponse;
import lotto.dto.response.WinningResponse;

public class LottoService {
    private final LottoBuyService lottoBuyService;
    private final LottoCompareService lottoCompareService;

    public LottoService(LottoBuyService lottoBuyService, LottoCompareService lottoCompareService) {
        this.lottoBuyService = lottoBuyService;
        this.lottoCompareService = lottoCompareService;
    }

    public UserLotto createUserLotto(LottoPurchaseRequest lottoPurchaseRequest) {
        return lottoBuyService.createUserLotto(lottoPurchaseRequest);
    }

    public List<PurchasedLottoResponse> showPurchasedLotto(UserLotto userLotto) {
        return lottoBuyService.showPurchasedLottos(userLotto);
    }

    public WinningLotto createWinningLotto(WinningNumberRequest winningNumberRequest,
                                           BonusNumberRequest bonusNumberRequest) {
        return lottoCompareService.createWinningLotto(winningNumberRequest, bonusNumberRequest);
    }

    public LottoWinningResult compare(UserLotto userLotto, WinningLotto winningLotto) {
        return lottoCompareService.compare(userLotto, winningLotto);
    }

    public WinningResponse receiveResults(LottoWinningResult lottoWinningResult, long purchaseAmount) {
        return lottoCompareService.receiveResults(lottoWinningResult, purchaseAmount);
    }
}
