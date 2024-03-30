package hello.hellospring.dto;

import hello.hellospring.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private Long id; //회원 정보 id
    private String userName; //회원 이름
    private String email; //회원 이메일
    private String phone; //회원 전화번호
    private String status; //회원 상품사용 상태

    public static UserDto fromEntity(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUserName(user.getUserName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setStatus(user.getStatus());
        return dto;
    }
}

