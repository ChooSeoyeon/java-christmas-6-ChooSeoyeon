package christmas.view;

import christmas.model.event.dto.DiscountSummary;
import christmas.model.event.dto.EventResult;
import christmas.model.event.dto.GiftSummary;
import christmas.model.order.dto.OrderResult;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OutputFormatter {
    private static final String LIST_DELIMITER = "\n";
    private static final DecimalFormat PRICE_FORMAT = new DecimalFormat("#,###");
    private static final String PRICE_SUFFIX = "원";
    private static final String DISCOUNT_EMPTY_FORMAT = "없음\n";
    private static final String GIFT_EMPTY_FORMAT = "";
    private static final int GIFT_EMPTY_CONDITION = 0;
    private static final String GIFT_DESCRIPTION_FORMAT = "증정 이벤트";
    private static final String BENEFIT_PREFIX = ": -";

    public static String formatEventBenefitStartWithDate(String startAnnounce, LocalDate date) {
        int dayOfMonth = date.getDayOfMonth();
        return String.format(startAnnounce, dayOfMonth);
    }

    public static String formatOrderMenus(OrderResult orderResult) {
        return orderResult.orderMenus().stream()
                .map(Object::toString)
                .collect(Collectors.joining(LIST_DELIMITER));
    }

    public static String formatOrderTotalPrice(OrderResult orderResult) {
        return PRICE_FORMAT.format(orderResult.orderTotalPrice()) + PRICE_SUFFIX;
    }

    public static String formatGift(EventResult eventResult) {
        return eventResult.gift().description();
    }

    public static String formatBenefitList(EventResult eventResult) {
        return formatDiscountBenefit(eventResult.discounts()) + formatGiftBenefit(eventResult.gift());
    }

    private static String formatDiscountBenefit(List<DiscountSummary> discounts) {
        if (discounts.isEmpty()) {
            return DISCOUNT_EMPTY_FORMAT;
        }
        return discounts.stream()
                .map(discount -> discount.description()
                        + BENEFIT_PREFIX
                        + discount.price()
                        + PRICE_SUFFIX
                        + LIST_DELIMITER)
                .collect(Collectors.joining());
    }

    private static String formatGiftBenefit(GiftSummary gift) {
        if (gift.price() == GIFT_EMPTY_CONDITION) {
            return GIFT_EMPTY_FORMAT;
        }
        return Optional.of(gift)
                .map(g -> GIFT_DESCRIPTION_FORMAT
                        + BENEFIT_PREFIX
                        + g.price()
                        + PRICE_SUFFIX)
                .orElse(GIFT_EMPTY_FORMAT);
    }
}
