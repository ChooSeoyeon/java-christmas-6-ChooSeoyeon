package christmas;

import christmas.controller.EventPlannerController;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        new EventPlannerController(new InputView(), new OutputView()).plan();
    }
}
