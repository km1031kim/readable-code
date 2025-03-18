package cleancode.studycafe.tobe.model.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;

class StudyCafePassOrderTest {

	@DisplayName("락커패스가 존재하지 않을 경우 시트패스 가격이 최종 가격이 된다.")
	@Test
	void getTotalPriceWithoutLockerPass() {
		// given
		StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 2, 4000, 0);

		// when
		StudyCafePassOrder passOrder = StudyCafePassOrder.of(seatPass, null);

		// then
		Assertions.assertThat(passOrder.getTotalPrice()).isEqualTo(4000);
	}

	@DisplayName("시트패스와 락커패스 모두 존재하는 경우 총 합이 최종 가격이 된다.")
	@Test
	void getTotalPriceFromSeatPassAndLockerPass() {

		// given
		StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 2, 4000, 0.1);
		StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafePassType.HOURLY, 2, 4000);

		// when
		StudyCafePassOrder passOrder = StudyCafePassOrder.of(seatPass, lockerPass);

		// then
		Assertions.assertThat(passOrder.getTotalPrice()).isEqualTo(7600);
	}
}
