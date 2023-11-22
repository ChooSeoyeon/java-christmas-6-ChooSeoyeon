package christmas.model.order;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class OrderMenuTest {
    private OrderMenu orderMenu;

    @BeforeEach
    void setUp() {
        orderMenu = new OrderMenu(Menu.MUSHROOM_SOUP);
    }

    @Test
    void 초기화된_주문서의_메뉴들은_비활성화_상태다() {
        assertThat(orderMenu.active()).isFalse();
    }

    @Test
    void 수량을_추가하면_메뉴는_활성화_상태가_된다() {
        orderMenu.addQuantity(1);

        assertThat(orderMenu.active()).isTrue();
    }

    @ParameterizedTest
    @CsvSource({
            "APPETIZER, 3",
            "MAIN, 0"
    })
    void 메뉴_타입이_일치할_때_타입별로_개수를_세는데에_기여한다(MenuType menuType, int count) {
        orderMenu.addQuantity(3);

        assertThat(orderMenu.contributeToTypeCount(menuType)).isEqualTo(count);
    }
}
