package oncall.domain.calendar;

import java.util.Map;

public enum DayOfWeekKorean {
    일, 월, 화, 수, 목, 금, 토;

    private static final Map<DayOfWeekKorean, DayOfWeekKorean> NEXT_DAY_MAP =
            Map.of(
                    일, 월,
                    월, 화,
                    화, 수,
                    수, 목,
                    목, 금,
                    금, 토,
                    토, 일
            );

    public static DayOfWeekKorean from(String text){
        if(text == null){
            throw new IllegalArgumentException("요일은 빈 칸일 수 없습니다.");
        }

        String trimmed = text.trim();

        for(DayOfWeekKorean day : values()){
            if(day.name().equals(trimmed)){
                return day;
            }
        }
        throw new IllegalArgumentException("존재하지 않는 요일입니다. : " + text);
    }

    public DayOfWeekKorean next(){
        DayOfWeekKorean nextDay = NEXT_DAY_MAP.get(this);

        if (nextDay == null) {
            throw new IllegalStateException("다음 요일이 존재하지 않습니다: " + this);
        }

        return nextDay;
    }

}
