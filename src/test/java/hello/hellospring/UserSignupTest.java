package hello.hellospring;

import hello.hellospring.dto.SignupRequestDto;
import hello.hellospring.entity.User;
import hello.hellospring.repository.UserRepository;
import hello.hellospring.service.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
public class UserSignupTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @MockBean
    PasswordEncoder passwordEncoder;

    @Test
    public void 회원가입_성공() {
        // Given
        SignupRequestDto registerDto = createValidRegisterDto();
        String encodedPassword = "encodedPassword";
        when(passwordEncoder.encode(registerDto.getPassword())).thenReturn(encodedPassword);

        // When
        Long saveId = userService.signup(registerDto).getId();

        // Then
        User findUser = userRepository.findById(saveId).orElse(null);
        assertNotNull(findUser);
        // 여기서 findUser의 password를 확인해서 encodedPassword와 동일한지 검증할 수도 있습니다.
    }

    @Test
    public void 회원가입_비밀번호_형식_불일치() {
        SignupRequestDto registerDto = createValidRegisterDto();
        registerDto.setPassword("weakpassword");

        assertThrows(IllegalArgumentException.class, () -> userService.signup(registerDto));
    }

    @Test
    public void 회원가입_이메일_형식_불일치() {
        SignupRequestDto registerDto = createValidRegisterDto();
        registerDto.setEmail("invalidemail");

        assertThrows(IllegalArgumentException.class, () -> userService.signup(registerDto));
    }

    @Test
    public void 회원가입_필수_입력값_누락() {
        SignupRequestDto registerDto = new SignupRequestDto();

        assertThrows(IllegalArgumentException.class, () -> userService.signup(registerDto));
    }

    private SignupRequestDto createValidRegisterDto() {
        SignupRequestDto registerDto = new SignupRequestDto();
        registerDto.setEmail("test4@example.com");
        registerDto.setPassword("StrongPassword123");
        registerDto.setPasswordConfirm("StrongPassword123");
        registerDto.setPhone("12345678");
        registerDto.setUserName("test user");
        return registerDto;
    }
}
