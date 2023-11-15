package christmas.model.event.dto;

import christmas.model.event.BadgeEvent;

public record PaymentSummary(int totalBenefitPrice, int finalPayment, String badgeName) {
    public static PaymentSummary createEmpty() {
        return new PaymentSummary(0, 0, BadgeEvent.defaultBadgeName());
    }
}
