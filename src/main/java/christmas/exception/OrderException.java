package christmas.exception;

public class OrderException extends IllegalArgumentException {
    public static final String INVALID_ORDER_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    public OrderException() {
        super(INVALID_ORDER_MESSAGE);
    }

    public static class InvalidOrderFormatException extends OrderException {
    }

    public static class EmptyMenuNameException extends OrderException {
    }

    public static class NonIntQuantityException extends OrderException {
    }

    public static class NotFoundMenuException extends OrderException {
    }

    public static class DuplicateMenuException extends OrderException {
    }

    public static class DrinkOnlyOrderException extends OrderException {
    }

    public static class LackingMenuQuantityException extends OrderException {
    }

    public static class OverTotalMenuQuantityException extends OrderException {
    }
}
