package cleancode.studycafe.asis.passes;

import cleancode.studycafe.asis.model.StudyCafeLockerPass;
import cleancode.studycafe.asis.model.StudyCafePass;
import cleancode.studycafe.asis.model.StudyCafePassType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StudyCafePasses {

    /**
     * 역할 : csv 파일을 읽고, studyCafePasses 를 만들어서 가지고 있다.
     * StudyCafePassType 에 따른 리스트를 별도로 반환할 수 있다.
     */

    private static final List<StudyCafePass> studyCafePasses = readStudyCafePasses();

    private StudyCafePasses() {
    }

    public static List<StudyCafePass> getStudyCafePassesOf(StudyCafePassType studyCafePassType) {
        return studyCafePasses.stream()
            .filter(studyCafePass -> studyCafePass.getPassType() == studyCafePassType)
            .toList();
    }



    private static List<StudyCafePass> readStudyCafePasses() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/resources/cleancode/studycafe/pass-list.csv"));
            List<StudyCafePass> studyCafePasses = new ArrayList<>();
            for (String line : lines) {
                String[] values = line.split(",");
                StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[0]);
                int duration = Integer.parseInt(values[1]);
                int price = Integer.parseInt(values[2]);
                double discountRate = Double.parseDouble(values[3]);

                StudyCafePass studyCafePass = StudyCafePass.of(studyCafePassType, duration, price, discountRate);
                studyCafePasses.add(studyCafePass);
            }

            return studyCafePasses;
        } catch (IOException e) {
            throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
        }
    }

}
