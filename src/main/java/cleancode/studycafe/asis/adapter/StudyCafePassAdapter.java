package cleancode.studycafe.asis.adapter;

import cleancode.studycafe.asis.io.InputHandler;
import cleancode.studycafe.asis.io.OutputHandler;
import cleancode.studycafe.asis.model.StudyCafePassType;

public interface StudyCafePassAdapter {

    InputHandler inputHandler = new InputHandler();
    OutputHandler outputHandler = new OutputHandler();

    boolean supports(StudyCafePassType studyCafePassType);

    void doHandle(StudyCafePassType studyCafePassType);

}
