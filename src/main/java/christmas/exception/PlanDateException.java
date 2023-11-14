package christmas.exception;

public class PlanDateException extends IllegalArgumentException {
    public static final String INVALID_DATE_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    public PlanDateException() {
        super(INVALID_DATE_MESSAGE);
    }

    public static class InvalidDateFormatException extends PlanDateException {
    }

    public static class OutOfRangeDateException extends PlanDateException {
    }

    public static class DateInPastException extends PlanDateException {
    }
}
