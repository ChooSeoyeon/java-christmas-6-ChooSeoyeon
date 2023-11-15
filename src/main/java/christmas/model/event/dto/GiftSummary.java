package christmas.model.event.dto;

import christmas.model.event.GiftEvent;

public record GiftSummary(String description, int price) {
    public static GiftSummary create(GiftEvent giftEvent) {
        String description = giftEvent.getGiftDescription();
        int price = giftEvent.getMenuPrice();
        return new GiftSummary(description, price);
    }

    public static GiftSummary createEmpty() {
        GiftEvent giftEvent = GiftEvent.NO_GIFT;
        return create(giftEvent);
    }
}
