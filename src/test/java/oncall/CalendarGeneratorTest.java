package oncall;

import oncall.domain.calendar.CalendarGenerator;
import oncall.domain.calendar.DayOfWeekKorean;
import oncall.domain.calendar.MonthInfo;
import oncall.domain.calendar.OnCallDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class CalendarGeneratorTest {

    private final CalendarGenerator generator = new CalendarGenerator();

    @Test
    void 잘못된_월이면_예외(){
        assertThatThrownBy(() -> new MonthInfo(0, DayOfWeekKorean.월))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 일_체크_2월_28일(){
        MonthInfo info = new MonthInfo(2, DayOfWeekKorean.월);
        List<OnCallDate> dates = generator.generate(info);

        assertThat(dates).hasSize(28);
        assertThat(dates.get(0).getDay()).isEqualTo(1);
        assertThat(dates.get(27).getDay()).isEqualTo(28);
    }

    @Test
    void 크리스마스_공휴일_체크() {
        MonthInfo info = new MonthInfo(12, DayOfWeekKorean.일);
        List<OnCallDate> dates = generator.generate(info);

        OnCallDate christmas = dates.stream()
                .filter(d -> d.getDay() == 25)
                .findFirst()
                .orElseThrow();

        assertThat(christmas.isLegalHoliday()).isTrue();
        assertThat(christmas.isWeekdayLegalHoliday())
                .isEqualTo(!christmas.isWeekend());
    }


}
