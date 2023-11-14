package christmas.model.order;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderTest {
    private Order order;

    @BeforeEach
    void setUp() {
        order = new Order();
    }

    @Test
    void 주문서_생성시_메뉴_목록을_담아_빈_주문서를_생성한다() {
        List<Menu> expectedMenus = List.of(Menu.values());
        List<OrderMenu> expectedOrderMenus = expectedMenus.stream()
                .map(OrderMenu::new)
                .toList();

        assertThat(order.getOrderMenus()).containsAll(expectedOrderMenus);
    }

    @Test
    void 주문할_메뉴를_메뉴_목록에_수량으로_표시할_수_있다() {

    }

    @Test
    void 주문_결과_생성시_주문_메뉴와_할인_전_총주문_금액을_반환한다() {
        List<Menu> expectedMenus = List.of(Menu.values());
        List<OrderMenu> expectedOrderMenus = expectedMenus.stream()
                .map(OrderMenu::new)
                .toList();

        OrderResult orderResult = order.createOrderResult();

        assertThat(orderResult.orderMenus().toString()).isEqualTo(expectedOrderMenus.toString());
        assertThat(orderResult.orderTotalPrice()).isEqualTo(0);
    }
}
