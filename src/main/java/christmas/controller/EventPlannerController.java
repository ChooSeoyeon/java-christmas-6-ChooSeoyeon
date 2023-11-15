package christmas.controller;

import christmas.model.event.Event;
import christmas.model.event.dto.EventResult;
import christmas.model.order.Order;
import christmas.model.order.dto.OrderRequest;
import christmas.model.order.dto.OrderResult;
import java.time.LocalDate;
import java.util.List;

public class EventPlannerController {
    private final Order order;
    private final Event event;

    public EventPlannerController() {
        this.order = new Order();
        this.event = new Event();
    }

    public void plan() {
        LocalDate visitDate = planDate();
        planOrder();
        applyEventToOrder(visitDate);
    }

    private LocalDate planDate() {
        return LocalDate.now(); // TODO : 입력값으로 교체하기
    }

    private void planOrder() {
        List<OrderRequest> orderRequests = List.of(); // TODO : 입력값으로 교체하기
        OrderResult orderResult = order.markMenusBy(orderRequests);
        // TODO : 출력하기
    }

    private void applyEventToOrder(LocalDate visitDate) {
        EventResult eventResult = event.applyTo(order, visitDate);
        // TODO : 출력하기
    }
}
