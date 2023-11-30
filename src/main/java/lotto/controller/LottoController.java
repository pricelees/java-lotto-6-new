package lotto.controller;

import java.util.List;
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
        UserLotto userLotto = lottoService.buyLotto(lottoPurchaseRequest);
        lottoOutputView.printPurchasedLotto(lottoService.printPurchasedLotto(userLotto));

        return userLotto;
    }

    private LottoPurchaseRequest receivePurchaseAmount() {
        LottoPurchaseRequest lottoPurchaseRequest = ExceptionHandler.retryInputOnException(
                () -> new LottoPurchaseRequest(lottoInputView.receivePurchaseAmount())
        );
        lottoOutputView.printEmptyLine();

        return lottoPurchaseRequest;
    }

    private WinningLotto receiveWinningLotto() {
        WinningNumberRequest winningNumberRequest = receiveWinningNumbers();
        BonusNumberRequest bonusNumberRequest = receiveBonusNumber(winningNumberRequest.winningNumber());

        return lottoService.receiveWinningLottoInfo(winningNumberRequest, bonusNumberRequest);
    }

    private WinningNumberRequest receiveWinningNumbers() {
        WinningNumberRequest winningNumberRequest = ExceptionHandler.retryInputOnException(
                () -> new WinningNumberRequest(lottoInputView.receiveWinningNumber())
        );
        lottoOutputView.printEmptyLine();

        return winningNumberRequest;
    }

    private BonusNumberRequest receiveBonusNumber(List<Integer> winningNumber) {
        BonusNumberRequest bonusNumberRequest = ExceptionHandler.retryInputOnException(
                () -> new BonusNumberRequest(winningNumber, lottoInputView.receiveBonusNumber())
        );
        lottoOutputView.printEmptyLine();

        return bonusNumberRequest;
    }
}
