package lotto.domain;

public enum Rank {
    FIRST(6, 2_000_000_000, "6개 일치 (2,000,000,000원)"),
    SECOND(5, 30_000_000, "5개 일치, 보너스 볼 일치 (30,000,000원)"),
    THIRD(5, 1_500_000, "5개 일치 (1,500,000원)"),
    FOURTH(4, 50_000, "4개 일치 (50,000원)"),
    FIFTH(3, 5_000, "3개 일치 (5,000원)"),
    MISS(0, 0, "");

    private final int matchCount;
    private final int prizeMoney;
    private final boolean matchBonus;
    private final String description; // ✅ 추가

    Rank(int matchCount, int prizeMoney, String description) {
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
        this.description = description;
        this.matchBonus = false;
    }

    public String getDescription() {
        return description;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public static Rank valueOf(int matchCount, boolean matchBonus) {
        if (matchCount == 5 && matchBonus)
            return SECOND;
        if (matchCount == 5)
            return THIRD;
        for (Rank rank : values()) {
            if (rank.matchCount == matchCount && !rank.matchBonus) {
                return rank;
            }
        }
        return MISS;
    }
}
