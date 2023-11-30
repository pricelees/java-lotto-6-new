package lotto.dto.response;

import java.util.List;

public record PurchasedLottoResponse(List<List<Integer>> numbers) {
}
