package christmas.model.order.dto;

import christmas.exception.OrderException.NotFoundMenuException;
import christmas.model.order.Menu;

public record OrderRequest(Menu menu, int quantity) {
    public static OrderRequest create(String menuName, int quantity) {
        Menu menu = Menu.findByName(menuName)
                .orElseThrow(NotFoundMenuException::new);
        return new OrderRequest(menu, quantity);
    }
}
