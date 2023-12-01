package lotto.view;

import java.text.DecimalFormat;
import java.util.List;
import lotto.constant.LottoConstants;
import lotto.dto.response.PurchasedLottoResponse;
import lotto.dto.response.WinningResponse;

public class LottoOutputView {
    private static final String PURCHASE_MESSAGE = "%d개를 구매했습니다.";
    private static final String STATISTICS_PREFIX = "당첨 통계\n---";
    private static final String EARNING_RATE_MESSAGE = "총 수익률은 %.1f%%입니다.";
    private static final String STATISTICS_FORMAT = """
            3개 일치 (5,000원) - %s개
            4개 일치 (50,000원) - %s개
            5개 일치 (1,500,000원) - %s개
            5개 일치, 보너스 볼 일치 (30,000,000원) - %s개
            6개 일치 (2,000,000,000원) - %s개
            """;

    private final Printer printer;

    public LottoOutputView(Printer printer) {
        this.printer = printer;
    }

    public void printPurchasedLotto(List<PurchasedLottoResponse> purchasedLottoResponses) {
        printer.printFormat(PURCHASE_MESSAGE + LottoConstants.LINE_SEPARATOR, purchasedLottoResponses.size());
        purchasedLottoResponses.stream()
                .map(PurchasedLottoResponse::numbers)
                .forEach(printer::print);
        printEmptyLine();
    }

    public void printStatistics(WinningResponse winningResponse) {
        printer.print(STATISTICS_PREFIX);
        printMatchedInfo(winningResponse.matchCount());
        printEarningRate(winningResponse.earningRate());
    }

    private void printEarningRate(double earningRate) {
        printer.printFormat(EARNING_RATE_MESSAGE + LottoConstants.LINE_SEPARATOR, earningRate);
    }

    private void printMatchedInfo(List<Integer> matchCounts) {
        printer.printFormat(STATISTICS_FORMAT, formatMatchCount(matchCounts));
    }

    private Object[] formatMatchCount(List<Integer> matchCount) {
        final DecimalFormat formatter = new DecimalFormat("###,###");
        return matchCount.stream()
                .map(formatter::format)
                .toArray();
    }

    public void printEmptyLine() {
        printer.printEmptyLine();
    }
}
