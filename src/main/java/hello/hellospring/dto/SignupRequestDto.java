package hello.hellospring.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignupRequestDto {
    private String email; //이메일
    private String password; // 비밀번호
    private String passwordConfirm; //비밀번호 검증
    private String userName; // 유저 이름
    private String phone; // 핸드폰 번호
}