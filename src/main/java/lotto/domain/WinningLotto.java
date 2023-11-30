package lotto.domain;

import java.util.List;

public class WinningLotto {
    private static final String DUPLICATE_WITH_WINNING_NUMBER = "당첨 번호와 중복될 수 없습니다.";
    private final Lotto numbers;
    private final BonusNumber bonusNumber;

    private WinningLotto(Lotto numbers, BonusNumber bonusNumber) {
        validate(numbers, bonusNumber);
        this.numbers = numbers;
        this.bonusNumber = bonusNumber;
    }

    private void validate(Lotto numbers, BonusNumber bonusNumber) {
        if (numbers.isContain(bonusNumber.getBonusNumber())) {
            throw new IllegalArgumentException(DUPLICATE_WITH_WINNING_NUMBER);
        }
    }

    public static WinningLotto valueOf(List<Integer> numbers, int bonusNumber) {
        return new WinningLotto(
                new Lotto(numbers),
                new BonusNumber(bonusNumber)
        );
    }

    public Lotto getNumbers() {
        return numbers;
    }

    public BonusNumber getBonusNumber() {
        return bonusNumber;
    }
}
