package christmas;

import christmas.model.event.Event;
import christmas.model.event.dto.EventResult;
import christmas.model.order.Order;
import christmas.model.order.dto.OrderRequest;
import christmas.model.order.dto.OrderResult;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.time.LocalDate;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        // MVP 구현
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        Order order = new Order();
        Event event = new Event();

        outputView.printEventPlannerStart();
        LocalDate date = inputView.readDate();

        List<OrderRequest> orderRequests = inputView.readOrderRequests();
        order.markMenusBy(orderRequests);
        OrderResult orderResult = order.createOrderResult();
        outputView.printEventBenefitStartWith(date);
        outputView.printOrderResult(orderResult);

        EventResult eventResult = event.applyTo(order, date);
        outputView.printEventResult(eventResult);
    }
}
