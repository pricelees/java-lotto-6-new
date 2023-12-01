package lotto.controller;

import java.util.function.Supplier;
import lotto.view.Printer;

public class ExceptionHandler {
    private static final String ERROR_PREFIX = "[ERROR] ";

    public static <T> T retryInputOnException(final Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            Printer.printException(ERROR_PREFIX + e.getMessage());
            return retryInputOnException(supplier);
        }
    }
}
