package lotto.exception;

public class InvalidInputException extends IllegalArgumentException {

    public InvalidInputException(String message) {
        super("[ERROR] " + message);
    }




}
