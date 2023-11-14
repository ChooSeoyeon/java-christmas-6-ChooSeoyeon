package christmas.model.event.dto;

import java.util.List;

public record EventResult(GiftSummary gift, List<DiscountSummary> discounts, PaymentSummary payment) {
}
