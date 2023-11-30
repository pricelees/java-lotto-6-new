package lotto.dto.request;

import java.util.HashSet;
import java.util.List;
import lotto.constant.LottoConstants;

public record WinningNumberRequest(List<Integer> winningNumber) {
    private static final String INVALID_RANGE = "숫자 범위가 유효하지 않습니다.";
    private static final String DUPLICATE_NUMBER = "중복되는 숫자는 존재할 수 없습니다.";

    public WinningNumberRequest {
        validateWinningNumber(winningNumber);
    }

    private void validateWinningNumber(List<Integer> winningNumber) {
        if (hasInvalidNumber(winningNumber)) {
            throw new IllegalArgumentException(INVALID_RANGE);
        }
        if (hasDuplicateNumber(winningNumber)) {
            throw new IllegalArgumentException(DUPLICATE_NUMBER);
        }
    }

    private boolean hasDuplicateNumber(List<Integer> winningNumber) {
        return new HashSet<>(winningNumber).size() != winningNumber.size();
    }

    private boolean hasInvalidNumber(List<Integer> winningNumber) {
        return winningNumber.stream()
                .anyMatch(
                        number -> number > LottoConstants.LOTTO_MAX_NUMBER || number < LottoConstants.LOTTO_MIN_NUMBER);
    }
}