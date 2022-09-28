package me.tojaeung.testing.Ch7_대역.회원가입;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserRegisterMockTest {
    private UserRegister userRegister;
    private WeakPasswordChecker mockPasswordChecker = Mockito.mock(WeakPasswordChecker.class);
    private MemoryUserRepository fakeRepository = new MemoryUserRepository();
    private EmailNotifier mockEmailNotifier = Mockito.mock(EmailNotifier.class);

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(mockPasswordChecker, fakeRepository, mockEmailNotifier);
    }

    @Test
    @DisplayName("약한암호라면 가입실패")
    void weakPassword() {
        // StubWeakPasswordChecker 대역의 일을 대신하고 있다.
        BDDMockito.given(mockPasswordChecker.checkPasswordWeak("pw")).willReturn(true);

        assertThrows(WeakPasswordException.class, () -> {
            userRegister.register("id", "pw", "email");
        });
    }

    @Test
    @DisplayName("회원가입시 암호검사 수행한다.")
    void checkPassword() {
        userRegister.register("id", "pw", "email");

        // 위의 코드가 실행될떄 메서드 호출여부를 확인한다.
        BDDMockito.then(mockPasswordChecker)
                .should()
                // 문자열로된 아무 String을 인자로 가지는 checkPasswordWeak이 호출되었는지 여부를 확인한다. 
                .checkPasswordWeak(BDDMockito.anyString());
    }

    @Test
    @DisplayName("회원가입하면 메일을 전송함")
    void whenRegisterThenSendMail() {
        userRegister.register("id", "pw", "email@email.com");
        // 모의 객체의 메서드를 호출할때 전달된 인자를 담는 기능을 한다.
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        BDDMockito.then(mockEmailNotifier)
                // sendRegisterEmail 메서드가 호출될때의 인자를 captor에 담는다.
                .should().sendRegisterEmail(captor.capture());

        String realMail = captor.getValue();
        assertEquals("email@email.com", realMail);
    }
    
}
