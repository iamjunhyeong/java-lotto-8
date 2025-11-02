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
        System.out.println(INPUT_AMOUNT);
        String input = Console.readLine();
        return validateMoney(input);
    }

    /** 당첨 번호 입력 */
    public Lotto inputWinningNumbers() {
        System.out.println(INPUT_WINNING_NUMBERS);
        String input = Console.readLine();
        String[] parts = input.split(",");

        if (parts.length != 6) {
            throw new InvalidInputException(ErrorMessage.INVALID_LOTTO_NUMBER_COUNT.getMessage());
        }

        // 입력값 포맷만 검증 → Lotto 객체 내부에서 중복/범위 검증 수행
        List<Integer> numbers = new ArrayList<>();
        for (String part : parts) {
            numbers.add(parseToInt(part.trim()));
        }

        return new Lotto(numbers);
    }

    /** 보너스 번호 입력 */
    public int inputBonusNumber(Lotto winningLotto) {
        System.out.println();
        System.out.println(INPUT_BONUS_NUMBER);
        String input = Console.readLine();
        int bonusNumber = parseToInt(input);

        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new InvalidInputException(ErrorMessage.INVALID_LOTTO_NUMBER_RANGE.getMessage());
        }

        if (winningLotto.getNumbers().contains(bonusNumber)) {
            throw new InvalidInputException(ErrorMessage.DUPLICATE_BONUS_NUMBER.getMessage());
        }

        return bonusNumber;
    }

    /** 구입 금액 검증 */
    private int validateMoney(String money) {
        int amount = parseToInt(money);
        if (amount <= 0 || amount % 1000 != 0) {
            throw new InvalidInputException(ErrorMessage.INVALID_PURCHASE_AMOUNT.getMessage());
        }
        return amount;
    }

    /** 문자열 → 정수 변환 + 예외 처리 */
    private int parseToInt(String input) {
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new InvalidInputException(ErrorMessage.INVALID_INTEGER_INPUT.getMessage());
            }
        }
        return Integer.parseInt(input);
    }
}
