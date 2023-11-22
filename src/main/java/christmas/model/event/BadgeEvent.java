package christmas.model.event;

import java.util.Arrays;

public enum BadgeEvent {
    NO_BADGE(0, "없음"),
    STAR(5000, "별"),
    TREE(10000, "트리"),
    SANTA(20000, "산타");

    private final int priceThreshold;
    private final String badgeName;

    BadgeEvent(int discountThreshold, String badgeName) {
        this.priceThreshold = discountThreshold;
        this.badgeName = badgeName;
    }

    public static String determineBadgeNameBy(int totalBenefitPrice) {
        return Arrays.stream(BadgeEvent.values())
                .filter(badgeEvent -> totalBenefitPrice >= badgeEvent.priceThreshold)
                .reduce((first, second) -> second)
                .map(badgeEvent -> badgeEvent.badgeName)
                .orElse(NO_BADGE.badgeName);
    }

    public static String defaultBadgeName() {
        return NO_BADGE.badgeName;
    }
}