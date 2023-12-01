package lotto.view;

public class LottoInputValidator {
    private static final String NOT_NUMBER = "숫자만 입력 가능합니다.";
    private static final String INVALID_WINNING_NUMBER_FORMAT = "입력 형식이 올바르지 않습니다.";
    private static final String WINNING_NUMBER_FORMAT_REGEX = "(\\d+)(,(\\d+)){5}";

    private LottoInputValidator() {
    }

    public static void validateContainsOnlyNumber(String input) {
        try {
            Long.parseLong(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_NUMBER);
        }
    }

    public static void validateWinningNumber(String input) {
        if (isInvalidWinningNumberFormat(input)) {
            throw new IllegalArgumentException(INVALID_WINNING_NUMBER_FORMAT);
        }
    }


    private static boolean isInvalidWinningNumberFormat(String input) {
        return !input.matches(WINNING_NUMBER_FORMAT_REGEX);
    }
}
