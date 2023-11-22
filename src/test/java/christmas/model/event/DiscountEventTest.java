package christmas.model.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.order.Menu;
import christmas.model.order.Order;
import christmas.model.order.dto.OrderRequest;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DiscountEventTest {
    private Order order;

    @BeforeEach
    void setUp() {
        order = new Order();
        order.markMenusBy(List.of(
                new OrderRequest(Menu.MUSHROOM_SOUP, 1),
                new OrderRequest(Menu.SEAFOOD_PASTA, 2),
                new OrderRequest(Menu.CHOCO_CAKE, 1),
                new OrderRequest(Menu.CHAMPAGNE, 1)
        ));
    }

    @ParameterizedTest
    @CsvSource({
            "CHRISTMAS_DDAY_DISCOUNT, 3_400",
            "WEEKDAY_DISCOUNT, 2_023",
            "WEEKEND_DISCOUNT, 0",
            "SPECIAL_DISCOUNT, 1_000"
    })
    void 할인_적용시_얼만큼_할인했는지_알려준다(DiscountEvent discountEvent, int discountPrice) {
        LocalDate date = LocalDate.of(2023, 12, 25);

        assertThat(discountEvent.applyDiscount(order, date)).isEqualTo(discountPrice);
    }

    @ParameterizedTest
    @CsvSource({
            "1, 1_000",
            "24, 3_300",
            "25, 3_400",
            "26, 0",
            "31, 0"
    })
    void 크리스마스_디데이_할인은_크리스마스_당일날까지만_적용된다(int day, int discountPrice) {
        DiscountEvent discountEvent = DiscountEvent.CHRISTMAS_DDAY_DISCOUNT;
        LocalDate date = LocalDate.of(2023, 12, day);

        assertThat(discountEvent.applyDiscount(order, date)).isEqualTo(discountPrice);
    }

    @ParameterizedTest
    @CsvSource({
            "3, 2_023",
            "7, 2_023",
            "8, 0",
            "9, 0"
    })
    void 평일_할인은_일요일부터_목요일까지_디저트_메뉴에만_적용된다(int day, int discountPrice) {
        DiscountEvent discountEvent = DiscountEvent.WEEKDAY_DISCOUNT;
        LocalDate date = LocalDate.of(2023, 12, day);

        assertThat(discountEvent.applyDiscount(order, date)).isEqualTo(discountPrice);
    }

    @ParameterizedTest
    @CsvSource({
            "3, 0",
            "7, 0",
            "8, 4_046",
            "9, 4_046"
    })
    void 주말_할인은_금요일과_토요일에_메인_메뉴에만_적용된다(int day, int discountPrice) {
        DiscountEvent discountEvent = DiscountEvent.WEEKEND_DISCOUNT;
        LocalDate date = LocalDate.of(2023, 12, day);

        assertThat(discountEvent.applyDiscount(order, date)).isEqualTo(discountPrice);
    }

    @ParameterizedTest
    @CsvSource({
            "2, 0",
            "3, 1_000",
            "24, 1_000",
            "25, 1_000",
            "26, 0"
    })
    void 일요일과_크리스마스_당일엔_특별_할인을_적용한다(int day, int discountPrice) {
        DiscountEvent discountEvent = DiscountEvent.SPECIAL_DISCOUNT;
        LocalDate date = LocalDate.of(2023, 12, day);

        assertThat(discountEvent.applyDiscount(order, date)).isEqualTo(discountPrice);
    }
}
