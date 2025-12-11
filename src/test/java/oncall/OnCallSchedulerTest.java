package oncall;

import oncall.domain.calendar.DayOfWeekKorean;
import oncall.domain.calendar.OnCallDate;
import oncall.domain.rotation.Rotation;
import oncall.domain.schedule.Assignment;
import oncall.domain.schedule.OnCallScheduler;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class OnCallSchedulerTest {

    @Test
    void 같은_사람_연속_배정_swap_하기(){

        // 날짜 3개 모두 평일
        OnCallDate d1 = new OnCallDate(5, 1, DayOfWeekKorean.월, false);
        OnCallDate d2 = new OnCallDate(5, 2, DayOfWeekKorean.화, false);
        OnCallDate d3 = new OnCallDate(5, 3, DayOfWeekKorean.수, false);
        List<OnCallDate> dates = List.of(d1, d2, d3);

        // 연속되게 만듦
        Rotation weekday = new Rotation(List.of("A", "A", "B"));
        Rotation holiday = new Rotation(List.of("X", "Y", "Z")); // 안 씀

        OnCallScheduler scheduler = new OnCallScheduler();
        List<Assignment> result = scheduler.schedule(dates, weekday, holiday);

        List<String> workers = result.stream()
                .map(Assignment::getWorker)
                .toList();

        assertThat(workers)
                .containsExactly("A", "B", "A");

    }

}
