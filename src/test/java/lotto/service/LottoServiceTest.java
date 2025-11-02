package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
