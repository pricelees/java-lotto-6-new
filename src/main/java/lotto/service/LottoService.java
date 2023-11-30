package lotto.service;

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

    public UserLotto buyLotto(LottoPurchaseRequest lottoPurchaseRequest) {
        return lottoBuyService.buyLotto(lottoPurchaseRequest);
    }

    public PurchasedLottoResponse printPurchasedLotto(UserLotto userLotto) {
        return lottoBuyService.printPurchasedLotto(userLotto);
    }

    public WinningLotto receiveWinningLottoInfo(WinningNumberRequest winningNumberRequest, BonusNumberRequest bonusNumberRequest) {
        return lottoCompareService.receiveWinningLottoInfo(winningNumberRequest, bonusNumberRequest);
    }

    public LottoWinningResult compare(UserLotto userLotto, WinningLotto winningLotto) {
        return lottoCompareService.compare(userLotto, winningLotto);
    }

    public WinningResponse receiveResults(LottoWinningResult lottoWinningResult, long purchaseAmount) {
        return lottoCompareService.receiveResults(lottoWinningResult, purchaseAmount);
    }
}
