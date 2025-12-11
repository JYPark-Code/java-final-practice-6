package oncall.domain.calendar;

import java.util.ArrayList;
import java.util.List;

public class CalendarGenerator {

    public List<OnCallDate> generate(MonthInfo info){
        int daysInMonth = calculateDaysInMonth(info.getMonth());
        List<OnCallDate> dates = new ArrayList<>();

        DayOfWeekKorean currentDayOfWeek = info.getStartDay();

        for(int day = 1; day <= daysInMonth; day++){
            boolean legalHoliday = Holidays.isLegalHoliday(info.getMonth(), day);
            OnCallDate onCallDate = new OnCallDate(
                    info.getMonth(),
                    day,
                    currentDayOfWeek,
                    legalHoliday
            );
            dates.add(onCallDate);
            currentDayOfWeek = currentDayOfWeek.next();
        }

        return dates;
    }

    private int calculateDaysInMonth(int month) {
        if(month == 2){
            return 28;
        }
        if(month == 4 || month == 6 || month == 9 || month == 11){
            return 30;
        }
        return 31;
    }

}
