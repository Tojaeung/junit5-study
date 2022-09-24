package me.tojaeung.testing.Ch2_테스트코드작성순서;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PayDate {
    private LocalDate firstBillingDate;
    private LocalDate billingDate;
    private int payAmount;
}
