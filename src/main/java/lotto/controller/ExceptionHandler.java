package lotto.controller;

import java.util.function.Supplier;
import lotto.constant.LottoConstants;

public class ExceptionHandler {
    private static final String ERROR_PREFIX = "[ERROR] ";

    public static <T> T retryInputOnException(final Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            System.out.println(ERROR_PREFIX + e.getMessage() + LottoConstants.LINE_SEPARATOR);
            return retryInputOnException(supplier);
        }
    }
}
