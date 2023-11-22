package christmas.model.event;

import christmas.model.order.Menu;
import java.util.Arrays;

public enum GiftEvent {
    NO_GIFT(0, null, "없음"),
    CHAMPAGNE_GIFT(120_000, Menu.CHAMPAGNE, "1개");

    private final int priceThreshold;
    private final Menu menu;
    private final String countDescription;

    GiftEvent(int priceThreshold, Menu menu, String countDescription) {
        this.priceThreshold = priceThreshold;
        this.menu = menu;
        this.countDescription = countDescription;
    }

    public static GiftEvent determineGiftBy(int orderTotalPrice) {
        return Arrays.stream(GiftEvent.values())
                .filter(giftEvent -> orderTotalPrice >= giftEvent.priceThreshold)
                .reduce((first, second) -> second)
                .orElse(NO_GIFT);
    }

    public String getGiftDescription() {
        if (this == NO_GIFT) {
            return countDescription;
        }
        return menu.getName() + " " + countDescription;
    }

    public int getMenuPrice() {
        if (this == NO_GIFT) {
            return 0;
        }
        return menu.getPrice();
    }
}