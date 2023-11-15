package christmas.model.event;

import christmas.model.event.dto.DiscountSummary;
import christmas.model.event.dto.EventResult;
import christmas.model.event.dto.GiftSummary;
import christmas.model.event.dto.PaymentSummary;
import christmas.model.order.Order;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Event {
    private static final int MINIMUM_PRICE_FOR_EVENT = 10_000;

    public EventResult applyTo(Order order, LocalDate date) {
        if (!canApply(order)) {
            return EventResult.createEmpty();
        }
        GiftSummary gift = applyGiftEvent(order);
        List<DiscountSummary> discounts = applyDiscountEvent(order, date);
        PaymentSummary payment = summarizeBenefit(order, gift, discounts);
        return new EventResult(gift, discounts, payment);
    }

    private boolean canApply(Order order) {
        return order.sumTotalPrice() >= MINIMUM_PRICE_FOR_EVENT;
    }

    private GiftSummary applyGiftEvent(Order order) {
        GiftEvent giftEvent = GiftEvent.determineGiftBy(order.sumTotalPrice());
        return GiftSummary.create(giftEvent);
    }

    private List<DiscountSummary> applyDiscountEvent(Order order, LocalDate date) {
        List<DiscountSummary> discountSummaries = new ArrayList<>();
        for (DiscountEvent discountEvent : DiscountEvent.values()) {
            int discountPrice = discountEvent.applyDiscount(order, date);
            if (discountPrice > 0) {
                discountSummaries.add(DiscountSummary.create(discountEvent, discountPrice));
            }
        }
        return discountSummaries;
    }

    private PaymentSummary summarizeBenefit(Order order, GiftSummary gift, List<DiscountSummary> discounts) {
        int totalDiscountPrice = discounts.stream().mapToInt(DiscountSummary::price).sum();
        int totalBenefitPrice = totalDiscountPrice + gift.price();
        int finalPayment = order.sumTotalPrice() - totalDiscountPrice;
        String badgeName = BadgeEvent.determineBadgeNameBy(totalBenefitPrice);
        return new PaymentSummary(totalBenefitPrice, finalPayment, badgeName);
    }
}
