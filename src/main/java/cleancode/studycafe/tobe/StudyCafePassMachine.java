package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.io.StudyCafeFileHandler;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.List;
import java.util.Optional;

public class StudyCafePassMachine {

    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();
    private final StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();


    public void run() {
        try {
            outputHandler.showWelcomeMessage();
            outputHandler.showAnnouncement();


            StudyCafePass selectedPass = selectPass();
            Optional<StudyCafeLockerPass> optionalLockerPass = selectLockerPass(selectedPass);

            optionalLockerPass.ifPresentOrElse(
                lockerPass -> outputHandler.showPassOrderSummary(selectedPass, lockerPass),
                // () -> outputHandler.showPassOrderSummary(selectedPass, null)
                () -> outputHandler.showPassOrderSummary(selectedPass)
            );

        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private StudyCafePass selectPass() {
        outputHandler.askPassTypeSelection();
        StudyCafePassType passType = inputHandler.getPassTypeSelectingUserAction();

        List<StudyCafePass> passCandidates = findPassCandidatesBy(passType);

        outputHandler.showPassListForSelection(passCandidates);
        return inputHandler.getSelectPass(passCandidates);
    }

    private List<StudyCafePass> findPassCandidatesBy(StudyCafePassType passType) {
        List<StudyCafePass> allPasses = studyCafeFileHandler.readStudyCafePasses();
        return allPasses.stream()
            .filter(studyCafePass -> studyCafePass.isSamePassType(passType))
            .toList();
    }

    private Optional<StudyCafeLockerPass> selectLockerPass(StudyCafePass selectedPass) {

        // 고정 좌석 타입이 아닌가?
        // -> 사물함 옵션을 사용할 수 있는 타입이 아닌가?
        // 고정 좌석은 낮은 추상화, 높은 추상화는? 사물함을 사용할 수 있는 타입인지.
        if (selectedPass.cannotUserLocker()) {
            return Optional.empty();
        }

        StudyCafeLockerPass lockerPassCandidate = findLockerPassCandidateBy(selectedPass);

        if (lockerPassCandidate != null) {
            outputHandler.askLockerPass(lockerPassCandidate);
            boolean isLockerSelected = inputHandler.getLockerSelection();

            if (isLockerSelected) {
                return Optional.of(lockerPassCandidate);
            }
        }
        return Optional.empty();
    }

    private StudyCafeLockerPass findLockerPassCandidateBy(StudyCafePass pass) {
        List<StudyCafeLockerPass> allLockerPasses = studyCafeFileHandler.readLockerPasses();
        return allLockerPasses.stream()
            .filter(pass::isSameDurationType)
            .findFirst()
            .orElse(null);
    }

}
