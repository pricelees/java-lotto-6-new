package lotto.domain;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class LottoWinningResult {
    private final EnumMap<LottoWinningType, Integer> allTypesCount;

    public LottoWinningResult(EnumMap<LottoWinningType, Integer> allTypesCount) {
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
}
