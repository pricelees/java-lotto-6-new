package lotto.controller;

import lotto.domain.LottoWinningResult;
import lotto.domain.UserLotto;
import lotto.domain.WinningLotto;
import lotto.dto.request.BonusNumberRequest;
import lotto.dto.request.LottoPurchaseRequest;
import lotto.dto.request.WinningNumberRequest;
import lotto.dto.response.WinningResponse;
import lotto.service.LottoService;
import lotto.view.LottoInputView;
import lotto.view.LottoOutputView;

public class LottoController {
    private final LottoService lottoService;
    private final LottoInputView lottoInputView;
    private final LottoOutputView lottoOutputView;

    public LottoController(
            LottoService lottoService,
            LottoInputView lottoInputView,
            LottoOutputView lottoOutputView
    ) {
        this.lottoService = lottoService;
        this.lottoInputView = lottoInputView;
        this.lottoOutputView = lottoOutputView;
    }

    public void run() {
        UserLotto userLotto = receiveUserLotto();
        WinningLotto winningLotto = receiveWinningLotto();

        printWinningResults(userLotto, winningLotto);
    }

    private void printWinningResults(UserLotto userLotto, WinningLotto winningLotto) {
        LottoWinningResult lottoWinningResult = lottoService.compare(userLotto, winningLotto);
        WinningResponse winningResponse = lottoService.receiveResults(
                lottoWinningResult,
                userLotto.getPurchasedAmountToBuy()
        );

        lottoOutputView.printStatistics(winningResponse);
    }

    private UserLotto receiveUserLotto() {
        LottoPurchaseRequest lottoPurchaseRequest = receivePurchaseAmount();
        UserLotto userLotto = lottoService.createUserLotto(lottoPurchaseRequest);
        lottoOutputView.printPurchasedLotto(lottoService.showPurchasedLotto(userLotto));

        return userLotto;
    }

    private LottoPurchaseRequest receivePurchaseAmount() {
        LottoPurchaseRequest lottoPurchaseRequest = ExceptionHandler.retryInputOnException(
                lottoInputView::receivePurchaseAmount
        );
        lottoOutputView.printEmptyLine();

        return lottoPurchaseRequest;
    }

    private WinningLotto receiveWinningLotto() {
        WinningNumberRequest winningNumberRequest = receiveWinningNumbers();
        BonusNumberRequest bonusNumberRequest = receiveBonusNumber(winningNumberRequest);

        return lottoService.createWinningLotto(winningNumberRequest, bonusNumberRequest);
    }

    private WinningNumberRequest receiveWinningNumbers() {
        WinningNumberRequest winningNumberRequest = ExceptionHandler.retryInputOnException(
                lottoInputView::receiveWinningNumber
        );
        lottoOutputView.printEmptyLine();

        return winningNumberRequest;
    }

    private BonusNumberRequest receiveBonusNumber(WinningNumberRequest winningNumberRequest) {
        BonusNumberRequest bonusNumberRequest = ExceptionHandler.retryInputOnException(
                () -> lottoInputView.receiveBonusNumber(winningNumberRequest)
        );
        lottoOutputView.printEmptyLine();

        return bonusNumberRequest;
    }
}
