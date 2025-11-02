package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.LottoGenerator;
import lotto.domain.LottoResult;
import lotto.domain.Rank;

import java.util.List;

public class LottoService {

    public List<Lotto> buyLottos(int money) {
        int ticketCount = money / 1000;
        return java.util.stream.IntStream.range(0, ticketCount)
                .mapToObj(i -> LottoGenerator.generateLotto())
                .toList();
    }

    public LottoResult checkResult(List<Lotto> tickets, Lotto winningLotto, int bonus) {
        LottoResult lottoResult = new LottoResult();
        for (Lotto ticket : tickets) {
            int matchCount = ticket.countMatchingNumbers(winningLotto);
            boolean bonusMatch = ticket.containsNumber(bonus);
            Rank rank = Rank.valueOf(matchCount, bonusMatch);
            lottoResult.addRank(rank);
        }
        return lottoResult;
    }
}
