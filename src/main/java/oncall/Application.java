package oncall;

import oncall.domain.calendar.CalendarGenerator;
import oncall.domain.calendar.DayOfWeekKorean;
import oncall.domain.calendar.MonthInfo;
import oncall.domain.calendar.OnCallDate;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        // 달력 테스트
        MonthInfo info = new MonthInfo(12, DayOfWeekKorean.from("월"));
        CalendarGenerator generator = new CalendarGenerator();
        List<OnCallDate> dates = generator.generate(info);

        // 출력
        for(OnCallDate date : dates){
            print(date);
        }
    }

    private static void print(OnCallDate date){
        String result = date.getMonth() + "월 "
                + date.getDay() + "일 "
                + date.getDayOfWeek();

        if (date.isWeekdayLegalHoliday()) {
            result += "(휴일)";
        }

        System.out.println(result);
    }



}
