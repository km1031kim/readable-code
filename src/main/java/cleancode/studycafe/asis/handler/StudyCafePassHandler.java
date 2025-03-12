package cleancode.studycafe.asis.handler;

import cleancode.studycafe.asis.adapter.FixedPassAdapter;
import cleancode.studycafe.asis.adapter.HourlyPassAdapter;
import cleancode.studycafe.asis.adapter.StudyCafePassAdapter;
import cleancode.studycafe.asis.adapter.WeeklyPassAdapter;
import cleancode.studycafe.asis.model.StudyCafePassType;

import java.util.List;

public class StudyCafePassHandler {

    private final List<StudyCafePassAdapter> studyCafePassAdapters =
            List.of(new HourlyPassAdapter(),
                    new WeeklyPassAdapter(),
                    new FixedPassAdapter());

    public void handle(StudyCafePassType studyCafePassType) {

        StudyCafePassAdapter studyCafePassAdapter = studyCafePassAdapters.stream()
                .filter(adapter -> adapter.supports(studyCafePassType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 회원권 종류입니다."));

        studyCafePassAdapter.doHandle(studyCafePassType);
    }
}
