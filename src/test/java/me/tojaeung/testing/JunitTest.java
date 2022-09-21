package me.tojaeung.testing;

import org.junit.jupiter.api.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class JunitTest {
    @Test
    @DisplayName("스터디 만들기")
    void Assertion기본사용() {
        Junit study = new Junit(-10);
        assertNotNull(study);

        /*
         * 원래 첫번째 줄에서 에러가 나고 테스트가 끝나야하는데
         * assertAll은 끝나지 않고 모든 에러를 출력해준다.
         * */
        assertAll(
                /*
                 * 위의 코드가 람다식으로 간추려질수 있다
                 * 람다식을 사용해서 좋은점은 문자열 연산을 테스트에 실패할때만 한다.
                 * */
                () -> assertEquals(JunitStatus.Draft, study.getStatus(), "스터디의 상태값은 DRAFT여야 한다."),
                () -> assertEquals(JunitStatus.Draft, study.getStatus(), () -> "스터디의 상태값은" + JunitStatus.Draft + "여야 한다."),
                () -> assertTrue(study.getLimit() > 0, "스터디 최대 참석가능 인원은 0이상이다.")
        );

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Junit(-10));
        String message = exception.getMessage();
        assertEquals("limit은 0보다 커야한다", message, "예외처리 메세지가 같아야한다.");

        // 시간경과되면 에러 출력
        assertTimeout(Duration.ofMillis(100), () -> {
            new Junit(10);
            Thread.sleep(300);
        });
    }

    @Test
    @Disabled
    void create1() {
        Junit study = new Junit(-10);
        assertNotNull(study);
        System.out.println("create1");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("before All");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("after All");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("before Each");
    }

    @AfterEach
    void afterEach() {
        System.out.println("after Each");
    }
}
