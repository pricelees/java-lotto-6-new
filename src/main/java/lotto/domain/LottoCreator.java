package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import lotto.constant.LottoConstants;

public class LottoCreator {
    private static final String NOT_ENOUGH_AMOUNT = "1000원 이상의 금액을 입력해주세요.";
    private static final String NOT_DIVIDED_BY_THOUSAND = "금액은 1000원 단위어야 합니다.";

    public static List<Lotto> createLottoByAmount(long purchasedAmount) {
        validate(purchasedAmount);
        long lottoCount = purchasedAmount / LottoConstants.ONE_LOTTO_PRICE;
        List<Lotto> result = new ArrayList<>();
        for (int i = LottoConstants.ZERO; i < lottoCount; i++) {
            result.add(createOneLotto());
        }

        return result;
    }

    private static void validate(long purchasedAmount) {
        if (isNotEnoughAmount(purchasedAmount)) {
            throw new IllegalArgumentException(NOT_ENOUGH_AMOUNT);
        }
        if (isInvalidAmount(purchasedAmount)) {
            throw new IllegalArgumentException(NOT_DIVIDED_BY_THOUSAND);
        }
    }

    private static boolean isNotEnoughAmount(long amount) {
        return amount < LottoConstants.ONE_LOTTO_PRICE;
    }

    private static boolean isInvalidAmount(long amount) {
        return amount % LottoConstants.ONE_LOTTO_PRICE != LottoConstants.ZERO;
    }

    private static Lotto createOneLotto() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(
                LottoConstants.LOTTO_MIN_NUMBER,
                LottoConstants.LOTTO_MAX_NUMBER,
                LottoConstants.LOTTO_NUMBER_COUNT
        );

        List<Integer> sortedNumbers = numbers.stream()
                .sorted()
                .toList();
        return new Lotto(sortedNumbers);
    }
}
