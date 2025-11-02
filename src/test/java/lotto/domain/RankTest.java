package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RankTest {

    @Test
    @DisplayName("일치 개수와 보너스 여부로 올바른 등수 반환")
    void shouldReturnCorrectRank() {
        assertEquals(Rank.FIRST, Rank.valueOf(6, false));
        assertEquals(Rank.SECOND, Rank.valueOf(5, true));
        assertEquals(Rank.THIRD, Rank.valueOf(5, false));
        assertEquals(Rank.FOURTH, Rank.valueOf(4, false));
        assertEquals(Rank.FIFTH, Rank.valueOf(3, false));
        assertEquals(Rank.MISS, Rank.valueOf(2, false));
    }

    @Test
    @DisplayName("등수별 상금이 올바르게 매핑되는지 확인")
    void shouldReturnCorrectPrize() {
        assertEquals(2_000_000_000, Rank.FIRST.getPrizeMoney());
        assertEquals(30_000_000, Rank.SECOND.getPrizeMoney());
        assertEquals(1_500_000, Rank.THIRD.getPrizeMoney());
        assertEquals(50_000, Rank.FOURTH.getPrizeMoney());
        assertEquals(5_000, Rank.FIFTH.getPrizeMoney());
    }
}
