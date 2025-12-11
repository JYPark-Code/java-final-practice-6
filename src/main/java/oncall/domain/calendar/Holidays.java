package oncall.domain.calendar;

import java.util.Set;

public final class Holidays {

    private static final Set<String> LEGAL_HOILDAYS = Set.of(
            "1-1",
            "3-1",
            "5-5",
            "6-6",
            "8-15",
            "10-3",
            "10-9",
            "12-25"
    );

    private Holidays() {

    }

    public static boolean isLegalHoliday(int month, int day) {
        String key = month + "-" + day;
        return LEGAL_HOILDAYS.contains(key);
    }


}

