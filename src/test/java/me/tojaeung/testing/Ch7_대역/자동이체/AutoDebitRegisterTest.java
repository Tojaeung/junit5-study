package me.tojaeung.testing.Ch7_대역.자동이체;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static me.tojaeung.testing.Ch7_대역.자동이체.CardValidity.INVALID;
import static me.tojaeung.testing.Ch7_대역.자동이체.CardValidity.THEFT;
import static org.junit.jupiter.api.Assertions.assertEquals;

// 스텁대역 테스트
class AutoDebitRegisterTest {
    private AutoDebitRegister register;
    private StubCardNumberValidator stubValidator;
    private StubAutoDebitInfoRepository stubRepository;
    private MemoryAutoDebitInfoRepository repository;

    @BeforeEach
    void setUp() {
        stubValidator = new StubCardNumberValidator();
        stubRepository = new StubAutoDebitInfoRepository();
        register = new AutoDebitRegister(stubValidator, stubRepository);

        // 외부 api에 의존하고 있는 테스트 코드
        // CardNumberValidator validator = new CardNumberValidator();
        // AutoDebitInfoRepository repository = new JpaAutoDebitInfoRepository();
        // register = new AutoDebitRegister(validator, repository);
    }

    @Test
    void invalidCard() {
        // 대역을 사용해서 외부api에 의존하지 않고 외부 api 환경과 비슷한 상황을 구현하여 테스트코드를 작성하였다.
        stubValidator.setInvalidNo("12341234");

        AutoDebitReq req = new AutoDebitReq("user1", "12341234");
        RegisterResult result = register.register(req);
        assertEquals(INVALID, result.getValidity());

        // 자신이 컨트롤 할수 없는 외부 api에 의존하고 있기 때문에 테스트 코드가 불안정하다.
        // AutoDebitReq req = new AutoDebitReq("user1", "12341234");
        // RegisterResult result = this.register.register(req);
        // assertEquals(VALID, result.getValidity());

    }

    @Test
    void theftCard() {
        stubValidator.setTheftNo("1111");

        AutoDebitReq req = new AutoDebitReq("user2", "1111");
        RegisterResult result = this.register.register(req);
        assertEquals(THEFT, result.getValidity());
    }


}
