package hello.hellospring;

import hello.hellospring.dto.LoginRequestDto;
import hello.hellospring.entity.User;
import hello.hellospring.repository.UserRepository;
import hello.hellospring.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserLoginTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private HttpServletResponse response;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserService userService;

    @Test
    public void testLoginSuccess() {
        // 가짜 사용자 데이터 생성
        String email = "test@example.com";
        String password = "password123";

        // 사용자 Repository에 가짜 데이터 저장
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(getUser(email, password)));

        // PasswordEncoder 모의 객체 설정
        when(passwordEncoder.matches(password, password)).thenReturn(true);

        // 로그인 요청 생성
        LoginRequestDto loginRequestDto = new LoginRequestDto();
        loginRequestDto.setEmail(email);
        loginRequestDto.setPassword(password);

        // 로그인 시도
        assertDoesNotThrow(() -> userService.login(loginRequestDto, response));
    }


    @Test
    public void testLoginFailure_WrongPassword() {
        // 가짜 사용자 데이터 생성
        String email = "test@example.com";
        String password = "password123";

        // 사용자 Repository에 가짜 데이터 저장
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(getUser(email, password)));

        // 잘못된 비밀번호로 로그인 시도
        LoginRequestDto loginRequestDto = new LoginRequestDto();
        loginRequestDto.setEmail(email);
        loginRequestDto.setPassword("wrongpassword");

        // 비밀번호가 일치하지 않아야 함
        assertThrows(IllegalArgumentException.class, () -> userService.login(loginRequestDto, response));
    }

    @Test
    public void testLoginFailure_UserNotFound() {
        // 이메일이 존재하지 않는 사용자로 로그인 시도
        String email = "nonexistent@example.com";
        String password = "password123";

        // 사용자 Repository에 해당 이메일을 찾을 수 없도록 설정
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // 사용자 없이 로그인을 시도하면 예외가 발생해야 함
        LoginRequestDto loginRequestDto = new LoginRequestDto();
        loginRequestDto.setEmail(email);
        loginRequestDto.setPassword(password);

        assertThrows(IllegalArgumentException.class, () -> userService.login(loginRequestDto, response));
    }

    // 가짜 사용자 객체를 생성하는 메서드
    private User getUser(String email, String password) {
        return User.builder()
                .email(email)
                .password(password)
                .build();
    }

}
