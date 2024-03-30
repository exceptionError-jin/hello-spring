package hello.hellospring.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequestDto {
    private String email; //이메일
    private String password; // 비밀번호
}
