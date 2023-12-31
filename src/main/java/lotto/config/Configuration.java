package lotto.config;

import lotto.controller.LottoController;
import lotto.service.LottoBuyService;
import lotto.service.LottoCompareService;
import lotto.service.LottoService;
import lotto.view.LottoInputView;
import lotto.view.LottoOutputView;
import lotto.view.Printer;
import lotto.view.Reader;

public class Configuration {
    private Configuration() {
    }

    public static LottoController lottoController() {
        return new LottoController(lottoService(), lottoInputView(), lottoOutputView());
    }

    private static LottoService lottoService() {
        return new LottoService(lottoBuyService(), lottoCompareService());
    }

    private static LottoBuyService lottoBuyService() {
        return new LottoBuyService();
    }

    private static LottoCompareService lottoCompareService() {
        return new LottoCompareService();
    }

    private static LottoInputView lottoInputView() {
        return new LottoInputView(reader(), printer());
    }

    private static LottoOutputView lottoOutputView() {
        return new LottoOutputView(printer());
    }

    private static Reader reader() {
        return new Reader();
    }

    private static Printer printer() {
        return new Printer();
    }
}
