package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class LottoWinningResult {
    private final Map<LottoWinningType, Integer> allTypesCount;

    public LottoWinningResult(Map<LottoWinningType, Integer> allTypesCount) {
        this.allTypesCount = allTypesCount;
    }

    public long calculateTotalWinningAmount() {
        return allTypesCount.keySet().stream()
                .mapToLong(type -> type.getWinningAmount() * allTypesCount.get(type))
                .sum();
    }

    public List<Integer> getMatchCount() {
        return new ArrayList<>(allTypesCount.values());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoWinningResult that = (LottoWinningResult) o;
        return Objects.equals(allTypesCount, that.allTypesCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(allTypesCount);
    }
}
