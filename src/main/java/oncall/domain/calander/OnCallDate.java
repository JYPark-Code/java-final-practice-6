package oncall.domain.calander;

import static oncall.domain.calander.DayOfWeekKorean.일;
import static oncall.domain.calander.DayOfWeekKorean.토;

public class OnCallDate {

    private final int month;
    private final int day;
    private final DayOfWeekKorean dayOfWeek;
    private final boolean legalHoliday;

    public OnCallDate(int month, int day, DayOfWeekKorean dayOfWeek, boolean legalHoliday) {
        this.month = month;
        this.day = day;
        this.dayOfWeek = dayOfWeek;
        this.legalHoliday = legalHoliday;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public DayOfWeekKorean getDayOfWeek() {
        return dayOfWeek;
    }

    public boolean isLegalHoliday() {
        return legalHoliday;
    }

    public boolean isWeekend() {
        return dayOfWeek == 토 ||
                dayOfWeek == 일;
    }

    // 주말 또는 공휴일인가?
    public boolean isHoliday() {
        if(isWeekend()) {
            return true;
        }
        return legalHoliday;
    }

    // 평일 공휴일인가?
    public boolean isWeekdayLegalHoliday() {
        if (isWeekend()) {
            return false;
        }
        return legalHoliday;
    }

}
