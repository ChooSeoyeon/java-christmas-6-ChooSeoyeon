package christmas.model.order;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Order {
    private List<OrderMenu> orderMenus;

    public Order() {
        orderMenus = Stream.of(Menu.values())
                .map(OrderMenu::new)
                .collect(Collectors.toList());
    }

    public List<OrderMenu> getOrderMenus() {
        return Collections.unmodifiableList(orderMenus);
    }

    public OrderResult createOrderResult() {
        return OrderResult.create(this);
    }

    public int sumTotalPrice() {
        return orderMenus.stream()
                .mapToInt(OrderMenu::sumMenuPrice)
                .sum();
    }
}
