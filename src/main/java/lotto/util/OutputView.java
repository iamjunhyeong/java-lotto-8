package lotto.util;

import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Rank;

import java.util.List;

public class OutputView {

    private static final String LOTTO_COUNT = "개를 구매했습니다.";
    private static final String STATISTICS_HEADER = "당첨 통계";
    private static final String SEPARATOR = "---";
    private static final String WINNING_COUNT = "%s - %d개";
    private static final String RATE_RETURN = "총 수익률은 %.1f%%입니다.";

    public void printLottos(List<Lotto> tickets) {
        System.out.println();
        System.out.println(tickets.size() + LOTTO_COUNT);
        for (Lotto ticket : tickets) {
            System.out.println(ticket.getNumbers());
        }
        System.out.println(); // 테스트 포맷상 공백 줄 필요
    }

    public void printStatistics(LottoResult result, List<Lotto> tickets) {
        System.out.println();
        System.out.println(STATISTICS_HEADER);
        System.out.println(SEPARATOR);

        printRankResults(result);

        double profitRate = result.calculateProfitRate(tickets.size() * 1000);
        System.out.printf(RATE_RETURN + "%n", profitRate);
    }

    private void printRankResults(LottoResult result) {
        // 출력 순서를 테스트 케이스 순서에 맞게 고정
        printRankLine(result, Rank.FIFTH, "3개 일치 (5,000원)");
        printRankLine(result, Rank.FOURTH, "4개 일치 (50,000원)");
        printRankLine(result, Rank.THIRD, "5개 일치 (1,500,000원)");
        printRankLine(result, Rank.SECOND, "5개 일치, 보너스 볼 일치 (30,000,000원)");
        printRankLine(result, Rank.FIRST, "6개 일치 (2,000,000,000원)");
    }

    private void printRankLine(LottoResult result, Rank rank, String description) {
        int count = result.getRankCount(rank);
        System.out.printf(WINNING_COUNT + "%n", description, count);
    }
}
