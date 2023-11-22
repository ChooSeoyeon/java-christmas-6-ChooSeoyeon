package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.order.dto.OrderRequest;
import java.time.LocalDate;
import java.util.List;

public class InputView {
    private static final String DATE_PROMPT = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String ORDER_REQUESTS_PROMPT
            = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    public LocalDate readDate() {
        System.out.println(DATE_PROMPT);
        String inputDate = Console.readLine();
        return InputConverter.convertInputDateToLocalDate(inputDate);
    }

    public List<OrderRequest> readOrderRequests() {
        System.out.println(ORDER_REQUESTS_PROMPT);
        String inputOrderRequests = Console.readLine();
        return InputConverter.convertInputOrderRequestsToListOrderRequests(inputOrderRequests);
    }
}
