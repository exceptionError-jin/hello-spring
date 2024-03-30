package hello.hellospring.service;
// 유저 유효성 메서드 모아둠

import hello.hellospring.dto.SignupRequestDto;
import hello.hellospring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor //필드를 final로 선언하고 생성자를 통해 주입 @AutoWired사용 안해도됨
public class UserValidationService {
    private final UserRepository userRepository;

    //이메일, 비밀번호 null 검사
    public void checkEmailAndPasswordNotNull(String email, String password) {
        if (Objects.isNull(email) || Objects.isNull(password)) {
            throw new IllegalArgumentException("이메일과 비밀번호는 필수값입니다.");
        }
    }

    //이메일 형식 검사
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    public void checkEmailValidity(String email) {
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("잘못된 이메일 형식입니다.");
        }
    }

    //이메일 중복 검사
    public void validateDuplicateUser(String email) {
        userRepository.findByEmail(email)
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 이메일입니다.");
                });
    }

    //비밀번호 유효성 검사
    public void checkPasswordValidity(String password) {
        //비밀번호 패턴: 최소 8자리 이상, 숫자와 문자 포함
        final Pattern passwordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-zA-Z])[a-zA-Z0-9]{8,}$");

        if (!passwordPattern.matcher(password).matches()) {
            throw new IllegalArgumentException("비밀번호는 숫자와 문자를 포함한 8자리 이상이어야 합니다.");
        }
    }


    //비밀번호 일치 확인 검사
    public void checkPasswordMatch(String password, String passwordConfirm) {
        if (!Objects.equals(password, passwordConfirm)) {
            throw new IllegalArgumentException("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }
    }

    //현재 회원가입 관련 유효성 검사들 다 모아둠
    public void validateUserRegistration(SignupRequestDto registerDto) {
        checkEmailAndPasswordNotNull(registerDto.getEmail(), registerDto.getPassword());
        checkEmailValidity(registerDto.getEmail());
        validateDuplicateUser(registerDto.getEmail());
        checkPasswordValidity(registerDto.getPassword());
        checkPasswordMatch(registerDto.getPassword(), registerDto.getPasswordConfirm());
    }
}
