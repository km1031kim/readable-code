package cleancode.studycafe.tobe.model.pass.locker;


import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;

class StudyCafeLockerPassesTest {

	@DisplayName("StudyCafeSeatPass와 동일한 패스타입 및 대여기간을 가진 StudyCafeLockerPass가 존재하는지 검증")
	@Test
	void findLockerPassBySeatPass() {
	    // given
		StudyCafeSeatPass studyCafeSeatPass = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 6, 20000, 0);

		StudyCafeLockerPass studyCafeLockerPass1 = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 4, 20000);
		StudyCafeLockerPass studyCafeLockerPass2 = StudyCafeLockerPass.of(StudyCafePassType.WEEKLY, 6, 40000);
		StudyCafeLockerPasses studyCafeLockerPasses = StudyCafeLockerPasses.of(
			List.of(studyCafeLockerPass1, studyCafeLockerPass2));

		// when
		Optional<StudyCafeLockerPass> lockerPassBy = studyCafeLockerPasses.findLockerPassBy(studyCafeSeatPass);

		// then
		Assertions.assertThat(lockerPassBy).isPresent();
	}

}
