package lotto.domain;

import java.util.List;
import java.util.Objects;
import lotto.constant.LottoConstants;

public record UserLotto(List<Lotto> lottos) {

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
        return Objects.equals(lottos(), userLotto.lottos());
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottos());
    }
}
