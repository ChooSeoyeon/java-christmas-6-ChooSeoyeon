package christmas.model.order;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class MenuTest {
    @ParameterizedTest
    @CsvSource({
            "양송이수프, MUSHROOM_SOUP",
            "타파스, TAPAS",
            "시저샐러드, CAESAR_SALAD"
    })
    void 이름으로_메뉴_상세정보를_찾을_수_있다(String name, Menu expectedMenu) {
        Optional<Menu> menu = Menu.findByName(name);
        assertThat(menu).isPresent();
        assertThat(menu.get()).isEqualTo(expectedMenu);
    }
}
