package oncall;

import oncall.domain.calendar.DayOfWeekKorean;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class DayOfWeekKoreanTest {

    @Test
    void 문자열에서_요일_파싱_성공(){
        DayOfWeekKorean day = DayOfWeekKorean.from("월");
        assertThat(day).isEqualTo(DayOfWeekKorean.월);
    }

    @Test
    void 문자열에서_요일_파싱_실패(){
        assertThatThrownBy(() -> DayOfWeekKorean.from("월요일"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 요일_순환_검증() {
        assertThat(DayOfWeekKorean.일.next()).isEqualTo(DayOfWeekKorean.월);
        assertThat(DayOfWeekKorean.월.next()).isEqualTo(DayOfWeekKorean.화);

    }

}
