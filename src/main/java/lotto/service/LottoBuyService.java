package lotto.service;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoCreator;
import lotto.domain.UserLotto;
import lotto.dto.request.LottoPurchaseRequest;
import lotto.dto.response.PurchasedLottoResponse;

public class LottoBuyService {
    public UserLotto buyLotto(LottoPurchaseRequest lottoPurchaseRequest) {
        long amount = lottoPurchaseRequest.amount();
        return new UserLotto(LottoCreator.createLottoByAmount(amount));
    }

    public PurchasedLottoResponse printPurchasedLotto(UserLotto userLotto) {
        List<List<Integer>> allNumbers = userLotto.getLottos().stream()
                .map(Lotto::getNumbers)
                .toList();

        return new PurchasedLottoResponse(allNumbers);
    }
}
