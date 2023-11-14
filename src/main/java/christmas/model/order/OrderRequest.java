package christmas.model.order;

import christmas.exception.MenuOrderException.NotFoundMenuException;

public record OrderRequest(Menu menu, int quantity) {
    public static OrderRequest of(String menuName, int quantity) {
        Menu menu = Menu.findByName(menuName)
                .orElseThrow(NotFoundMenuException::new);
        return new OrderRequest(menu, quantity);
    }
}
