package cleancode.studycafe.tobe.model.pass;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StudyCafeSeatPassTest {

	@DisplayName("주어진 패스타입에 대한 일치 여부를 확인한다.")
	@Test
	void checkPassTypeIsSame() {

		// given
		StudyCafeSeatPass studyCafeSeatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 2, 2000, 0);

		// when
		boolean isSamePassType = studyCafeSeatPass.isSamePassType(StudyCafePassType.FIXED);
		boolean isDifferentPassType = studyCafeSeatPass.isSamePassType(StudyCafePassType.HOURLY);

		// then
		Assertions.assertThat(isSamePassType).isTrue();
		Assertions.assertThat(isDifferentPassType).isFalse();
	}
}
