package oncall;

import oncall.domain.calendar.CalendarGenerator;
import oncall.domain.calendar.DayOfWeekKorean;
import oncall.domain.calendar.MonthInfo;
import oncall.domain.calendar.OnCallDate;
import oncall.domain.rotation.Rotation;
import oncall.domain.schedule.Assignment;
import oncall.domain.schedule.OnCallScheduler;

import java.util.Arrays;
import java.util.List;

public class ScheduleTestMain {

    public static void main(String[] args) {
        // ✅ 1. 12월, 시작 요일 직접 설정 (예: 일요일이면 "일")
        MonthInfo monthInfo = new MonthInfo(12, DayOfWeekKorean.from("월"));

        CalendarGenerator calendarGenerator = new CalendarGenerator();
        List<OnCallDate> dates = calendarGenerator.generate(monthInfo);

        // ✅ 2. 평일 / 휴일 근무 순번 샘플
        List<String> workers = Arrays.asList("준팍", "도밥", "고니", "수아", "루루");

        Rotation weekdayRotation = new Rotation(workers);
        Rotation holidayRotation = new Rotation(workers);

        // ✅ 3. 스케줄러로 근무표 생성
        OnCallScheduler scheduler = new OnCallScheduler();
        List<Assignment> assignments = scheduler.schedule(
                dates,
                weekdayRotation,
                holidayRotation
        );

        // ✅ 4. 출력
        for (Assignment assignment : assignments) {
            printAssignment(assignment);
        }
    }

    private static void printAssignment(Assignment assignment) {
        OnCallDate date = assignment.getDate();

        StringBuilder sb = new StringBuilder();
        sb.append(date.getMonth()).append("월 ")
                .append(date.getDay()).append("일 ")
                .append(date.getDayOfWeek());

        if (date.isWeekdayLegalHoliday()) {
            sb.append("(휴일)");
        }

        sb.append(" ").append(assignment.getWorker());

        System.out.println(sb.toString());
    }
}
