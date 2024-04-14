package chess;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class PositionTest {
    @Test
    void setNewPosition() {
        Position testPosition = new Position('A', 6);
        assertThat(testPosition).isNotNull();

        assertThat(testPosition.getRow()).isEqualTo('A');
        assertThat(testPosition.getColumn()).isEqualTo(6);
    }
}
