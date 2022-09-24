package me.tojaeung.testing.Ch2_테스트코드작성순서;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {

    public LocalDate calculateExpiryDate(PayDate payDate) {
        int addedMonths = payDate.getPayAmount() == 100000 ? 12 : payDate.getPayAmount() / 10000;

        if (payDate.getFirstBillingDate() != null) {
            // 첫째일과 납부일의 일자가 다르면 첫 납부일의 일자를 만료일의 일자로 사용한다.
            LocalDate candidateExp = payDate.getBillingDate().plusMonths(addedMonths);
            if (payDate.getFirstBillingDate().getDayOfMonth() != candidateExp.getDayOfMonth()) {

                /*
                 * 원래 첫째날의 일자를 따라야하는데 그럴때 다음 달의 일수를 초과하는 경우 에러가 발생한다.
                 * 위의 예외 상황을 처리하기 위해 아래 코드를 추가 하였다.
                 * 조건: 후보달의 최대 일자보다 첫재날의 일자가 더 클때....
                 * */
                if (YearMonth.from(candidateExp).lengthOfMonth() < payDate.getFirstBillingDate().getDayOfMonth()) {
                    return candidateExp.withDayOfMonth(YearMonth.from(candidateExp).lengthOfMonth());
                }

                return candidateExp.withDayOfMonth(payDate.getFirstBillingDate().getDayOfMonth());
            }
        }


        return payDate.getBillingDate().plusMonths(addedMonths);
    }
}
