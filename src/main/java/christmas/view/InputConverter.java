package christmas.view;

import christmas.exception.OrderException.EmptyMenuNameException;
import christmas.exception.OrderException.InvalidOrderFormatException;
import christmas.exception.OrderException.NonIntQuantityException;
import christmas.exception.PlanDateException.DateInPastException;
import christmas.exception.PlanDateException.InvalidDateFormatException;
import christmas.exception.PlanDateException.OutOfRangeDateException;
import christmas.model.order.dto.OrderRequest;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class InputConverter {
    private static final int YEAR = 2023;
    private static final int MONTH = 12;
    private static final int TEMP_MONTH = 11;
    private static final int TEMP_DATE = 20;
    private static final String ORDER_DELIMITER = ",";
    private static final String MENU_DELIMITER = "-";

    public static LocalDate convertInputDateToLocalDate(String inputDate) {
        int date = parseInputDateToIntDate(inputDate);
        LocalDate localDate = parseIntDateToLocalDate(date);
        checkDateInPast(localDate);
        return localDate;
    }

    private static int parseInputDateToIntDate(String inputDate) {
        try {
            return Integer.parseInt(inputDate);
        } catch (NumberFormatException exception) {
            throw new InvalidDateFormatException();
        }
    }

    private static LocalDate parseIntDateToLocalDate(int date) {
        try {
            return LocalDate.of(YEAR, MONTH, date);
        } catch (DateTimeException exception) {
            throw new OutOfRangeDateException();
        }
    }

    private static void checkDateInPast(LocalDate date) {
        LocalDate today = LocalDate.of(YEAR, TEMP_MONTH, TEMP_DATE);
        if (date.isBefore(today)) {
            throw new DateInPastException();
        }
    }

    public static List<OrderRequest> convertInputOrderRequestsToListOrderRequests(String inputOrderRequests) {
        return Arrays.stream(inputOrderRequests.split(ORDER_DELIMITER))
                .map(InputConverter::convertInputOrderRequestToOrderRequest)
                .toList();
    }

    private static OrderRequest convertInputOrderRequestToOrderRequest(String inputOrderRequest) {
        List<String> orderRequestParts = parseOrderRequestParts(inputOrderRequest);
        String menuName = parseMenuName(orderRequestParts.get(0));
        int quantity = parseQuantity(orderRequestParts.get(1));
        return OrderRequest.create(menuName, quantity);
    }

    private static List<String> parseOrderRequestParts(String inputOrderRequest) {
        List<String> orderRequestParts = Arrays.stream(inputOrderRequest.split(MENU_DELIMITER)).toList();
        if (orderRequestParts.size() != 2) {
            throw new InvalidOrderFormatException();
        }
        return orderRequestParts;
    }

    private static String parseMenuName(String menuName) {
        if (menuName == null) {
            throw new EmptyMenuNameException();
        }
        return menuName;
    }

    private static int parseQuantity(String quantity) {
        try {
            return Integer.parseInt(quantity);
        } catch (NumberFormatException e) {
            throw new NonIntQuantityException();
        }
    }
}
