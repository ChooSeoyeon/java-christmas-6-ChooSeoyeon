package christmas;

import christmas.model.event.Event;
import christmas.model.event.dto.DiscountSummary;
import christmas.model.event.dto.EventResult;
import christmas.model.order.Order;
import christmas.model.order.dto.OrderRequest;
import christmas.model.order.dto.OrderResult;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Application {
    public static void main(String[] args) {
        // MVP 구현
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        Order order = new Order();

        outputView.printEventPlannerStart();
        LocalDate date = inputView.readDate();

        List<OrderRequest> orderRequests = inputView.readOrderRequests();
        order.markMenusBy(orderRequests);
        OrderResult orderResult = order.createOrderResult();

        Integer orderTotalPrice = orderResult.orderTotalPrice();

        outputView.printEventBenefitStartWith(Integer.parseInt(String.valueOf(date.getDayOfMonth())));
        outputView.printOrderResult(orderResult);

        Event event = new Event();
        EventResult eventResult = event.applyTo(order, date);

        System.out.println("\n<증정 메뉴>");
        System.out.println(eventResult.gift().description());

        System.out.println("\n<혜택 내역>");
        Optional<DiscountSummary> optionalFirstDiscount = eventResult.discounts().stream().findFirst();
        optionalFirstDiscount.ifPresentOrElse(
                firstDiscount -> eventResult.discounts().forEach(discountSummary ->
                        System.out.println(discountSummary.description() + ": -" + discountSummary.price() + "원")),
                () -> System.out.println("없음")
        );

        if (eventResult.gift().price() != 0) {
            System.out.println("증정 이벤트: -" + eventResult.gift().price() + "원");
        }

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
