package cleancode.studycafe.asis;

import cleancode.studycafe.asis.exception.AppException;
import cleancode.studycafe.asis.handler.StudyCafePassHandler;
import cleancode.studycafe.asis.io.InputHandler;
import cleancode.studycafe.asis.io.OutputHandler;
import cleancode.studycafe.asis.model.StudyCafePassType;

public class StudyCafePassMachine {

    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();
    private final StudyCafePassHandler studyCafePassHandler = new StudyCafePassHandler();

    public void run() {
        try {
            outputHandler.showWelcomeMessage();
            outputHandler.showAnnouncement();
            outputHandler.askPassTypeSelection();

            StudyCafePassType studyCafePassType = inputHandler.getPassTypeSelectingUserAction();
            studyCafePassHandler.handle(studyCafePassType);

        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

}
