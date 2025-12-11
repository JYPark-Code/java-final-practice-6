package oncall.domain.rotation;

import java.util.List;

public class Workers {
    private final List<String> weekdayWorkers;
    private final List<String> holidayWorkers;

    public Workers(List<String> weekdayWorkers, List<String> holidayWorkers) {
        this.weekdayWorkers = weekdayWorkers;
        this.holidayWorkers = holidayWorkers;
    }

    public List<String> getWeekdayWorkers() {
        return weekdayWorkers;
    }

    public List<String> getHolidayWorkers() {
        return holidayWorkers;
    }
}
