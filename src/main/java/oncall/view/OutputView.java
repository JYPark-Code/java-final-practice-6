package oncall.view;

import oncall.domain.calendar.OnCallDate;
import oncall.domain.schedule.Assignment;

import java.util.List;

public class OutputView {

    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String ERROR_INVALID_INPUT =
            "유효하지 않은 입력 값입니다. 다시 입력해주세요.";

    private OutputView(){}

    public static void printAssignments(List<Assignment> assignments) {
        for (Assignment assignment : assignments){
            printAssignment(assignment);
        }
    }

    private static void printAssignment(Assignment assignment){
        OnCallDate date = assignment.getDate();

        StringBuilder sb = new StringBuilder();
        sb.append(date.getMonth()).append("월 ")
          .append(date.getDay()).append("일 ")
          .append(date.getDayOfWeek());

        if(date.isWeekdayLegalHoliday()) {
            sb.append("(휴일)");
        }

        sb.append(" ").append(assignment.getWorker());

        System.out.println(sb.toString());
    }

    public static void printInvalidInputError(){
        System.out.println(ERROR_PREFIX + ERROR_INVALID_INPUT);
    }

}
