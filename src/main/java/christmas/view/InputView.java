package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.exception.PlanDateException.InvalidDateFormatException;
import christmas.exception.PlanDateException.OutOfRangeDateException;
import java.time.LocalDate;

public class InputView {
    private static final String DATE_PROMPT = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final int YEAR = 2023;
    private static final int MONTH = 12;

    public LocalDate readDate() {
        System.out.println(DATE_PROMPT);
        String inputDate = Console.readLine();
        return convertInputDateToLocalDate(inputDate);
    }

    private LocalDate convertInputDateToLocalDate(String inputDate) {
        int date = convertInputDateToInt(inputDate);
        return convertDateToLocalDate(date);
    }

    private int convertInputDateToInt(String inputDate) {
        try {
            return Integer.parseInt(inputDate);
        } catch (NumberFormatException exception) {
            throw new InvalidDateFormatException();
        }
    }

    private LocalDate convertDateToLocalDate(int date) {
        try {
            return LocalDate.of(YEAR, MONTH, date);
        } catch (Exception e) {
            throw new OutOfRangeDateException();
        }
    }
}
