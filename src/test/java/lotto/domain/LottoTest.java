package lotto.domain;

import lotto.exception.InvalidInputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class LottoTest {

    @Test
    @DisplayName("로또 번호가 6개가 아니면 예외 발생")
    void shouldThrowExceptionWhenNotSixNumbers() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(InvalidInputException.class)
                .hasMessageContaining("로또 번호는 6개여야");
    }

    @Test
    @DisplayName("로또 번호에 중복이 있으면 예외 발생")
    void shouldThrowExceptionWhenDuplicateNumbers() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 3, 4, 5)))
                .isInstanceOf(InvalidInputException.class)
                .hasMessageContaining("중복");
    }

    @Test
    @DisplayName("로또 번호가 1~45 범위를 벗어나면 예외 발생")
    void shouldThrowExceptionWhenNumberOutOfRange() {
        assertThatThrownBy(() -> new Lotto(List.of(0, 2, 3, 4, 5, 6)))
                .isInstanceOf(InvalidInputException.class)
                .hasMessageContaining("1부터 45");
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 100)))
                .isInstanceOf(InvalidInputException.class)
                .hasMessageContaining("1부터 45");
    }

    @Test
    @DisplayName("정상적인 로또 번호는 정렬되어 저장된다")
    void shouldStoreSortedNumbers() {
        Lotto lotto = new Lotto(List.of(6, 1, 45, 20, 9, 3));
        assertThat(lotto.getNumbers()).containsExactly(1, 3, 6, 9, 20, 45);
    }

    @Test
    @DisplayName("보너스 번호 포함 여부 확인")
    void shouldCheckContainsNumber() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        assertThat(lotto.containsNumber(3)).isTrue();
        assertThat(lotto.containsNumber(10)).isFalse();
    }

    @Test
    @DisplayName("두 로또 간 일치 번호 개수 계산")
    void shouldCountMatchingNumbersCorrectly() {
        Lotto myLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto winningLotto = new Lotto(List.of(4, 5, 6, 7, 8, 9));

        int matchCount = myLotto.countMatchingNumbers(winningLotto);

        assertThat(matchCount).isEqualTo(3);
    }

    @Test
    @DisplayName("로또 번호는 외부에서 수정할 수 없다")
    void shouldBeImmutable() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        assertThatThrownBy(() -> lotto.getNumbers().add(7))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
