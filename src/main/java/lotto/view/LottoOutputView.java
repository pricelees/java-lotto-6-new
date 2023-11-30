package lotto.view;

import java.util.List;
import lotto.constant.LottoConstants;
import lotto.dto.response.PurchasedLottoResponse;
import lotto.dto.response.WinningResponse;

public class LottoOutputView {
    private static final String PURCHASE_MESSAGE = "%d개를 구매했습니다.";
    private static final String STATISTICS_PREFIX = "당첨 통계\n---";
    private static final String EARNING_RATE_MESSAGE = "총 수익률은 %.1f%%입니다.";
    private static final String STATISTICS_FORMAT = """
            3개 일치 (5,000원) - %d개
            4개 일치 (50,000원) - %d개
            5개 일치 (1,500,000원) - %d개
            5개 일치, 보너스 볼 일치 (30,000,000원) - %d개
            6개 일치 (2,000,000,000원) - %d개
            """;

    public void printPurchasedLotto(PurchasedLottoResponse purchasedLottoResponse) {
        List<List<Integer>> allLottoNumbers = purchasedLottoResponse.numbers();
        System.out.printf(PURCHASE_MESSAGE + LottoConstants.LINE_SEPARATOR, allLottoNumbers.size());
        allLottoNumbers.forEach(System.out::println);
        System.out.println();
    }

    public void printStatistics(WinningResponse winningResponse) {
        System.out.println(STATISTICS_PREFIX);
        printMatchedInfo(winningResponse.matchCount());
        printEarningRate(winningResponse.earningRate());
    }

    private void printEarningRate(double earningRate) {
        System.out.printf(EARNING_RATE_MESSAGE + LottoConstants.LINE_SEPARATOR, earningRate);
    }

    private void printMatchedInfo(List<Integer> matchCounts) {
        System.out.printf(STATISTICS_FORMAT + LottoConstants.LINE_SEPARATOR, matchCounts.toArray());
    }

    public void printEmptyLine() {
        System.out.println();
    }
}
