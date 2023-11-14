package christmas.model.order;

import java.util.List;

public record OrderResult(List<OrderMenu> orderMenus, int orderTotalPrice) {
    public static OrderResult create(Order order) {
        return new OrderResult(order.getOrderMenus(), order.sumTotalPrice());
    }
}
