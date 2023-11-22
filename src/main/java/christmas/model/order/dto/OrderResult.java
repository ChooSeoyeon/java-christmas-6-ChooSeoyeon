package christmas.model.order.dto;

import christmas.model.order.Order;
import christmas.model.order.OrderMenu;
import java.util.List;

public record OrderResult(List<OrderMenu> orderMenus, int orderTotalPrice) {
    public static OrderResult create(Order order) {
        return new OrderResult(order.extractActiveOrderMenus(), order.sumTotalPrice());
    }
}
