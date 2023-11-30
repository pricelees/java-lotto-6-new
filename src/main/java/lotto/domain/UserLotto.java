package lotto.domain;

import java.util.List;
import lotto.constant.LottoConstants;

public class UserLotto {
    private final List<Lotto> lottos;

    public UserLotto(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public long getPurchasedAmountToBuy() {
        return (long) lottos.size() * LottoConstants.ONE_LOTTO_PRICE;
    }
}
