package oncall;

import oncall.domain.rotation.Rotation;

import java.util.List;

public class RotationTestMain {

    public static void main(String[] args) {
        List<String> workers = List.of("A", "B", "C");

        Rotation rotation = new Rotation(workers);

        System.out.println("=== 기본 순환 테스트 ===");
        System.out.println(rotation.next()); // A
        System.out.println(rotation.next()); // B
        System.out.println(rotation.next()); // C
        System.out.println(rotation.next()); // A

        System.out.println();

        System.out.println("=== swap 테스트 ===");
        Rotation rotation2 = new Rotation(workers);

        System.out.println("peek: " + rotation2.peek()); // A
        rotation2.swapNext(); // A <-> B
        System.out.println(rotation2.next()); // B
        System.out.println(rotation2.next()); // A
        System.out.println(rotation2.next()); // C
    }
}
