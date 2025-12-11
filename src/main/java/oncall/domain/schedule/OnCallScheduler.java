package oncall.domain.schedule;

import oncall.domain.calendar.OnCallDate;
import oncall.domain.rotation.Rotation;

import java.util.ArrayList;
import java.util.List;

public class OnCallScheduler {

    public List<Assignment> schedule(
            List<OnCallDate> dates,
            Rotation weekdayRotation,
            Rotation holidayRotation
    ) {
        validateInputs(dates, weekdayRotation, holidayRotation);

        List<Assignment> result = new ArrayList<>();
        String previousWorker = null;

        for(OnCallDate date : dates){
            Rotation rotation = chooseRotation(date, weekdayRotation, holidayRotation);

            String candidate = rotation.peek();
            if(isContinuousDuty(previousWorker, candidate)){
                rotation.swapNext();
            }

            String worker = rotation.next();
            Assignment assignment = new Assignment(date, worker);
            result.add(assignment);

            previousWorker = worker;
        }

        return result;

    }


    private void validateInputs(
            List<OnCallDate> dates,
            Rotation weekdayRotation,
            Rotation holidayRotation
    ) {
        if (dates == null || dates.isEmpty()) {
            throw new IllegalArgumentException("스케쥴링 할 날짜가 비어있습니다.");
        }
        if (weekdayRotation == null) {
            throw new IllegalArgumentException("평일 근무 순번이 없습니다.");
        }

        if (holidayRotation == null) {
            throw new IllegalArgumentException("휴일 근무 순번이 없습니다.");
        }
    }

    private Rotation chooseRotation(
            OnCallDate date,
            Rotation weekdayRotation,
            Rotation holidayRotation
    ) {
        if(date.isHoliday()){
            return holidayRotation;
        }
        return weekdayRotation;
    }

    private boolean isContinuousDuty(String previousWorker, String candidate){
        if(previousWorker == null){
            return false;
        }
        return previousWorker.equals(candidate);
    }


}
