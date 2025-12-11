package oncall.domain.validation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RotationValidator {

    private RotationValidator(){
    }

    public static void validate(List<String> weekdayNames, List<String> holidayNames){
        validateNull(weekdayNames, holidayNames);
        validateSameMember(weekdayNames, holidayNames);
    }

    private static void validateNull(List<String> weekdayNames, List<String> holidayNames) {
        if (weekdayNames == null || holidayNames == null){
            throw new IllegalArgumentException("근무자 목록이 존재하지 않습니다.");
        }
    }

    private static void validateSameMember(List<String> weekdayNames, List<String> holidaysNames) {
        Set<String> weekdaySet = new HashSet<>();
        for (String name : weekdayNames){
            weekdaySet.add(name.trim());
        }


        Set<String> holidaySet = new HashSet<>();
        for (String name : holidaysNames){
            holidaySet.add(name.trim());
        }

        if(!weekdaySet.equals(holidaySet)) {
            throw new IllegalArgumentException("평일과 휴일 근무자 구성원이 동일하지 않습니다.");
        }

    }

}
