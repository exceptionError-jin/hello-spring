package hello.hellospring.controller;

import hello.hellospring.dto.UserDto;
import hello.hellospring.jwt.JwtUtil;
import hello.hellospring.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class BoardController {
    private final BoardService boardService;
    private final JwtUtil jwtUtil;

    //회원 정보 조회(토큰 없으면 조회 안됨)
    @GetMapping
    public ResponseEntity<UserDto> getUserInformation(HttpServletRequest request) {
        String token = jwtUtil.getJwtFromHeader(request);

        if (StringUtils.hasText(token) && jwtUtil.validateToken(token)) {
            // 토큰이 유효하면 토큰에서 이메일 정보 가져오기
            String email = jwtUtil.getUserInfoFromToken(token).getSubject();

            // 이메일 정보를 사용하여 사용자 조회
            UserDto userDto = boardService.findUserByEmail(email);

            // 조회된 사용자 정보 응답
            return ResponseEntity.ok(userDto);
        } else {
            // 토큰이 없거나 유효하지 않은 경우
            return ResponseEntity.badRequest().build();
        }
    }
}