package lotto.util;

import camp.nextstep.edu.missionutils.Console;
import lotto.domain.Lotto;
import lotto.exception.ErrorMessage;
import lotto.exception.InvalidInputException;

import java.util.ArrayList;
import java.util.List;

public class InputView {

    private final static String INPUT_AMOUNT = "구입금액을 입력해 주세요.";
    private final static String INPUT_WINNING_NUMBERS = "당첨 번호를 입력해 주세요.";
    private final static String INPUT_BONUS_NUMBER = "보너스 번호를 입력해 주세요.";

    /** 구입 금액 입력 */
    public int inputMoney() {
        try {
            System.out.println(INPUT_AMOUNT);
            String input = Console.readLine();
            return validateMoney(input);
        } catch (InvalidInputException e) {
            return inputMoney(); // 재귀 호출로 재입력 유도
        }
    }

    /** 당첨 번호 입력 */
    public Lotto inputWinningNumbers() {
        try {
            System.out.println(INPUT_WINNING_NUMBERS);
            String input = Console.readLine();
            String[] parts = input.split(",");
            List<Integer> numbers = validateWinningNumbers(parts);
            return new Lotto(numbers);
        } catch (InvalidInputException e) {
            return inputWinningNumbers(); // 재귀 호출로 재입력 유도
        }

    }

    private List<Integer> validateWinningNumbers(String[] parts) {
        if (parts.length != 6) {
            throw new InvalidInputException(ErrorMessage.INVALID_LOTTO_NUMBER_COUNT.getMessage());
        }
        List<Integer> numbers = new ArrayList<>();
        for (String part : parts) {
            numbers.add(parseToInt(part.trim()));
        }
        return numbers;
    }

    /** 보너스 번호 입력 */
    public int inputBonusNumber(Lotto winningLotto) {
        System.out.println();
        try {
            System.out.println(INPUT_BONUS_NUMBER);
            String input = Console.readLine();
            return validateBonusNumber(input.trim(), winningLotto);
        } catch (InvalidInputException e) {
            return inputBonusNumber(winningLotto); // 재귀 호출로 재입력 유도
        }


    }

    private int validateBonusNumber(String input, Lotto winningLotto) {
        int bonusNumber = parseToInt(input);
        if (winningLotto.containsNumber(bonusNumber)) {
            System.out.println(ErrorMessage.DUPLICATE_BONUS_NUMBER.getMessage());
            throw new InvalidInputException(ErrorMessage.DUPLICATE_BONUS_NUMBER.getMessage());
        }
        return bonusNumber;
    }

    /** 구입 금액 검증 */
    private int validateMoney(String money) {
        int amount = parseToInt(money);
        if (amount <= 0 || amount % 1000 != 0) {
            System.out.println(ErrorMessage.INVALID_PURCHASE_AMOUNT.getMessage());
            throw new InvalidInputException(ErrorMessage.INVALID_PURCHASE_AMOUNT.getMessage());
        }
        return amount;
    }

    /** 문자열 → 정수 변환 + 예외 처리 */
    private int parseToInt(String input) {
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) {
                System.out.println(ErrorMessage.INVALID_INTEGER_INPUT.getMessage());
                throw new InvalidInputException(ErrorMessage.INVALID_INTEGER_INPUT.getMessage());
            }
        }
        return Integer.parseInt(input);
    }
}
