package cleancode.studycafe.tobe;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;

class StudyCafePassMachineTest {

	@Test
	@DisplayName("락커 구매는 지정된 타입만 가능하다.")
	void checkLockerPassIsOnlyAvailableForSpecifiedType() {

		// given
		StudyCafePassType studyCafePassType = StudyCafePassType.WEEKLY;
		StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(studyCafePassType, 2, 4000, 0);

		// when
		boolean cannotUseLocker = seatPass.cannotUseLocker();
		boolean isEnableTypeToBuyLocker = studyCafePassType.isLockerType();

		// then
		Assertions.assertThat(cannotUseLocker)
			.as("락커를 사용할 수 없는 패스는 락커구매가능 타입에 속하지 않는다.")
			.isEqualTo(!isEnableTypeToBuyLocker);
	}
}
