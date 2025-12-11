package oncall;

import oncall.domain.rotation.Rotation;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RotationTest {

    @Test
    void 순환하면서_next_호출() {
        Rotation rotation = new Rotation(List.of("A", "B", "C"));

        assertThat(rotation.next()).isEqualTo("A");
        assertThat(rotation.next()).isEqualTo("B");
        assertThat(rotation.next()).isEqualTo("C");
        assertThat(rotation.next()).isEqualTo("A");
    }

    @Test
    void peek_기능_체크_상태_변화없음() {
        Rotation rotation = new Rotation(List.of("A", "B", "C"));

        assertThat(rotation.peek()).isEqualTo("A");
        assertThat(rotation.peek()).isEqualTo("A");
        assertThat(rotation.next()).isEqualTo("A");
        assertThat(rotation.next()).isEqualTo("B");
    }

    @Test
    void swapNext_순서변경_체크(){
        Rotation rotation = new Rotation(List.of("A", "B", "C"));

        assertThat(rotation.peek()).isEqualTo("A");
        rotation.swapNext(); // A <-> B

        assertThat(rotation.next()).isEqualTo("B");
        assertThat(rotation.next()).isEqualTo("A");
        assertThat(rotation.next()).isEqualTo("C");
    }

}
