package oncall.domain.rotation;

import java.util.ArrayList;
import java.util.List;

public class Rotation {
    
    private final List<String> workers;
    private int index = 0;

    public Rotation(List<String> workers) {
        validate(workers);
        this.workers = new ArrayList<>(workers);
    }

    private void validate(List<String> workers) {
        if (workers == null || workers.isEmpty()) {
            throw new IllegalArgumentException("근무자 목록은 비어 있을 수 없습니다.");
        }
    }

    public String peek() {
        return workers.get(index);
    }

    public String next() {
        String current = workers.get(index);
        moveNext();
        return current;
    }

    public void swapNext() {
        int nextIndex = nextIndex();
        String currentWorker = workers.get(index);
        String nextWorker = workers.get(nextIndex);

        workers.set(index, nextWorker);
        workers.set(nextIndex, currentWorker);

    }

    private void moveNext() {
        index = nextIndex();
    }

    private int nextIndex(){
        int next = index + 1;

        if (next >= workers.size()) {
            return 0;
        }

        return next;
    }
    

}
