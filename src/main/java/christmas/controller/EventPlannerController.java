package christmas.controller;

import christmas.model.event.Event;
import christmas.model.event.dto.EventResult;
import christmas.model.order.Order;
import christmas.model.order.dto.OrderRequest;
import christmas.model.order.dto.OrderResult;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.time.LocalDate;
import java.util.List;

public class EventPlannerController {
    private final InputView inputView;
    private final OutputView outputView;
    private final Order order;
    private final Event event;

    public EventPlannerController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.order = new Order();
        this.event = new Event();
    }

    public void plan() {
        LocalDate visitDate = planDate();
        planOrder(visitDate);
        applyEventToOrder(visitDate);
    }

    private LocalDate planDate() {
        outputView.printEventPlannerStart();
        return inputView.readDate();
    }

    private void planOrder(LocalDate visitDate) {
        List<OrderRequest> orderRequests = inputView.readOrderRequests();
        OrderResult orderResult = order.markMenusBy(orderRequests);
        outputView.printEventBenefitStartWith(visitDate);
        outputView.printOrderResult(orderResult);
    }

    private void applyEventToOrder(LocalDate visitDate) {
        EventResult eventResult = event.applyTo(order, visitDate);
        outputView.printEventResult(eventResult);
    }
}
