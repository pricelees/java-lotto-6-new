package lotto.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.mockito.Mockito.mockStatic;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import lotto.config.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

class LottoControllerTest {
    private PrintStream standardOut;
    private OutputStream captor;

    @BeforeEach
    void setUp() {
        standardOut = System.out;
        captor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(captor));
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
        System.out.println(captor.toString().trim());
        Console.close();
    }

    @DisplayName("게임의 정상 실행 확인")
    @Test
    void run_WithValidInput() {
        command("5000", "1,2,3,4,5,6", "7");
        runAfterSetPurchasedLotto(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(1, 2, 3, 4, 5, 7),
                List.of(1, 2, 3, 4, 5, 45),
                List.of(1, 2, 3, 4, 44, 45),
                List.of(1, 2, 3, 43, 44, 45)
        );
        assertThat(captor.toString()).contains(
                "5개를 구매했습니다.",
                "[1, 2, 3, 4, 5, 6]",
                "[1, 2, 3, 4, 5, 7]",
                "[1, 2, 3, 4, 5, 45]",
                "[1, 2, 3, 4, 44, 45]",
                "[1, 2, 3, 43, 44, 45]",
                "당첨 통계",
                "3개 일치 (5,000원) - 1개",
                "4개 일치 (50,000원) - 1개",
                "5개 일치 (1,500,000원) - 1개",
                "5개 일치, 보너스 볼 일치 (30,000,000원) - 1개",
                "6개 일치 (2,000,000,000원) - 1개",
                "총 수익률은 40631100.0%입니다."
        );
    }

    @DisplayName("로또 구입 금액에 대한 예외 발생시 반복 입력 확인")
    @Test
    void run_WithInvalidPurchaseAmount() {
        command("", "abc", "-1", "812", "5983");
        try {
            assertTimeoutPreemptively(Duration.ofSeconds(1L), this::run);
        } catch (NoSuchElementException ignored) {
        }
    }

    @DisplayName("로또 당첨 번호에 대한 예외 발생시 반복 입력 확인")
    @Test
    void run_WithInvalidWinningNumber() {
        // 금액은 정상적인 값을 입력
        command("1000", "abc", "1,2,3,4,5,46", "1:2:3:4:5:6", "1,2,2,3,4,5");
        try {
            assertTimeoutPreemptively(Duration.ofSeconds(1L), this::run);
        } catch (NoSuchElementException ignored) {
        }
    }

    @DisplayName("보너스 번호에 대한 예외 발생시 반복 입력 확인")
    @Test
    void run_WithInvalidBonusNumber() {
        // 금액과 당첨 번호는 정상적인 값을 입력
        command("1000", "1,2,3,4,5,6", "abc", "-1", "46", "1");
        try {
            assertTimeoutPreemptively(Duration.ofSeconds(1L), this::run);
        } catch (NoSuchElementException ignored) {
        }
    }


    @SafeVarargs
    private void runAfterSetPurchasedLotto(List<Integer> value, List<Integer>... values) {
        try (final MockedStatic<Randoms> mock = mockStatic(Randoms.class)) {
            mock.when(() -> Randoms.pickUniqueNumbersInRange(1, 45, 6))
                    .thenReturn(value, Arrays.stream(values).toArray());
            run();
        }
    }

    private void run() {
        Configuration.lottoController().run();
        Console.close();
    }

    private void command(final String... args) {
        final byte[] buf = String.join("\n", args).getBytes();
        System.setIn(new ByteArrayInputStream(buf));
    }
}