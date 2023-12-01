package lotto.domain;

import java.util.List;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserLotto userLotto = (UserLotto) o;
        return Objects.equals(getLottos(), userLotto.getLottos());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLottos());
    }
}
