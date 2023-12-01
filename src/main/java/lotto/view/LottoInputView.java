package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import lotto.dto.request.BonusNumberRequest;
import lotto.dto.request.LottoPurchaseRequest;
import lotto.dto.request.WinningNumberRequest;

public class LottoInputView {
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String WINNING_NUMBER_DELIMITER = ",";
    private static final String PURCHASE_AMOUNT_REQUEST = "구입금액을 입력해 주세요.";
    private static final String WINNING_NUMBER_REQUEST = "당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUMBER_REQUEST = "보너스 번호를 입력해 주세요.";
    private final Reader reader;
    private final Printer printer;

    public LottoInputView(Reader reader, Printer printer) {
        this.reader = reader;
        this.printer = printer;
    }

    public LottoPurchaseRequest receivePurchaseAmount() {
        return retryInputOnException(() -> {
            printer.print(PURCHASE_AMOUNT_REQUEST);
            String input = reader.readLine();
            LottoInputValidator.validateContainsOnlyNumber(input);

            return new LottoPurchaseRequest(Long.parseLong(input));
        });
    }

    public WinningNumberRequest receiveWinningNumber() {
        return retryInputOnException(() -> {
            printer.print(WINNING_NUMBER_REQUEST);
            String input = reader.readLine();
            LottoInputValidator.validateWinningNumber(input);

            return new WinningNumberRequest(Arrays.stream(input.split(WINNING_NUMBER_DELIMITER))
                    .map(Integer::parseInt)
                    .toList());
        });
    }

    public BonusNumberRequest receiveBonusNumber(List<Integer> winningNumber) {
        return retryInputOnException(() -> {
            printer.print(BONUS_NUMBER_REQUEST);
            String input = reader.readLine();
            LottoInputValidator.validateContainsOnlyNumber(input);

            return new BonusNumberRequest(winningNumber, Integer.parseInt(input));
        });
    }

    private <T> T retryInputOnException(final Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            Printer.printException(ERROR_PREFIX + e.getMessage());
            return retryInputOnException(supplier);
        }
    }
}
