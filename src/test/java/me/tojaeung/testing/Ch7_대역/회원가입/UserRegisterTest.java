package me.tojaeung.testing.Ch7_대역.회원가입;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRegisterTest {
    private UserRegister userRegister;
    private StubWeakPasswordChecker stubWeakPasswordChecker = new StubWeakPasswordChecker();
    private MemoryUserRepository fakeRepository = new MemoryUserRepository();
    private SpyEmailNotifier spyEmailNotifier = new SpyEmailNotifier();

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(stubWeakPasswordChecker, fakeRepository, spyEmailNotifier);
    }

    @Test
    @DisplayName("약한 암호면 가입실패")
    void weakPassword() {
        // 암호가 약하다고 응답하도록 설정
        stubWeakPasswordChecker.setWeak(true);

        assertThrows(WeakPasswordException.class, () -> {
            userRegister.register("id", "pw", "email");
        });
    }

    @Test
    @DisplayName("아이디가 이미 존재하면 가입실패")
    void dupIdExists() {
        // 이미 같은 아이디가 존재하는 상황만들기
        fakeRepository.save(new User("id", "pw", "email@email.com"));

        assertThrows(DupIdException.class, () -> {
            userRegister.register("id", "pw", "email");
        });
    }

    @Test
    @DisplayName("아이디가 존재하지않으면 가입성공")
    void noDupId_RegisterSuccess() {
        userRegister.register("id", "pw", "email");
        User savedUser = fakeRepository.findById("id");
        assertEquals("id", savedUser.getId());
        assertEquals("email", savedUser.getEmail());
    }

    @Test
    @DisplayName("가입하면 메일을 전송한다.")
    void whenRegisterThenSendMail() {
        // 회원가입을 하면...
        userRegister.register("id", "pw", "email@email.com");
  
        // 이메일이 전송됐다는것을 테스트하는 코드이다.
        assertTrue(spyEmailNotifier.isCalled());
        assertEquals("email@email.com", spyEmailNotifier.getEmail());
    }
}
