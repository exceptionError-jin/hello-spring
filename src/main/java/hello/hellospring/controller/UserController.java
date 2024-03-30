package hello.hellospring.controller;

import hello.hellospring.dto.LoginRequestDto;
import hello.hellospring.dto.MsgResponseDto;
import hello.hellospring.dto.SignupRequestDto;
import hello.hellospring.dto.UserDto;
import hello.hellospring.jwt.JwtUtil;
import hello.hellospring.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    // 회원 가입
    @PostMapping("/signup")
    public ResponseEntity<MsgResponseDto> signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);
        return ResponseEntity.ok(new MsgResponseDto("회원가입 완료", HttpStatus.OK.value()));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<MsgResponseDto> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        userService.login(loginRequestDto, response);
        return ResponseEntity.ok(new MsgResponseDto("로그인 완료",HttpStatus.OK.value()));
    }

    //회원 정보 조회(토큰 없으면 조회 안됨)
    @GetMapping
    public ResponseEntity<UserDto> getUserInformation(HttpServletRequest request) {
        String token = jwtUtil.getJwtFromHeader(request);

        if (StringUtils.hasText(token) && jwtUtil.validateToken(token)) {
            // 토큰이 유효하면 토큰에서 이메일 정보 가져오기
            String email = jwtUtil.getUserInfoFromToken(token).getSubject();

            // 이메일 정보를 사용하여 사용자 조회
            UserDto userDto = userService.findUserByEmail(email);

            // 조회된 사용자 정보 응답
            return ResponseEntity.ok(userDto);
        } else {
            // 토큰이 없거나 유효하지 않은 경우
            return ResponseEntity.badRequest().build();
        }
    }
}