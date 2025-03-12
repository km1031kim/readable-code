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

    /**
         생각 적기

         1. StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();
            List<StudyCafePass> studyCafePasses = studyCafeFileHandler.readStudyCafePasses();
            studyCafePasses 는 if문 분기마다 계속 만들어질 필요가 없다. 그러면? 일급 컬렉션으로 만들어보자.
            그리고 컬렉션에서 타입에 맞게 반환받자.

         2. 아래와 같이 변경했다.
            List<StudyCafePass> studyCafePasses  = StudyCafePasses.getAllStudyCafePasses();
            StudyCafePasses.getAllStudyCafePasses();

            위와 같이 전체 리스트를 반환받을 필요는 없다.
            StudyCafePasses 일급 컬렉션 클래스에서 StudyCafePassType 을 전달받고, 해당되는 타입에 맞는 StudyCafePass 리스트를 반환해주면 되지 않을까?

         3. 아래와 같이 변경해보았다.
            List<StudyCafePass> passes = StudyCafePasses.getStudyCafePassesOf(studyCafePassType);

            현재 if, else if 로 타입을 체크할때마다 반복되는 로직이 수행된다.
            로직의 내용은 타입에 맞는 StudyCafePass 리스트를 받아와서 사용자에게 StudyCafePass 목록을 보여주고, 사용자로부터 입력을 선택받고, 총 가격을 보여준다.
            사용자가 입력 가능한 목록을 보여주기 위해 타입에 맞는 StudyCafePass 리스트를 제공받아야 하는데 이 역할을 StudyCafePasses 일급 컬렉션에 위임했다.
            이렇게 하면 if 문으로 일일히 타입 체크를 하지 않아도 된다.
            그런데 타입이 FIXED 인 경우 별도의 로직이 필요하다. 락커도 선택해야한다. 이건 어디에 책임을 두어야 할까?

         4. 일급 컬렉션으로 모든 걸 처리하는데는 무리가 있는 듯 하다. PassHandler 인터페이스를 만들고 거기서 doHandle() 을 호출하면서 타입을 전달해보면 어떨까?
            어댑터를 찾고, 핸들러 로직을 수행하는 방향으로 변경해보자.
            HourlyPassAdapter 와 WeeklyPassAdapter 의 doHandle() 로직이 현재 동일하지만, 추후 변경 가능성이 있다고 판단하여 분리하여 두도록 했다.

         5. 이제 StudyCafeFileHandler 는 필요없다. 다음으로 doHandle() 내부 로직 각각을 살펴보자.

         6. 어울리지 않는 부분이 있다.
            StudyCafePass 가 할인율을 가지고 있음.
            OutputHandler 가 showPassOrderSummary 메서드 내에서 할인 + 가격 계산을 수행한다.
            SRP 원칙에 위반된다.

            파일을 읽어와서 행 별로 StudyCafePass 만드는 현재 로직에서 별도의 할인 정책을 만드는 건 어려울 듯 했다.
            그래서 StudyCafePricingService 클래스를 생성, 해당 클래스에서 StudyCafePass 를 파라미터로 전달받고 할인금액과 결제금액 계산하여 반환한다.

         7. in/out 핸들러 메서드 추상화.

         8. display() 메서드의 if 문 수정.
            PassType 에 맞게 String format 을 생성하는 건 StudyCafePass 클래스의 역할이 아니라고 본다.
            StudyCafePassTypeShowable 인터페이스를 생성, StudyCafePassType enum 클래스가 show() 메서드를 구현하도록 했다.
            이로써 중첩 if 문을 StudyCafePass 에서 사용하지 않아도 되고,
            아래와 같은 코드로 StudyCafePass 타입에 맞는 문자열을 출력할 수 있다.

             public String display() {
                return passType.show(duration, price);
             }

            StudyCafePass 의 display 메서드는 해당 클래스의 정보인 유효기간, 가격만 파라미터로 넘겨주고, enum 타입에 따른 구체적인 문자열을 생성하는 건 StudyCafePassType 의 역할이다.

     */
    }
}
