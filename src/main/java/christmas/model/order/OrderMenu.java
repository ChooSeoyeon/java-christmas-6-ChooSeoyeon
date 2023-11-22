package christmas.model.order;

import java.util.Objects;

public class OrderMenu {
    private static final int ACTIVE_QUANTITY_CONDITION = 1;
    private final Menu menu;
    private int quantity;

    public OrderMenu(Menu menu) {
        this.menu = menu;
        this.quantity = 0;
    }

    public boolean active() {
        return this.quantity >= ACTIVE_QUANTITY_CONDITION;
    }

    public void addQuantity(int quantityToAdd) {
        this.quantity += quantityToAdd;
    }

    public boolean isMenuEqual(Menu otherMenu) {
        return menu.equals(otherMenu);
    }

    public int sumMenuPrice() {
        return menu.getPrice() * quantity;
    }

    public int contributeToTypeCount(MenuType menuType) {
        if (this.menu.getMenuType().equals(menuType)) {
            return quantity;
        }
        return 0;
    }

    @Override
    public String toString() {
        return String.format("%s %d개", menu.getName(), quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderMenu orderMenu)) {
            return false;
        }
        return quantity == orderMenu.quantity && Objects.equals(menu, orderMenu.menu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menu, quantity);
    }
}
