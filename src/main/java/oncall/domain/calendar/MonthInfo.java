package oncall.domain.calendar;

public class MonthInfo {

    private final int month;
    private final DayOfWeekKorean startDay;

    public MonthInfo(int month, DayOfWeekKorean startDay) {
        validateMonth(month);
        this.month = month;
        this.startDay = startDay;

    }

    private void validateMonth(int month){
        if(month < 1 || month > 12){
            throw new IllegalArgumentException("월은 1 ~ 12 범위여야 합니다: " + month);
        }
    }

    public int getMonth() {
        return month;
    }

    public DayOfWeekKorean getStartDay() {
        return startDay;
    }
}
