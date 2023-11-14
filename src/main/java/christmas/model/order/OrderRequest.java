package christmas.model.order;

import christmas.exception.OrderException.NotFoundMenuException;
import java.util.Arrays;
import java.util.List;

public record OrderRequest(Menu menu, int quantity) {
    public static OrderRequest createWithMenuName(String menuName, int quantity) {
        Menu menu = Menu.findByName(menuName)
                .orElseThrow(NotFoundMenuException::new);
        return new OrderRequest(menu, quantity);
    }

    public static OrderRequest createFromInput(String inputOrderMenu) { // TODO : OutputFormatter로 옮기기
        List<String> splitInputOrder = Arrays.stream(inputOrderMenu.split("-")).toList();
        String menuName = splitInputOrder.get(0);
        String quantity = splitInputOrder.get(1);
        return OrderRequest.createWithMenuName(menuName, Integer.parseInt(quantity));
    }
}
