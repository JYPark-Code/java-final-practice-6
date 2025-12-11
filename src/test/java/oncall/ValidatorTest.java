package oncall;


import oncall.domain.validation.NicknameValidator;
import oncall.domain.validation.RotationValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ValidatorTest {

    @Test
    void 닉네임_정상_검증_통과(){
        List<String> names = List.of("준팍", "도밥", "고니", "수아", "루루");
        NicknameValidator.validate(names);
    }

    @Test
    void 닉네임_개수가_부족하면_예외() {
        List<String> names = List.of("준팍", "도밥", "고니", "수아");

        assertThatThrownBy(() -> NicknameValidator.validate(names))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 닉네임_중복이면_예외() {
        List<String> names = List.of("준팍", "준팍", "고니", "수아", "루루");

        assertThatThrownBy(() -> NicknameValidator.validate(names))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 평일과_휴일_구성원이_같으면_통과() {
        List<String> weekday = List.of("A", "B", "C", "D", "E");
        List<String> holiday = List.of("C", "D", "E", "A", "B");

        RotationValidator.validate(weekday, holiday);
    }

    @Test
    void 평일과_휴일_구성원이_다르면_예외() {
        List<String> weekday = List.of("A", "B", "C", "D", "E");
        List<String> holiday = List.of("A", "B", "C", "D", "X");

        assertThatThrownBy(() -> RotationValidator.validate(weekday, holiday))
                .isInstanceOf(IllegalArgumentException.class);
    }


}
