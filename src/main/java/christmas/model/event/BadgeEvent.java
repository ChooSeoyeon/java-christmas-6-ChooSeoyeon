package christmas.model.event;

public enum BadgeEvent {
    NO_BADGE(0, "없음"),
    STAR(5000, "별"),
    TREE(10000, "트리"),
    SANTA(20000, "산타");

    private final int discountThreshold;
    private final String badgeName;

    BadgeEvent(int discountThreshold, String badgeName) {
        this.discountThreshold = discountThreshold;
        this.badgeName = badgeName;
    }

    public static String determineBadge(int totalDiscount) {
        for (BadgeEvent badgeEvent : BadgeEvent.values()) {
            if (totalDiscount >= badgeEvent.discountThreshold) {
                return badgeEvent.badgeName;
            }
        }
        return NO_BADGE.badgeName;
    }
}