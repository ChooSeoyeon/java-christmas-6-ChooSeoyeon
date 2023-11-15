package christmas.view;

import christmas.model.order.dto.OrderResult;
import java.text.DecimalFormat;
import java.util.stream.Collectors;

public class OutputFormatter {
    private static final String ORDER_MENUS_DELIMITER = "\n";
    private static final DecimalFormat PRICE_FORMAT = new DecimalFormat("#,###");
    private static final String PRICE_SUFFIX = "원";

    public static String formatOrderMenus(OrderResult orderResult) {
        return orderResult.orderMenus().stream()
                .map(Object::toString)
                .collect(Collectors.joining(ORDER_MENUS_DELIMITER));
    }

    public static String formatOrderTotalPrice(OrderResult orderResult) {
        return PRICE_FORMAT.format(orderResult.orderTotalPrice()) + PRICE_SUFFIX;
    }
}
