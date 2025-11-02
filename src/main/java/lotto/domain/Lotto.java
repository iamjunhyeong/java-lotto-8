package lotto.domain;

import lotto.exception.ErrorMessage;
import lotto.exception.InvalidInputException;

import java.util.*;

import static java.util.Collections.sort;

public class Lotto {

    private final static int LOTTO_SIZE = 6;
    private final static int MIN_NUMBER = 1;
    private final static int MAX_NUMBER = 45;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        List<Integer> sortedNumbers = new ArrayList<>(numbers);
        sort(sortedNumbers);
        this.numbers = Collections.unmodifiableList(sortedNumbers);
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new InvalidInputException(ErrorMessage.INVALID_LOTTO_NUMBER_COUNT.getMessage());
        }

        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != LOTTO_SIZE) {
            throw new InvalidInputException(ErrorMessage.DUPLICATE_LOTTO_NUMBERS.getMessage());
        }

        for (int number : numbers) {
            if (number < MIN_NUMBER || number > MAX_NUMBER) {
                throw new InvalidInputException(ErrorMessage.INVALID_LOTTO_NUMBER_RANGE.getMessage());
            }
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public boolean containsNumber(int bonus) {
        return numbers.contains(bonus);
    }

    public int countMatchingNumbers(Lotto winningLotto) {
        int matchCount = 0;
        for (int number : winningLotto.getNumbers()) {
            if (numbers.contains(number)) {
                matchCount++;
            }
        }
        return matchCount;
    }
}
