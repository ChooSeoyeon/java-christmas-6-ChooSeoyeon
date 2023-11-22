package christmas.model.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.event.dto.EventResult;
import christmas.model.order.Menu;
import christmas.model.order.Order;
import christmas.model.order.dto.OrderRequest;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EventTest {
    private EventResult eventResult;

    @BeforeEach
    void setUp() {
        Order order = new Order();
        Event event = new Event();
        LocalDate date = LocalDate.of(2023, 12, 25);
        List<OrderRequest> orderRequests = Collections.singletonList(
                new OrderRequest(Menu.TBONE_STEAK, 3)
        );
        order.markMenusBy(orderRequests);

        eventResult = event.applyTo(order, date);
    }

    @Test
    void 이벤트를_주문에_적용시_이벤트_적용_결과가_생성된다() {
        assertThat(eventResult).isNotNull();
    }

    @Test
    void 증정_이벤트는_가격_구간대별로_한_가지가_적용된다() {
        assertThat(eventResult.gift()).isNotNull();
    }

    @Test
    void 할인_이벤트는_여러개가_적용될_수_있다() {
        assertThat(eventResult.discounts()).isNotEmpty();
        assertThat(eventResult.discounts().size()).isGreaterThan(1);
    }

    @Test
    void 배지_이벤트는_가격_구간대별로_한_가지가_적용된다() {
        assertThat(eventResult.payment()).isNotNull();
    }
}
