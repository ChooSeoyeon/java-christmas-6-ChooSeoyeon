package christmas.model.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class OrderTest {
    private Order order;
    private List<OrderRequest> defaultOrderRequests;

    @BeforeEach
    void setUp() {
        order = new Order();
        defaultOrderRequests = Arrays.asList(
                new OrderRequest(Menu.MUSHROOM_SOUP, 2),
                new OrderRequest(Menu.BBQ_RIBS, 3)
        );
    }

    @Test
    void 주문서_생성시_메뉴_목록을_담아_빈_주문서를_생성한다() {
        List<Menu> emptyMenus = List.of(Menu.values());
        List<OrderMenu> emptyOrderMenus = emptyMenus.stream()
                .map(OrderMenu::new)
                .toList();

        assertThat(order.listAllMenus().toString()).isEqualTo(emptyOrderMenus.toString());
    }

    @ParameterizedTest
    @CsvSource({
            "MUSHROOM_SOUP, 2",
            "BBQ_RIBS, 3"
    })
    void 주문한_메뉴를_주문서의_메뉴_목록에_수량으로_표시할_수_있다(Menu menu, int expectedQuantity) {
        order.markMenusBy(defaultOrderRequests);

        String expectedResult = String.format("%s %d개", menu.getName(), expectedQuantity);
        assertThat(order.listAllMenus())
                .anyMatch(orderMenu -> orderMenu.toString().equals(expectedResult));
    }


    @Test
    void 주문_결과_생성시_주문_메뉴와_할인_전_총주문_금액을_반환한다() {
        order.markMenusBy(defaultOrderRequests);

        OrderResult orderResult = order.createOrderResult();

        int expectedTotalPrice = Menu.MUSHROOM_SOUP.getPrice() * 2 + Menu.BBQ_RIBS.getPrice() * 3;
        assertThat(orderResult.orderMenus())
                .containsExactlyInAnyOrderElementsOf(order.extractActiveOrderMenus());
        assertThat(orderResult.orderTotalPrice())
                .isEqualTo(expectedTotalPrice);
    }

    @Test
    void 주문_수량이_최소_메뉴_수량_미만이면_예외가_발생한다() {
        List<OrderRequest> orderRequests = Collections.singletonList(
                new OrderRequest(Menu.MUSHROOM_SOUP, Order.MINIMUM_MENU_QUANTITY - 1)
        );

        assertThatThrownBy(() -> order.markMenusBy(orderRequests))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 주문_총_수량이_최대_총_메뉴_수량을_초과하면_예외가_발생한다() {
        List<OrderRequest> orderRequests = new ArrayList<>();
        for (int i = 0; i < Order.MAXIMUM_TOTAL_MENU_QUANTITY + 1; i++) {
            orderRequests.add(new OrderRequest(Menu.CAESAR_SALAD, 1));
        }

        assertThatThrownBy(() -> order.markMenusBy(orderRequests))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 주문시_중복된_메뉴가_있으면_예외가_발생한다() {
        List<OrderRequest> orderRequests = Arrays.asList(
                new OrderRequest(Menu.BBQ_RIBS, 2),
                new OrderRequest(Menu.BBQ_RIBS, 3)
        );

        assertThatThrownBy(() -> order.markMenusBy(orderRequests))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 음료만_주문하는_경우_예외가_발생한다() {
        List<OrderRequest> orderRequests = Collections.singletonList(
                new OrderRequest(Menu.ZERO_COLA, 3)
        );

        assertThatThrownBy(() -> order.markMenusBy(orderRequests))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
