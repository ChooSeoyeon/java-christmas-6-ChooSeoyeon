package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.event.BadgeEvent;
import christmas.model.event.DiscountEvent;
import christmas.model.event.GiftEvent;
import christmas.model.order.Order;
import christmas.model.order.OrderMenu;
import christmas.model.order.OrderRequest;
import christmas.model.order.OrderResult;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        // MVP 구현
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String inputDate = Console.readLine();
        LocalDate date = LocalDate.of(2023, 12, Integer.parseInt(inputDate));

        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String inputOrder = Console.readLine();
        List<String> inputOrderMenus = Arrays.stream(inputOrder.split(",")).toList();
        List<OrderRequest> orderRequests = inputOrderMenus.stream()
                .map(OrderRequest::createFromInput)
                .toList();
        Order order = new Order();
        order.markMenusBy(orderRequests);

        OrderResult orderResult = order.createOrderResult();
        List<OrderMenu> orderMenus = orderResult.orderMenus();
        Integer orderTotalPrice = orderResult.orderTotalPrice();
        System.out.println("12월 " + inputDate + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");

        System.out.println();
        System.out.println("<주문 메뉴>");
        System.out.println(orderMenus.stream().map(Object::toString).collect(Collectors.joining("\n")));

        System.out.println();
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(String.format("%,d", orderTotalPrice) + "원");

        int totalDiscount = 0;
        for (DiscountEvent discountEvent : DiscountEvent.values()) {
            int discount = discountEvent.applyDiscount(order, date);
            totalDiscount += discount;
        }

        GiftEvent applicableGift = GiftEvent.determineApplicableGift(order);

        System.out.println("<증정 메뉴>");
        System.out.println(applicableGift.getGiftDescription());

        String badge = BadgeEvent.determineBadge(totalDiscount);
        int finalPrice = order.sumTotalPrice() - totalDiscount;

        System.out.println("\n<혜택 내역>");
        for (DiscountEvent discountEvent : DiscountEvent.values()) {
            int discount = discountEvent.applyDiscount(order, date);
            if (discount > 0) {
                System.out.println(discountEvent.getDisplayName() + ": -" + discount + "원");
            }
        }
        if (applicableGift != GiftEvent.NO_GIFT) {
            System.out.println("증정 이벤트: -" + applicableGift.getMenuPrice() + "원");
        }

        System.out.println("<총혜택 금액>");
        System.out.println("-" + totalDiscount + "원");

        System.out.println("\n<할인 후 예상 결제 금액>");
        System.out.println(finalPrice + "원");

        System.out.println("\n<12월 이벤트 배지>");
        System.out.println(badge);
    }
}
