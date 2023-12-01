package lotto.service;

import java.util.List;
import lotto.domain.LottoCreator;
import lotto.domain.UserLotto;
import lotto.dto.request.LottoPurchaseRequest;
import lotto.dto.response.PurchasedLottoResponse;

public class LottoBuyService {
    public UserLotto createUserLotto(LottoPurchaseRequest lottoPurchaseRequest) {
        long amount = lottoPurchaseRequest.amount();
        return new UserLotto(LottoCreator.createLottoByAmount(amount));
    }

    public List<PurchasedLottoResponse> showPurchasedLottos(UserLotto userLotto) {
        return userLotto.lottos().stream()
                .map(lotto -> new PurchasedLottoResponse(lotto.numbers()))
                .toList();
    }
}
