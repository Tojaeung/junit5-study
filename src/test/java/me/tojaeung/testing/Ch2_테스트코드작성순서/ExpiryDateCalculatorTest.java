package me.tojaeung.testing.Ch2_테스트코드작성순서;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class ExpiryDateCalculatorTest {
    // 중복제거
    private void assertExpiryDate(
            PayDate payDate, LocalDate expectedExpiryDate
    ) {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate realExpiryDate = cal.calculateExpiryDate(payDate);
        assertThat(expectedExpiryDate).isEqualTo(realExpiryDate);
    }

    @Test
    @DisplayName("만원을 납부하면 한달뒤가 만료일이 된다.")
    void pay_10000_won() {
        assertExpiryDate(
                PayDate
                        .builder()
                        .billingDate(LocalDate.of(2019, 5, 5))
                        .payAmount(10000)
                        .build()
                , LocalDate.of(2019, 6, 5));
    }

    @Test
    @DisplayName("첫 납부일과 만료일 일자가 다를때 만원납부")
    void pay_10000_won2() {

        assertExpiryDate(
                PayDate
                        .builder()
                        .firstBillingDate(LocalDate.of(2019, 1, 30))
                        .billingDate(LocalDate.of(2019, 2, 28))
                        .payAmount(10000)
                        .build()
                , LocalDate.of(2019, 3, 30));

        assertExpiryDate(
                PayDate
                        .builder()
                        .firstBillingDate(LocalDate.of(2019, 5, 31))
                        .billingDate(LocalDate.of(2019, 6, 30))
                        .payAmount(10000)
                        .build()
                , LocalDate.of(2019, 7, 31));


    }

    @Test
    @DisplayName("2만원이상 납부하면 비례해서 만료일 계산")
    void pay_20000_won() {
        // 2만원 납부할때
        assertExpiryDate(
                PayDate
                        .builder()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .payAmount(20000)
                        .build()
                , LocalDate.of(2019, 5, 1));

        // 3만원 납부할때
        assertExpiryDate(
                PayDate
                        .builder()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .payAmount(30000)
                        .build()
                , LocalDate.of(2019, 6, 1));
    }

    @Test
    @DisplayName("첫 납부일과 만료일 일자가 다를때 2만원 이상 납부할떄")
    void pay_20000_won2() {
        // 2만원 납부할때
        assertExpiryDate(
                PayDate
                        .builder()
                        .firstBillingDate(LocalDate.of(2019, 1, 31))
                        .billingDate(LocalDate.of(2019, 2, 28))
                        .payAmount(20000)
                        .build()
                , LocalDate.of(2019, 4, 30));
    }

    @Test
    @DisplayName("10만원을 납부하면 1년 제공")
    void pay_100000_won() {
        // 10만원 납부할때
        assertExpiryDate(
                PayDate
                        .builder()
                        .billingDate(LocalDate.of(2019, 1, 28))
                        .payAmount(100000)
                        .build()
                , LocalDate.of(2020, 1, 28));
    }


}
