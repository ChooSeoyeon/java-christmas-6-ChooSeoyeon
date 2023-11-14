package christmas.model.order;

import christmas.exception.MenuOrderException.DrinkOnlyOrderException;
import christmas.exception.MenuOrderException.DuplicateMenuException;
import christmas.exception.MenuOrderException.LackingMenuQuantityException;
import christmas.exception.MenuOrderException.OverTotalMenuQuantityException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Order {
    public static final int MAXIMUM_TOTAL_MENU_QUANTITY = 20;
    public static final int MINIMUM_MENU_QUANTITY = 1;
    private final List<OrderMenu> orderMenus;

    public Order() {
        orderMenus = Stream.of(Menu.values())
                .map(OrderMenu::new)
                .collect(Collectors.toList());
    }

    public OrderResult createOrderResult() {
        return OrderResult.create(this);
    }

    public List<OrderMenu> getOrderMenus() {
        return Collections.unmodifiableList(orderMenus); // TODO : count 0이 아닌 경우만 반환하게 수정 필요
    }

    public int sumTotalPrice() {
        return orderMenus.stream()
                .mapToInt(OrderMenu::sumMenuPrice)
                .sum();
    }

    public void markMenusBy(List<OrderRequest> orderRequests) {
        validate(orderRequests);
        for (OrderRequest orderRequest : orderRequests) {
            markMenu(orderRequest.menu(), orderRequest.quantity());
        }
    }

    private void markMenu(Menu menu, int quantity) {
        orderMenus.stream()
                .filter(orderMenu -> orderMenu.isMenuEqual(menu))
                .findFirst()
                .ifPresent(orderMenu -> orderMenu.addQuantity(quantity));
    }

    private void validate(List<OrderRequest> orderRequests) {
        List<Integer> quantities = orderRequests.stream().map(OrderRequest::quantity).toList();
        List<Menu> menus = orderRequests.stream().map(OrderRequest::menu).toList();
        validateLackingMenuQuantity(quantities);
        validateOverTotalMenuQuantity(quantities);
        validateDuplicateMenu(menus);
        validateDrinkOnlyOrder(menus);
    }

    private void validateLackingMenuQuantity(List<Integer> quantities) {
        for (Integer quantity : quantities) {
            if (quantity < MINIMUM_MENU_QUANTITY) {
                throw new LackingMenuQuantityException();
            }
        }
    }

    private void validateOverTotalMenuQuantity(List<Integer> quantities) {
        int totalQuantity = quantities.stream()
                .mapToInt(Integer::intValue)
                .sum();
        if (totalQuantity > MAXIMUM_TOTAL_MENU_QUANTITY) {
            throw new OverTotalMenuQuantityException();
        }
    }

    private void validateDuplicateMenu(List<Menu> menus) {
        Set<Menu> nonDuplicatedMenus = new HashSet<>(menus);
        if (nonDuplicatedMenus.size() < menus.size()) {
            throw new DuplicateMenuException();
        }
    }

    private void validateDrinkOnlyOrder(List<Menu> menus) {
        menus.stream()
                .filter(menu -> menu.getMenuType() != MenuType.DRINK)
                .findAny()
                .orElseThrow(DrinkOnlyOrderException::new);
    }
}