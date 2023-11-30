package lotto.domain;

import lotto.constant.LottoConstants;

public class BonusNumber {
    private static final String INVALID_RANGE = "숫자 범위가 유효하지 않습니다.";
    private final int bonusNumber;

    public BonusNumber(int bonusNumber) {
        validate(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validate(int bonusNumber) {
        if (isInvalidNumber(bonusNumber)) {
            throw new IllegalArgumentException(INVALID_RANGE);
        }
    }

    private boolean isInvalidNumber(int bonusNumber) {
        return bonusNumber > LottoConstants.LOTTO_MAX_NUMBER || bonusNumber < LottoConstants.LOTTO_MIN_NUMBER;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
