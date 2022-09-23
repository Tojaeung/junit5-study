package me.tojaeung.testing.Ch1_Tdd시작하기;

public class PaawordStrengthMeter {
    public PasswordStrength meter(String s) {
        // 입력값이 null인 경우, 빈문자열인 경우
        if (s == null && s.isEmpty()) return PasswordStrength.INVALID;

        // 비밀번호가 8보다 작으면 예외
        if (s.length() < 8) {
            return PasswordStrength.NORMAL;
        }

        // 비밀번호가 숫자를 포함하는 경우
        if (!meetsContaingNumberCriteria(s)) return PasswordStrength.NORMAL;
        if (!meetsContaingUppercaseCriteria(s)) return PasswordStrength.NORMAL;

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

    // 가독성을 위해 대문자가 포함되었는지 확인하는 메서드 리팩토링
    private boolean meetsContaingUppercaseCriteria(String s) {
        for (char ch : s.toCharArray()) {
            if (Character.isUpperCase(ch)) return true;
        }
        return false;
    }
}
