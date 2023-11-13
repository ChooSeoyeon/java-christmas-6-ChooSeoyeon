package christmas.exception;

public class MenuOrderException extends IllegalArgumentException {
    public static final String INVALID_ORDER_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    public MenuOrderException() {
        super(INVALID_ORDER_MESSAGE);
    }

    public static class InvalidOrderFormatException extends MenuOrderException {
    }

    public static class NotFoundMenuException extends MenuOrderException {
    }

    public static class DuplicateMenuException extends MenuOrderException {
    }

    public static class BeverageOnlyOrderException extends MenuOrderException {
    }

    public static class LackingMenuCountException extends MenuOrderException {
    }

    public static class OverTotalMenuCountException extends MenuOrderException {
    }
}
