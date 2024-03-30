package hello.hellospring.service;

import hello.hellospring.dto.UserDto;
import hello.hellospring.entity.User;
import hello.hellospring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class BoardService {
    private final UserRepository userRepository;

    // 이메일을 사용하여 사용자 정보 조회
    public UserDto findUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 이메일로 사용자를 찾을 수 없습니다."));

        // 조회된 User 엔티티를 UserDto로 변환하여 반환
        return UserDto.fromEntity(user);
    }
}
