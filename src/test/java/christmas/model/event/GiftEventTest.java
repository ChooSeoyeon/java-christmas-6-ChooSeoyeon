package christmas.model.event;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class GiftEventTest {
    @ParameterizedTest
    @CsvSource({
            "CHAMPAGNE_GIFT, 120_000",
            "CHAMPAGNE_GIFT, 120_001",
            "NO_GIFT, 119_999",
            "NO_GIFT, 0"
    })
    void 할인_전_총주문_금액에_따라_증정품을_제공한다(GiftEvent giftEvent, int orderTotalPrice) {
        assertThat(GiftEvent.determineGiftBy(orderTotalPrice)).isEqualTo(giftEvent);
    }

    @ParameterizedTest
    @CsvSource({
            "CHAMPAGNE_GIFT, 샴페인 1개",
            "NO_GIFT, 없음"
    })
    void 상세보기_시_증정_메뉴_이름과_개수를_알려준다(GiftEvent giftEvent, String description) {
        assertThat(giftEvent.getGiftDescription()).isEqualTo(description);
    }
}
