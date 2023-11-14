package christmas.model.event;

import christmas.model.order.Menu;
import christmas.model.order.Order;
import java.util.function.Predicate;

public enum GiftEvent {
    CHAMPAGNE_GIFT(Menu.CHAMPAGNE, "1개", order -> order.sumTotalPrice() >= 120000),
    NO_GIFT(null, "없음", order -> true);

    private final Menu menu;
    private final String countDescription;
    private final Predicate<Order> giftCondition;

    GiftEvent(Menu menu, String countDescription, Predicate<Order> giftCondition) {
        this.menu = menu;
        this.countDescription = countDescription;
        this.giftCondition = giftCondition;
    }

    public static GiftEvent determineApplicableGift(Order order) {
        for (GiftEvent giftEvent : GiftEvent.values()) {
            if (giftEvent.giftCondition.test(order)) {
                return giftEvent;
            }
        }
        return NO_GIFT;
    }

    public String getGiftDescription() {
        if (this == NO_GIFT) {
            return countDescription;
        }
        return menu.getName() + " " + countDescription;
    }

    public Menu getMenu() {
        return menu;
    }

    public String getMenuName() {
        return menu.getName();
    }

    public int getMenuPrice() {
        return menu.getPrice();
    }
}