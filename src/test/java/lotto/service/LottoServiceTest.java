package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.LottoGenerator;
import lotto.domain.LottoResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoServiceTest {

    LottoService lottoService = new LottoService();

    @Test
    @DisplayName("금액에 맞게 로또가 정확히 생성되는지 확인")
    void shouldGenerateCorrectNumberOfTickets() {
        int money = 8000;
        List<Lotto> tickets = lottoService.buyLottos(money);
        assertEquals(8, tickets.size());
    }

    @Test
    @DisplayName("결과 계산 시 당첨 개수가 올바르게 계산되는지 확인")
    void shouldReturnCorrectLottoResult() {
        List<Lotto> tickets = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(10, 11, 12, 13, 14, 15))
        );

        Lotto winning = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonus = 7;

        LottoResult result = lottoService.checkResult(tickets, winning, bonus);
        assertEquals(1, result.getRankCount(lotto.domain.Rank.FIRST));
    }

    @Test
    @DisplayName("8장의 로또를 구입하고, 당첨 결과 및 수익률을 계산한다 (NsTest 없이)")
    void shouldGenerateLottosAndCalculateResult() {
        // given
        int purchaseAmount = 8000;
        int lottoPrice = 1000;
        int lottoCount = purchaseAmount / lottoPrice;

        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;

        LottoGenerator generator = new LottoGenerator();
        List<Lotto> purchasedLottos = new ArrayList<>();

        purchasedLottos.add(new Lotto(List.of(8, 21, 23, 41, 42, 43)));
        purchasedLottos.add(new Lotto(List.of(3, 5, 11, 16, 32, 38)));
        purchasedLottos.add(new Lotto(List.of(7, 11, 16, 35, 36, 44)));
        purchasedLottos.add(new Lotto(List.of(1, 8, 11, 31, 41, 42)));
        purchasedLottos.add(new Lotto(List.of(13, 14, 16, 38, 42, 45)));
        purchasedLottos.add(new Lotto(List.of(7, 11, 30, 40, 42, 43)));
        purchasedLottos.add(new Lotto(List.of(2, 13, 22, 32, 38, 45)));
        purchasedLottos.add(new Lotto(List.of(1, 3, 5, 14, 22, 45)));

        // when
        LottoResult result = lottoService.checkResult(purchasedLottos, winningLotto, bonusNumber);


        double profitRate = result.calculateProfitRate(purchaseAmount);

        // then
        assertEquals(1, result.getRankCount(lotto.domain.Rank.FIFTH));
        assertEquals(0, result.getRankCount(lotto.domain.Rank.FOURTH));
        assertEquals(0, result.getRankCount(lotto.domain.Rank.THIRD));
        assertEquals(0, result.getRankCount(lotto.domain.Rank.SECOND));
        assertEquals(0, result.getRankCount(lotto.domain.Rank.FIRST));
        assertEquals(62.5, profitRate);
    }
}
