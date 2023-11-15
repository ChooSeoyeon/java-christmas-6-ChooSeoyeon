package christmas.view;

import christmas.model.event.dto.EventResult;
import christmas.model.order.dto.OrderResult;
import java.time.LocalDate;

public class OutputView {
    private static final String EVENT_PLANNER_START_ANNOUNCE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String EVENT_BENEFIT_START_ANNOUNCE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_MENUS_ANNOUNCE = "\n<주문 메뉴>";
    private static final String ORDER_TOTAL_PRICE_ANNOUNCE = "\n<할인 전 총주문 금액>";
    private static final String GIFT_ANNOUNCE = "\n<증정 메뉴>";
    private static final String BENEFIT_LIST_ANNOUNCE = "\n<혜택 내역>";
    private static final String TOTAL_BENEFIT_PRICE_ANNOUNCE = "\n<총혜택 금액>";
    private static final String FINAL_PAYMENT_ANNOUNCE = "\n<할인 후 예상 결제 금액>";
    private static final String BADGE_ANNOUNCE = "\n<12월 이벤트 배지>";

    public void printEventPlannerStart() {
        System.out.println(EVENT_PLANNER_START_ANNOUNCE);
    }

    public void printEventBenefitStartWith(LocalDate date) {
        System.out.println(OutputFormatter.formatEventBenefitStartWithDate(EVENT_BENEFIT_START_ANNOUNCE, date));
    }

    public void printOrderResult(OrderResult orderResult) {
        printOrderMenus(orderResult);
        printOrderTotalPrice(orderResult);
    }

    private void printOrderMenus(OrderResult orderResult) {
        System.out.println(ORDER_MENUS_ANNOUNCE);
        System.out.println(OutputFormatter.formatOrderMenus(orderResult));
    }

    private void printOrderTotalPrice(OrderResult orderResult) {
        System.out.println(ORDER_TOTAL_PRICE_ANNOUNCE);
        System.out.println(OutputFormatter.formatOrderTotalPrice(orderResult));
    }

    public void printEventResult(EventResult eventResult) {
        printGift(eventResult);
        printBenefitList(eventResult);
        printTotalBenefitPrice(eventResult);
        printFinalPayment(eventResult);
        printBadge(eventResult);
    }

    private void printGift(EventResult eventResult) {
        System.out.println(GIFT_ANNOUNCE);
        System.out.println(eventResult.gift().description());
    }

    private void printBenefitList(EventResult eventResult) {
        System.out.println(BENEFIT_LIST_ANNOUNCE);
        System.out.println(OutputFormatter.formatBenefitList(eventResult));
    }

    private void printTotalBenefitPrice(EventResult eventResult) {
        System.out.println(TOTAL_BENEFIT_PRICE_ANNOUNCE);
        System.out.println(OutputFormatter.formatTotalBenefitPrice(eventResult));
    }

    private void printFinalPayment(EventResult eventResult) {
        System.out.println(FINAL_PAYMENT_ANNOUNCE);
        System.out.println(OutputFormatter.formatFinalPayment(eventResult));
    }

    private void printBadge(EventResult eventResult) {
        System.out.println(BADGE_ANNOUNCE);
        System.out.println(eventResult.payment().badgeName());
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}
