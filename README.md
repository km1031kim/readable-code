# 리팩토링 과제

## 1. StudyCafePasses 일급 컬렉션 사용
```java
StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();
List<StudyCafePass> studyCafePasses = studyCafeFileHandler.readStudyCafePasses();
```
- `studyCafePasses`는 if문 분기마다 계속 만들어질 필요가 없다.
- **일급 컬렉션으로 변경**하여 컬렉션에서 타입에 맞게 반환받자.    
  
  
## 2. 전체 리스트 반환 개선
```java
List<StudyCafePass> studyCafePasses  = StudyCafePasses.getAllStudyCafePasses();
```
- 전체 리스트를 반환받을 필요 없음.
- `StudyCafePasses` 일급 컬렉션 클래스에서 `StudyCafePassType`을 전달받고, 해당 타입의 `StudyCafePass` 리스트를 반환하도록 변경.  


## 3. 타입별 StudyCafePass 리스트 반환
```java
List<StudyCafePass> passes = StudyCafePasses.getStudyCafePassesOf(studyCafePassType);
```
- 현재 if, else if로 타입을 체크할 때마다 반복되는 로직이 수행됨.
- **타입에 맞는 StudyCafePass 리스트를 받아와 사용자에게 목록을 보여주고 입력을 받음.**
- 사용자가 입력 가능한 목록을 보여주기 위해 해당 역할을 `StudyCafePasses` 일급 컬렉션에 위임.
- **if문으로 일일이 타입을 체크하지 않아도 됨.**
- 그러나 `FIXED` 타입의 경우 락커 선택이 필요함 → **이 책임을 어디에 둘 것인가?**  


## 4. PassHandler 인터페이스 도입
- 일급 컬렉션만으로 모든 것을 처리하기에는 한계가 있음.
- `PassHandler` 인터페이스를 만들고 `doHandle()`을 호출하며 타입을 전달.
- 어댑터를 찾아 핸들러 로직을 수행하는 방식으로 변경.
- `HourlyPassAdapter`와 `WeeklyPassAdapter`의 `doHandle()` 로직이 동일하지만, **추후 변경 가능성을 고려해 분리.**  


## 5. StudyCafeFileHandler 제거
- 기존 `StudyCafeFileHandler`는 더 이상 필요 없음.
- 다음으로 `doHandle()` 내부 로직을 검토.  


## 6. 할인 로직 책임 분리
- `StudyCafePass`가 할인율을 가지고 있음.
- `OutputHandler`가 `showPassOrderSummary()` 메서드 내에서 **할인 + 가격 계산 수행** → **SRP 위반**
- 파일을 읽어와 `StudyCafePass`를 생성하는 현재 로직에서 별도의 할인 정책을 만드는 것은 어려움.
- `StudyCafePricingService` 클래스를 생성하여 `StudyCafePass`를 파라미터로 전달받고 **할인 금액과 결제 금액을 계산**하도록 변경.  


## 7. in/out 핸들러 메서드 추상화
- 입출력 처리 로직을 별도로 추상화하여 코드 가독성을 높임.  


## 8. display() 메서드의 if문 수정
- `PassType`에 맞는 `String format`을 생성하는 것은 `StudyCafePass`의 역할이 아님.
- `StudyCafePassTypeShowable` 인터페이스를 생성하고 `StudyCafePassType` enum 클래스가 `show()` 메서드를 구현하도록 변경.  

### 변경 후
```java
public String display() {
    return passType.show(duration, price);
}
```
- `StudyCafePass`의 `display()` 메서드는 유효기간과 가격을 파라미터로 넘겨주고,
- **enum 타입에 따른 문자열 생성은 `StudyCafePassType`이 담당**
- **중첩 if문 제거** 및 **책임 분리** 완료.  

