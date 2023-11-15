package christmas.view;

import christmas.model.order.dto.OrderResult;
import java.util.stream.Collectors;

public class OutputFormatter {
    public static String formatOrderMenus(OrderResult orderResult) {
        return orderResult.orderMenus().stream()
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }
}
