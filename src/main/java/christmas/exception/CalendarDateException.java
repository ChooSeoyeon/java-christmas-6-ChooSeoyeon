package christmas.exception;

public class CalendarDateException extends IllegalArgumentException {
    public static final String INVALID_DATE_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    public CalendarDateException() {
        super(INVALID_DATE_MESSAGE);
    }

    public static class InvalidDateFormatException extends CalendarDateException {
    }

    public static class OutOfRangeDateException extends CalendarDateException {
    }

    public static class DateInPastException extends CalendarDateException {
    }
}
