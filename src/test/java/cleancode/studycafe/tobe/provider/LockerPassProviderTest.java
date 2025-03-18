package cleancode.studycafe.tobe.provider;

import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import cleancode.studycafe.tobe.io.provider.LockerPassFileReader;

class LockerPassProviderTest {

	LockerPassProvider lockerPassProvider = new LockerPassFileReader();

	@DisplayName("StudyCafeLockerPasses 생성 시, 주어진 파일을 읽을 수 없는 경우 예외를 발생시킨다.")
	@Test
	void defaultLockerPassFileIsAvailable() {
		// 파라미터가 있어야 테스트가 용이하다는걸 절실하게 느낀다.. 임시로 path 를 전달받은 메서드 생성.

		// given
		String path = UUID.randomUUID().toString();

		// then
		Assertions.assertThatThrownBy(() -> lockerPassProvider.getLockerPassesFrom(path))
			.isInstanceOf(RuntimeException.class)
			.hasMessage("파일을 읽는데 실패했습니다.");
	}
}

