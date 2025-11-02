package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LottoResultTest {

    @Test
    @DisplayName("등수를 추가하면 개수가 정확히 카운트되는지 확인")
    void shouldIncreaseRankCount() {
        LottoResult result = new LottoResult();
        result.addRank(Rank.FIFTH);
        result.addRank(Rank.FIFTH);
        assertEquals(2, result.getRankCount(Rank.FIFTH));
    }

    @Test
    @DisplayName("수익률 계산이 정확한지 확인")
    void shouldCalculateProfitRate() {
        LottoResult result = new LottoResult();
        result.addRank(Rank.FIFTH); // 5,000원
        double rate = result.calculateProfitRate(1_0000); // 10,000원 지출
        assertEquals(50.0, rate, 0.01);
    }
}
