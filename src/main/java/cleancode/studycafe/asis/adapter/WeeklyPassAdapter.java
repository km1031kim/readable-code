package cleancode.studycafe.asis.adapter;

import cleancode.studycafe.asis.passes.StudyCafePasses;
import cleancode.studycafe.asis.model.StudyCafePass;
import cleancode.studycafe.asis.model.StudyCafePassType;

import java.util.List;

public class WeeklyPassAdapter implements StudyCafePassAdapter {

    @Override
    public boolean supports(StudyCafePassType studyCafePassType) {
        return studyCafePassType == StudyCafePassType.WEEKLY;
    }

    @Override
    public void doHandle(StudyCafePassType studyCafePassType) {
        List<StudyCafePass> passes = StudyCafePasses.getStudyCafePassesOf(studyCafePassType);
        outputHandler.showPassListForSelection(passes);
        StudyCafePass selectedPass = inputHandler.getSelectPass(passes);
        outputHandler.showPassOrderSummary(selectedPass, null);
    }
}
