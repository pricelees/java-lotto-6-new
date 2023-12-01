package lotto.dto.request;

import java.util.List;
import lotto.constant.LottoConstants;

public record BonusNumberRequest(List<Integer> winningNumber, int bonusNumber) {
    private static final String INVALID_RANGE = "숫자 범위가 유효하지 않습니다.";
    private static final String DUPLICATE_WITH_WINNING_NUMBER = "당첨 번호와 중복될 수 없습니다.";

    public BonusNumberRequest {
        validateBonusNumber(winningNumber, bonusNumber);
    }

    private void validateBonusNumber(List<Integer> winningNumber, int bonusNumber) {
        if (isInvalidNumber(bonusNumber)) {
            throw new IllegalArgumentException(INVALID_RANGE);
        }
        if (isDuplicateWithWinningNumber(winningNumber, bonusNumber)) {
            throw new IllegalArgumentException(DUPLICATE_WITH_WINNING_NUMBER);
        }
    }

    private boolean isDuplicateWithWinningNumber(List<Integer> winningNumber, int bonusNumber) {
        return winningNumber.contains(bonusNumber);
    }

    private boolean isInvalidNumber(int bonusNumber) {
        return bonusNumber > LottoConstants.LOTTO_MAX_NUMBER || bonusNumber < LottoConstants.LOTTO_MIN_NUMBER;
    }
}