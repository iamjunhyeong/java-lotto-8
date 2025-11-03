package lotto.exception;

public enum ErrorMessage {

    INVALID_LOTTO_NUMBER_COUNT("로또 번호는 6개여야 합니다."),
    INVALID_LOTTO_NUMBER_RANGE("로또 번호는 1부터 45 사이의 숫자여야 합니다."),
    DUPLICATE_LOTTO_NUMBERS("로또 번호는 중복될 수 없습니다."),
    INVALID_PURCHASE_AMOUNT("구매 금액은 1,000원 단위여야 합니다."),
    INVALID_INTEGER_INPUT("입력은 숫자여야 합니다."),
    INVALID_BONUS_NUMBER("보너스 번호는 당첨 번호와 중복될 수 없습니다."),
    DUPLICATE_BONUS_NUMBER("보너스 번호는 당첨 번호와 중복될 수 없습니다."),
    NULL_INPUT("입력 값이 null일 수 없습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return "[ERROR] " + message;
    }
}
