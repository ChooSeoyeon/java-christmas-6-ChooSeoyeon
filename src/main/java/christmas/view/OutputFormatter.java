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
    private static final DecimalFormat PRICE_FORMAT = new DecimalFormat("#,###'원'");
    private static final int PRICE_VALID_CONDITION = 0;
    private static final String PRICE_PREFIX = "-";
    private static final String BENEFIT_PREFIX = ": -";
    private static final String DISCOUNT_EMPTY_FORMAT = "없음";
    private static final String GIFT_EMPTY_FORMAT = "";
    private static final String GIFT_DESCRIPTION_FORMAT = "\n증정 이벤트";


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
        return PRICE_FORMAT.format(orderResult.orderTotalPrice());
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
                        + PRICE_FORMAT.format(discount.price()))
                .collect(Collectors.joining(LIST_DELIMITER));
    }

    private static String formatGiftBenefit(GiftSummary gift) {
        if (gift.price() == PRICE_VALID_CONDITION) {
            return GIFT_EMPTY_FORMAT;
        }
        return Optional.of(gift)
                .map(g -> GIFT_DESCRIPTION_FORMAT
                        + BENEFIT_PREFIX
                        + PRICE_FORMAT.format(g.price()))
                .orElse(GIFT_EMPTY_FORMAT);
    }

    public static String formatTotalBenefitPrice(EventResult eventResult) {
        StringBuilder builder = new StringBuilder();
        int totalBenefitPrice = eventResult.payment().totalBenefitPrice();
        if (totalBenefitPrice != PRICE_VALID_CONDITION) {
            builder.append(PRICE_PREFIX);
        }
        builder.append(PRICE_FORMAT.format(totalBenefitPrice));
        return builder.toString();
    }

    public static String formatFinalPayment(EventResult eventResult) {
        return PRICE_FORMAT.format(eventResult.payment().finalPayment());
    }
}
