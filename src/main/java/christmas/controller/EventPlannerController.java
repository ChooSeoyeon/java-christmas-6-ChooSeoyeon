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
import java.util.function.Supplier;

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
        return repeatUntilSuccess(inputView::readDate);
    }

    private void planOrder(LocalDate visitDate) {
        OrderResult orderResult = repeatUntilSuccess(this::processOrder);
        outputView.printEventBenefitStartWith(visitDate);
        outputView.printOrderResult(orderResult);
    }

    private OrderResult processOrder() {
        List<OrderRequest> orderRequests = inputView.readOrderRequests();
        return order.markMenusBy(orderRequests);
    }

    private void applyEventToOrder(LocalDate visitDate) {
        EventResult eventResult = event.applyTo(order, visitDate);
        outputView.printEventResult(eventResult);
    }

    private <T> T repeatUntilSuccess(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
