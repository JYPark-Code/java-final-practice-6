package oncall.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final String MSG_MONTH_AND_DAY =
            "비상 근무를 배정할 월과 시작 요일을 입력하세요.";
    private static final String MSG_WEEKDAY_WORKERS =
            "평일 비상 근무 순번대로 사원 닉네임을 입력하세요.";
    private static final String MSG_HOLIDAY_WORKERS =
            "휴일 비상 근무 순번대로 사원 닉네임을 입력하세요.";

    private InputView() {
    }

    public static String readMonthAndStartDay() {
        System.out.println(MSG_MONTH_AND_DAY);
        String line = Console.readLine();
        return line;
    }

    public static String readWeekdayWorkers() {
        System.out.println(MSG_WEEKDAY_WORKERS);
        String line = Console.readLine();
        return line;
    }

    public static String readHolidayWorkers(){
        System.out.println(MSG_HOLIDAY_WORKERS);
        String line = Console.readLine();
        return line;
    }


}
