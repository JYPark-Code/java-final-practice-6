package oncall;

import camp.nextstep.edu.missionutils.Console;
import oncall.controller.OnCallController;

public class Application {

    public static void main(String[] args) {

        try {
            OnCallController controller = new OnCallController();
            controller.run();
        } finally {
            Console.close();
        }
    }

}
