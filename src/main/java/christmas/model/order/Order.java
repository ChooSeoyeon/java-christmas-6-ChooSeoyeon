package christmas.model.order;

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
        return orderMenus;
    }
}
