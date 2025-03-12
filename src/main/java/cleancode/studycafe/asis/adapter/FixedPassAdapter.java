package cleancode.studycafe.asis.adapter;

import cleancode.studycafe.asis.passes.StudyCafeLockerPasses;
import cleancode.studycafe.asis.passes.StudyCafePasses;
import cleancode.studycafe.asis.model.StudyCafeLockerPass;
import cleancode.studycafe.asis.model.StudyCafePass;
import cleancode.studycafe.asis.model.StudyCafePassType;

import java.util.List;

public class FixedPassAdapter implements StudyCafePassAdapter {

    @Override
    public boolean supports(StudyCafePassType studyCafePassType) {
        return studyCafePassType == StudyCafePassType.FIXED;
    }

    @Override
    public void doHandle(StudyCafePassType studyCafePassType) {

        List<StudyCafePass> passes = StudyCafePasses.getStudyCafePassesOf(studyCafePassType);
        outputHandler.showPassListForSelection(passes);
        StudyCafePass selectedPass = inputHandler.getSelectPass(passes);

        StudyCafeLockerPass lockerPass = StudyCafeLockerPasses.getStudyCafeLockerPassOf(selectedPass);

        boolean lockerSelection = false;

        if (lockerPass != null) {
            outputHandler.askLockerPass(lockerPass);
            lockerSelection = inputHandler.getLockerSelection();
        }

        if (lockerSelection) {
            outputHandler.showPassOrderSummary(selectedPass, lockerPass);
        } else {
            outputHandler.showPassOrderSummary(selectedPass, null);
        }
    }
}
