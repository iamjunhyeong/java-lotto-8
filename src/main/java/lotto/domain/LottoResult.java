package lotto.domain;

import java.util.EnumMap;
import java.util.Map;

public class LottoResult {

    private final Map<Rank, Integer> rankCount = new EnumMap<>(Rank.class);

    public LottoResult() {
        // 모든 Rank를 0으로 초기화 (null 방지)
        for (Rank rank : Rank.values()) {
            rankCount.put(rank, 0);
        }
    }

    /** 당첨 결과를 추가 */
    public void addRank(Rank rank) {
        rankCount.put(rank, rankCount.get(rank) + 1);
    }

    /** 특정 등수의 개수를 반환 */
    public int getRankCount(Rank rank) {
        return rankCount.getOrDefault(rank, 0);
    }

    /** 전체 상금 총합 계산 */
    public long calculateTotalPrize() {
        long total = 0;
        for (Rank rank : Rank.values()) {
            total += (long) rank.getPrizeMoney() * rankCount.get(rank);
        }
        return total;
    }

    /** 수익률 계산 (단위: %) */
    public double calculateProfitRate(int spentMoney) {
        if (spentMoney <= 0) {
            return 0.0;
        }
        double profit = (double) calculateTotalPrize() / spentMoney * 100;
        return Math.round(profit * 100.0) / 100.0; // 소수 둘째 자리 반올림
    }

    /** 통계 출력용 포맷 (UI에서 사용) */
    public Map<Rank, Integer> getStatistics() {
        return Map.copyOf(rankCount);
    }
}
