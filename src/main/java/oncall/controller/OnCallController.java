package oncall.controller;

import oncall.domain.calendar.CalendarGenerator;
import oncall.domain.calendar.DayOfWeekKorean;
import oncall.domain.calendar.MonthInfo;
import oncall.domain.calendar.OnCallDate;
import oncall.domain.rotation.Rotation;
import oncall.domain.rotation.Workers;
import oncall.domain.schedule.Assignment;
import oncall.domain.schedule.OnCallScheduler;
import oncall.domain.validation.NicknameValidator;
import oncall.domain.validation.RotationValidator;
import oncall.view.InputView;
import oncall.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class OnCallController {

    private final CalendarGenerator calendarGenerator = new CalendarGenerator();
    private final OnCallScheduler scheduler = new OnCallScheduler();

    public void run(){
        MonthInfo monthInfo = readMonthInfo();
        Workers workers = readWorkers();

        List<OnCallDate> dates = calendarGenerator.generate(monthInfo);

        Rotation weekdayRotation = new Rotation(workers.getWeekdayWorkers());
        Rotation holidayRotation = new Rotation(workers.getHolidayWorkers());

        List<Assignment> assignments =
                scheduler.schedule(dates, weekdayRotation, holidayRotation);

        OutputView.printAssignments(assignments);
    }

    private MonthInfo readMonthInfo() {
        while(true) {
            try {
                String input = InputView.readMonthAndStartDay();
                return parseMonthInfo(input);
            } catch (IllegalArgumentException e) {
                OutputView.printInvalidInputError();
            }
        }
    }

    private MonthInfo parseMonthInfo(String input) {
        if (input == null) {
            throw new IllegalArgumentException("입력이 null입니다.");
        }

        String trimmed = input.trim();
        String[] parts = trimmed.split(",");

        if (parts.length != 2) {
            throw new IllegalArgumentException("월과 요일 형식이 올바르지 않습니다.");
        }

        int month = parseMonth(parts[0]);
        DayOfWeekKorean startday = DayOfWeekKorean.from(parts[1]);

        return new MonthInfo(month, startday);
    }

    private int parseMonth(String text){
        String trimmed = text.trim();

        try {
            int month = Integer.parseInt(trimmed);
            return month;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("월은 숫자여야 합니다.");
        }
    }

    private Workers readWorkers() {
        while (true) {
            try {
                String weekdayInput = InputView.readWeekdayWorkers();
                String holidayInput = InputView.readHolidayWorkers();
                return createWorker(weekdayInput, holidayInput);
            } catch (IllegalArgumentException e) {
                OutputView.printInvalidInputError();
            }
        }
    }

    private Workers createWorker(String weekdayInput, String holidayInput){
        List<String> weekdayNames = parseNames(weekdayInput);
        List<String> holidayNames = parseNames(holidayInput);

        NicknameValidator.validate(weekdayNames);
        NicknameValidator.validate(holidayNames);
        RotationValidator.validate(weekdayNames, holidayNames);

        return new Workers(weekdayNames, holidayNames);
    }

    private List<String> parseNames(String input) {
        if(input == null) {
            throw new IllegalArgumentException("근무자 입력이 null입니다.");
        }

        String trimmed = input.trim();

        if(trimmed.isEmpty()) {
            throw new IllegalArgumentException("근무자 입력이 비어있습니다.");
        }

        String[] parts = trimmed.split(",");
        List<String> result = new ArrayList<>();

        for(String part : parts){
            String name = part.trim();
            if(!name.isEmpty()){
                result.add(name);
            }
        }
        return result;
    }

}
