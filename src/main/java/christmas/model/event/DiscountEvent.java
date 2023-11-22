package christmas.model.event;

import christmas.model.order.MenuType;
import christmas.model.order.Order;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.function.BiFunction;

public enum DiscountEvent {
    CHRISTMAS_DDAY_DISCOUNT("크리스마스 디데이 할인", (order, date) -> {
        if (isBeforeChristmas(date)) {
            return (date.getDayOfMonth() - 1) * 100 + 1000;
        }
        return 0;
    }),
    WEEKDAY_DISCOUNT("평일 할인", (order, date) -> {
        if (isWeekday(date)) {
            int dessertCount = order.countItemsByType(MenuType.DESSERT);
            return dessertCount * 2023;
        }
        return 0;
    }),
    WEEKEND_DISCOUNT("주말 할인", (order, date) -> {
        if (isWeekend(date)) {
            int mainCourseCount = order.countItemsByType(MenuType.MAIN);
            return mainCourseCount * 2023;
        }
        return 0;
    }),
    SPECIAL_DISCOUNT("특별 할인", (order, date) -> {
        if (isSpecial(date)) {
            return 1000;
        }
        return 0;
    });

    private final String displayName;
    private final BiFunction<Order, LocalDate, Integer> discount;

    DiscountEvent(String displayName, BiFunction<Order, LocalDate, Integer> discountFunction) {
        this.displayName = displayName;
        this.discount = discountFunction;
    }

    public int applyDiscount(Order order, LocalDate date) {
        return discount.apply(order, date);
    }

    public String getDisplayName() {
        return displayName;
    }

    private static boolean isBeforeChristmas(LocalDate date) {
        return date.getMonthValue() == 12 && date.getDayOfMonth() <= 25;
    }

    private static boolean isWeekday(LocalDate date) {
        return !isWeekend(date);
    }

    private static boolean isWeekend(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day == DayOfWeek.FRIDAY || day == DayOfWeek.SATURDAY;
    }

    private static boolean isSpecial(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SUNDAY || date.getDayOfMonth() == 25;
    }
}