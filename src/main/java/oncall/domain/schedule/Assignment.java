package oncall.domain.schedule;

import oncall.domain.calendar.OnCallDate;

// 어느 날에 누가 근무하는가? (배정)
public class Assignment {

    private final OnCallDate date;
    private final String worker;

    public Assignment(OnCallDate date, String worker) {

        if(date == null) {
            throw new IllegalArgumentException("날짜는 필수 항목입니다.");
        }

        if(worker == null || worker.isBlank()){
            throw new IllegalArgumentException("근무자는 필수 항목입니다.");
        }

        this.date = date;
        this.worker = worker;
    }

    public OnCallDate getDate() {
        return date;
    }

    public String getWorker() {
        return worker;
    }
}
