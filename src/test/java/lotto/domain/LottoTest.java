package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoTest {

    @Test
    @DisplayName("로또 번호가 6개가 아닐 경우 예외 발생")
    void shouldThrowExceptionWhenNumbersSizeIsNot6() {
        List<Integer> invalidNumbers = Arrays.asList(1, 2, 3, 4, 5);
        assertThrows(IllegalArgumentException.class, () -> new Lotto(invalidNumbers));
    }

    @Test
    @DisplayName("중복된 숫자가 있을 경우 예외 발생")
    void shouldThrowExceptionWhenNumbersDuplicate() {
        List<Integer> invalidNumbers = Arrays.asList(1, 2, 3, 3, 4, 5);
        assertThrows(IllegalArgumentException.class, () -> new Lotto(invalidNumbers));
    }

    @Test
    @DisplayName("1~45 범위 밖의 숫자가 있으면 예외 발생")
    void shouldThrowExceptionWhenOutOfRange() {
        List<Integer> invalidNumbers = Arrays.asList(0, 2, 3, 4, 5, 6);
        assertThrows(IllegalArgumentException.class, () -> new Lotto(invalidNumbers));
    }

    @Test
    @DisplayName("로또 생성 시 자동 오름차순 정렬 확인")
    void shouldSortNumbersAscending() {
        Lotto lotto = new Lotto(Arrays.asList(45, 1, 9, 3, 10, 7));
        List<Integer> expected = Arrays.asList(1, 3, 7, 9, 10, 45);
        assertEquals(expected, lotto.getNumbers());
    }
}
