package me.tojaeung.testing.Ch1_Tdd시작하기;

public class PaawordStrengthMeter {
    public PasswordStrength meter(String s) {
        // 비밀번호가 8보다 작으면 예외
        if (s.length() < 8) {
            return PasswordStrength.NORMAL;
        }

        // 비밀번호가 숫자를 포함하는 경우
        if (!meetsContaingNumberCriteria(s)) return PasswordStrength.NORMAL;

        return PasswordStrength.STRONG;
    }

    // 가독성을 위해 숫자가 포함되었는지 확인하는 메서드 리팩토링
    private boolean meetsContaingNumberCriteria(String s) {
        for (char ch : s.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                return true;
            }
        }
        return false;
    }
}
