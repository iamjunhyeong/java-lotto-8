package lotto.util;

import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Rank;

import java.util.List;

public class OutputView {

    public void printLottos(List<Lotto> tickets) {
        System.out.println(tickets.size() + "개를 구매했습니다.");
        for (Lotto ticket : tickets) {
            System.out.println(ticket.getNumbers());
        }
    }

    public void printStatistics(LottoResult result, List<Lotto> tickets) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        System.out.println("3개 일치 (5,000원) - " + result.getRankCount(Rank.FIFTH) + "개");
        System.out.println("4개 일치 (50,000원) - " + result.getRankCount(Rank.FOURTH) + "개");
        System.out.println("5개 일치 (1,500,000원) - " + result.getRankCount(Rank.THIRD) + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + result.getRankCount(Rank.SECOND) + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + result.getRankCount(Rank.FIRST) + "개");
        System.out.printf("총 수익률은 %.2f%%입니다.%n", result.calculateProfitRate(tickets.size() * 1000));
    }
}
