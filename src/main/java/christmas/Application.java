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

        System.out.println("\n<총혜택 금액>");
        if (eventResult.payment().totalBenefitPrice() != 0) {
            System.out.print("-");
        }
        System.out.println(eventResult.payment().totalBenefitPrice() + "원");

        System.out.println("\n<할인 후 예상 결제 금액>");
        System.out.println(eventResult.payment().finalPayment() + "원");

        System.out.println("\n<12월 이벤트 배지>");
        System.out.println(eventResult.payment().badgeName());
    }
}
