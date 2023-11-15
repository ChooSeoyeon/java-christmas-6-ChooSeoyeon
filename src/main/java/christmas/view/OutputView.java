package christmas.view;

public class OutputView {
    private static final String EVENT_PLANNER_START = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String EVENT_BENEFIT_START = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";

    public void printEventPlannerStart() {
        System.out.println(EVENT_PLANNER_START);
    }

    public void printEventBenefitStartWith(int date) {
        System.out.println(String.format(EVENT_BENEFIT_START, date));
    }
}
