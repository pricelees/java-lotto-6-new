package lotto.dto.response;

import java.util.List;

public record WinningResponse (List<Integer> matchCount, double earningRate) {
}