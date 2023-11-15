package christmas.model.event.dto;

import java.util.Collections;
import java.util.List;

public record EventResult(GiftSummary gift, List<DiscountSummary> discounts, PaymentSummary payment) {
    public static EventResult createEmpty() {
        return new EventResult(
                GiftSummary.createEmpty(),
                Collections.emptyList(),
                PaymentSummary.createEmpty()
        );
    }
}
