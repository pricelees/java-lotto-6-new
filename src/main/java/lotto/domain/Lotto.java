package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import lotto.constant.LottoConstants;

public record Lotto(List<Integer> numbers) {
    private static final String INVALID_SIZE = "로또의 번호는 6개여야 합니다.";
    private static final String HAS_DUPLICATE_NUMBER = "중복된 번호가 존재합니다.";
    private static final String HAS_INVALID_NUMBER = "범위를 초과하는 숫자가 존재합니다.";

    public Lotto {
        validate(numbers);
    }

    private void validate(List<Integer> numbers) {
        if (isInvalidSize(numbers)) {
            throw new IllegalArgumentException(INVALID_SIZE);
        }
        if (hasDuplicateNumber(numbers)) {
            throw new IllegalArgumentException(HAS_DUPLICATE_NUMBER);
        }
        if (hasInvalidNumber(numbers)) {
            throw new IllegalArgumentException(HAS_INVALID_NUMBER);
        }
    }

    private boolean isInvalidSize(List<Integer> numbers) {
        return numbers.size() != LottoConstants.LOTTO_NUMBER_COUNT;
    }

    private boolean hasDuplicateNumber(List<Integer> numbers) {
        return new HashSet<>(numbers).size() != numbers.size();
    }

    private boolean hasInvalidNumber(List<Integer> numbers) {
        return numbers.stream()
                .anyMatch(number -> number > LottoConstants.LOTTO_MAX_NUMBER
                        || number < LottoConstants.LOTTO_MIN_NUMBER);
    }

    public boolean isContain(int number) {
        return numbers.contains(number);
    }

    public int calculateMatchedCount(Lotto otherLotto) {
        return (int) numbers.stream()
                .filter(otherLotto::isContain)
                .count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Lotto lotto = (Lotto) o;
        return Objects.equals(numbers(), lotto.numbers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers());
    }
}
