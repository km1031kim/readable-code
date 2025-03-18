package cleancode.studycafe.tobe.io.provider;

import java.util.List;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPasses;
import cleancode.studycafe.tobe.provider.SeatPassProvider;

class SeatPassFileReaderTest {

	SeatPassProvider seatPassProvider = new SeatPassFileReader();

	@DisplayName("StudyCafeSeatPasses 생성 시 기본 파일에서 선택 가능한 패스 옵션이 하나 이상 존재해야 한다.")
	@Test
	void defaultSeatPassFileIsAvailable() {
		// given
		StudyCafeSeatPasses seatPasses = seatPassProvider.getSeatPasses();
		int enablePassTypeOptionCount = 0;

		// when
		for (StudyCafePassType passType : StudyCafePassType.values()) {
			List<StudyCafeSeatPass> studyCafeSeatPasses = seatPasses.findPassBy(passType);
			enablePassTypeOptionCount += studyCafeSeatPasses.size();
		}

		// then
		Assertions.assertThat(enablePassTypeOptionCount).isGreaterThan(0);
	}

	@DisplayName("StudyCafeSeatPasses 생성 시, 주어진 파일을 읽을 수 없는 경우 예외를 발생시킨다.")
	@Test
	void seatPassFileIsAvailable() {
		// 파라미터가 있어야 테스트가 용이하다는걸 절실하게 느낀다.. 임시로 path 를 전달받은 메서드 생성.

		// given
		String path = UUID.randomUUID().toString();

		// then
		Assertions.assertThatThrownBy(() -> seatPassProvider.getSeatPassesFrom(path))
			.isInstanceOf(RuntimeException.class)
			.hasMessage("파일을 읽는데 실패했습니다.");
	}
}
