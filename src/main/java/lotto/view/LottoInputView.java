package lotto.view;

import java.util.Arrays;
import java.util.List;

public class LottoInputView {
    private static final String WINNING_NUMBER_DELIMITER = ",";
    private static final String PURCHASE_AMOUNT_REQUEST = "구입금액을 입력해 주세요.";
    private static final String WINNING_NUMBER_REQUEST = "당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUMBER_REQUEST = "보너스 번호를 입력해 주세요.";
    private final Reader reader;

    public LottoInputView(Reader reader) {
        this.reader = reader;
    }

    public long receivePurchaseAmount() {
        System.out.println(PURCHASE_AMOUNT_REQUEST);
        String input = reader.readLine();
        LottoInputValidator.validateContainsOnlyNumber(input);

        return Long.parseLong(input);
    }

    public List<Integer> receiveWinningNumber() {
        System.out.println(WINNING_NUMBER_REQUEST);
        String input = reader.readLine();
        LottoInputValidator.validateWinningNumber(input);

        return Arrays.stream(input.split(WINNING_NUMBER_DELIMITER))
                .map(Integer::parseInt)
                .toList();
    }

    public int receiveBonusNumber() {
        System.out.println(BONUS_NUMBER_REQUEST);
        String input = reader.readLine();
        LottoInputValidator.validateContainsOnlyNumber(input);

        return Integer.parseInt(input);
    }
}
