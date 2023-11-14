package christmas.model.event.dto;

import christmas.model.event.DiscountEvent;

public record DiscountSummary(String description, int price) {
    public static DiscountSummary create(DiscountEvent discountEvent, int discountPrice) {
        String description = discountEvent.getDisplayName();
        return new DiscountSummary(description, discountPrice);
    }
}
