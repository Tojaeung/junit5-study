package me.tojaeung.testing.Ch7_대역.자동이체;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 가짜대역 테스트
class MemoryAutoDebitInfoRepositoryTest {
    private AutoDebitRegister register;
    private StubCardNumberValidator cardNumberValidator;
    private MemoryAutoDebitInfoRepository repository;

    @BeforeEach
    void setUp() {
        cardNumberValidator = new StubCardNumberValidator();
        repository = new MemoryAutoDebitInfoRepository();
        register = new AutoDebitRegister(cardNumberValidator, repository);
    }

    @Test
    @DisplayName("이미 존재하는 유저는 입력받은 카드번호로 업데이트된다.")
    void alreadyRegistered_InfoUpdated() {
        repository.save(new AutoDebitInfo("user1", "111111111111", LocalDateTime.now()));
        AutoDebitReq req = new AutoDebitReq("user1", "12341234");
        RegisterResult result = this.register.register(req);

        AutoDebitInfo saved = repository.findOne("user1");
        assertEquals("12341234", saved.getCardNumber());

    }

    @Test
    @DisplayName("존재하지 않는 유저는 유저정보와 카드번호를 등록한다.")
    void notYetRegistered_newInfoRegistered() {
        AutoDebitReq req = new AutoDebitReq("user1", "12341234");
        RegisterResult result = this.register.register(req);

        AutoDebitInfo saved = repository.findOne("user1");
        assertEquals("12341234", saved.getCardNumber());
    }

}
