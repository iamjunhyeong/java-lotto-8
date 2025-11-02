package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGenerator {

    private final static int LOTTO_MIN_NUMBER = 1;
    private final static int LOTTO_MAX_NUMBER = 45;
    private final static int LOTTO_NUMBER_COUNT = 6;

    public static Lotto generateLotto() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER, LOTTO_NUMBER_COUNT);
        Collections.sort(numbers);
        return new Lotto(numbers);
    }
}
