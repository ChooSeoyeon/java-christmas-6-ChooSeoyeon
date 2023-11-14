package christmas.model.event;

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

    public static String determineBadgeNameBy(int totalDiscountPrice) {
        for (BadgeEvent badgeEvent : BadgeEvent.values()) {
            if (totalDiscountPrice >= badgeEvent.priceThreshold) {
                return badgeEvent.badgeName;
            }
        }
        return NO_BADGE.badgeName;
    }
}