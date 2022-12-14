package me.tojaeung.testing.Ch1_Tdd시작하기;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaawordStrengthMeterTest {
    // 중복을 제거하기 위해 선언하여서 리팩토링하였다..
    private PaawordStrengthMeter meter = new PaawordStrengthMeter();

    // 중복을 제거하기 위해 선언하여서 리팩토링하였다..
    private void assertStrength(String password, PasswordStrength passwordStrength) {
        PasswordStrength result = meter.meter(password);
        assertEquals(passwordStrength, result);
    }

    @Test
    @DisplayName("모든 조건을 만족하는 경우")
    void meetsAllCriteria_Then_Strong() {
        // PaawordStrengthMeter meter = new PaawordStrengthMeter();
        // PasswordStrength result = meter.meter("ab12!@AB");
        // assertEquals(PasswordStrength.STRONG, result);

        assertStrength("ab12!@AB", PasswordStrength.STRONG);
    }

    @Test
    @DisplayName("비밀번호 길이가 8이하만 통과못하고 나머지는 모두 통과한 경우")
    void meetsOtherCriteria_except_for_Length_Then_Normal() {
        // PaawordStrengthMeter meter = new PaawordStrengthMeter();
        // PasswordStrength result = meter.meter("ab12!@A");
        // assertEquals(PasswordStrength.NORMAL, result);

        assertStrength("ab12!@A", PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("숫자를 포함하지 않는경우만 통과못하고 나머지는 모두 통과한 경우")
    void meetsOtherCriteria_except_for_number_Then_Normal() {
        // PaawordStrengthMeter meter = new PaawordStrengthMeter();
        // PasswordStrength result = meter.meter("ab!@Afewfwe");
        // assertEquals(PasswordStrength.NORMAL, result);

        assertStrength("ab!@Afewfwe", PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("입력값이 null인 경우만 통과못하고 나머지는 모두 통과한 경우")
    void nullInput_Then_Invalid() {
        assertStrength(null, PasswordStrength.INVALID);
    }

    @Test
    @DisplayName("입력값이 빈문자열 인 경우만 통과못하고 나머지는 모두 통과한 경우")
    void emptyInput_Then_Invalid() {
        assertStrength("", PasswordStrength.INVALID);
    }

    @Test
    @DisplayName("대문자를 포함하지 않는 경우만 통과못하고 나머지는 모두 통과한 경우")
    void meetsOtherCriteria_except_for_Uppercase_Then_Normal() {
        assertStrength("ab12!@df", PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("길이가 8이상인 경우만 통과한 경우")
    void meetOnlyLengthCriteria_Then_Weak() {
        assertStrength("wfehewuifhewi", PasswordStrength.WEAK);
    }

    @Test
    @DisplayName("숫자포함 조건만 통과한 경우")
    void meetOnlyNumberCriteria_Then_Weak() {
        assertStrength("12345", PasswordStrength.WEAK);
    }

    @Test
    @DisplayName("대문자포함 조건만 통과한 경우")
    void meetOnlyUpperCriteria_Then_Weak() {
        assertStrength("ABZBC", PasswordStrength.WEAK);
    }

    @Test
    @DisplayName("아무 조건도 만족하지 않는 경우")
    void meetNoCriteria_Then_Weak() {
        assertStrength("abc", PasswordStrength.WEAK);
    }


}
