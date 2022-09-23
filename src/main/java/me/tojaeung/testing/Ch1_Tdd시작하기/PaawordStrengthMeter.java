package me.tojaeung.testing.Ch1_Tdd시작하기;

public class PaawordStrengthMeter {
    public PasswordStrength meter(String s) {
        // 입력값이 null인 경우, 빈문자열인 경우
        if (s == null || s.isEmpty()) return PasswordStrength.INVALID;

        /*
         * 이것이 리팩토링이다...
         * 각 조건을 만족하는 개수에따라 비밀번호 강도가 결정되는 규칙을 잘 표현하고 있다.
         * */
        int cnt = 0;    // 각 조건이 충족된다면 증가한다.
        if (s.length() >= 8) cnt++;
        if (meetsContaingNumberCriteria(s)) cnt++;
        if (meetsContaingUppercaseCriteria(s)) cnt++;

        if (cnt <= 1) return PasswordStrength.WEAK;     // 하나의 조건만 맞으면 강도 약함
        if (cnt == 2) return PasswordStrength.NORMAL;   // 두개의 조건만 맞으면 강도 중간
        return PasswordStrength.STRONG;   // 모든조건 맞으면 강도 강함


        // boolean lengthEnough = s.length() >= 8; // 입력값이 8 이상인가 여부
        // boolean containsNumber = meetsContaingNumberCriteria(s); // 입력값이 숫자가 있는지 여부
        // boolean containsUpper = meetsContaingUppercaseCriteria(s);  // 입력값이 대문자가 있는지 여부
        //
        // // 한가지만 통과하고 나머지는 통과 못하는 경우
        // if (lengthEnough && !containsNumber && !containsUpper) return PasswordStrength.WEAK;
        // if (!lengthEnough && containsNumber && !containsUpper) return PasswordStrength.WEAK;
        // if (!lengthEnough && !containsNumber && containsUpper) return PasswordStrength.WEAK;
        //
        // // 한가지만 통과못하고 나머지는 통과하는 경우
        // if (!lengthEnough) return PasswordStrength.NORMAL;
        // if (!containsNumber) return PasswordStrength.NORMAL;
        // if (!containsUpper) return PasswordStrength.NORMAL;

        // // 비밀번호가 8보다 작으면 예외
        // if (s.length() < 8) {
        //     return PasswordStrength.NORMAL;
        // }
        //
        // // 비밀번호가 숫자를 포함하는 경우
        // if (!meetsContaingNumberCriteria(s)) return PasswordStrength.NORMAL;
        // if (!meetsContaingUppercaseCriteria(s)) return PasswordStrength.NORMAL;


        // 모든 조건을 만족하는 경우
        // return PasswordStrength.STRONG;
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
