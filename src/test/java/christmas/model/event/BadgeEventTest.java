package christmas.model.event;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BadgeEventTest {
    @ParameterizedTest
    @CsvSource({
            "없음, 500",
            "별, 5_000",
            "별, 5_001",
            "트리, 10_000",
            "트리, 19_999",
            "산타, 20_000"
    })
    void 총혜택_금액에_따라_배지를_증정한다(String badgeName, int totalDiscountPrice) {
        assertThat(BadgeEvent.determineBadgeNameBy(totalDiscountPrice)).isEqualTo(badgeName);
    }
}
