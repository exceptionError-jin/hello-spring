package hello.hellospring.service;

import hello.hellospring.dto.LoginRequestDto;
import hello.hellospring.dto.SignupRequestDto;
import hello.hellospring.dto.UserDto;
import hello.hellospring.entity.User;
import hello.hellospring.jwt.JwtUtil;
import hello.hellospring.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor //필드를 final로 선언하고 생성자를 통해 주입 @AutoWired사용 안해도됨
public class UserService {
    private final UserRepository userRepository;
    private final UserValidationService userValidationService;
    private final PasswordEncoder passwordEncoder;

    //회원가입
    public User signup(SignupRequestDto signupDto) {
        // 디버깅을 위해 입력된 비밀번호와 확인용 비밀번호를 로그로 출력
        System.out.println("Entered Password: " + signupDto.getPassword());
        System.out.println("Confirmed Password: " + signupDto.getPasswordConfirm());
        //회원가입 유효성 검사들
        userValidationService.validateUserRegistration(signupDto);

        // User 엔티티 생성 및 저장
        User user = User.builder()
                .email(signupDto.getEmail())
                .password(signupPasswordEncoder(signupDto.getPassword()))
                .userName(signupDto.getUserName())
                .build();

        return userRepository.save(user);
    }

    // 로그인
    public void login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();

        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new IllegalArgumentException("해당 이메일을 찾을 수 없습니다.")
        );

        // 비밀번호 일치 여부 확인
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // Header 에 key 값과 Token 담기
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, JwtUtil.createToken(user.getEmail()));
    }

    // 비밀번호 암호화
    public String signupPasswordEncoder(String password) {
        return passwordEncoder.encode(password);
    }

    // 이메일을 사용하여 사용자 정보 조회
    public UserDto findUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 이메일로 사용자를 찾을 수 없습니다."));

        // 조회된 User 엔티티를 UserDto로 변환하여 반환
        return UserDto.fromEntity(user);
    }
}
