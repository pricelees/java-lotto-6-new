package lotto.dto.request;

import lotto.constant.LottoConstants;

public record LottoPurchaseRequest(long amount) {
    private static final String NOT_ENOUGH_AMOUNT = "1000원 미만의 금액은 입력할 수 없습니다.";
    private static final String NOT_DIVIDED_BY_THOUSAND = "금액은 1000원 단위어야 합니다.";

    public LottoPurchaseRequest {
        if (isNotEnoughAmount(amount)) {
            throw new IllegalArgumentException(NOT_ENOUGH_AMOUNT);
        }
        if (isInvalidAmount(amount)) {
            throw new IllegalArgumentException(NOT_DIVIDED_BY_THOUSAND);
        }
    }

    private boolean isNotEnoughAmount(long amount) {
        return amount < LottoConstants.ONE_LOTTO_PRICE;
    }

    private static boolean isInvalidAmount(long amount) {
        return amount % LottoConstants.ONE_LOTTO_PRICE != LottoConstants.ZERO;
    }
}