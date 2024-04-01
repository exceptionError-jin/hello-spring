package hello.hellospring.controller;

import hello.hellospring.dto.UserDto;
import hello.hellospring.entity.Orders;
import hello.hellospring.jwt.JwtUtil;
import hello.hellospring.service.OrderAndPaymentService;
import hello.hellospring.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderAndPaymentService orderService;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @GetMapping("/order")
    public String order(@RequestParam(name = "message", required = false) String message,
                        @RequestParam(name = "orderUuid", required = false) String id,
                        Model model) {
        model.addAttribute("message", message);
        model.addAttribute("orderUuid", id);
        return "주문 등록 화면";
    }

    @PostMapping("/order")
    public ResponseEntity<Map<String, Object>> autoOrder(HttpServletRequest request, @RequestBody Map<String, Object> requestBody) {
        String token = jwtUtil.getJwtFromHeader(request);

        if (StringUtils.hasText(token) && jwtUtil.validateToken(token)) {
            // 토큰이 유효하면 토큰에서 이메일 정보 가져오기
            String email = jwtUtil.getUserInfoFromToken(token).getSubject();

            // 이메일 정보를 사용하여 사용자 조회
            UserDto userDto = userService.findUserByEmail(email);

            // 사용자가 존재하면 주문 생성
            if (userDto != null) {
                Orders order = orderService.autoOrder(userDto.toUser());

                if (order != null) {
                    // 주문이 성공적으로 생성되었을 때의 응답 데이터
                    Map<String, Object> responseData = new HashMap<>();
                    responseData.put("success", true);
                    responseData.put("orderUuid", order.getOrderUuid());
                    responseData.put("message", "주문 성공");
                    return ResponseEntity.ok(responseData);
                } else {
                    // 주문 생성 실패 시의 응답 데이터
                    Map<String, Object> responseData = new HashMap<>();
                    responseData.put("success", false);
                    responseData.put("message", "주문 실패");
                    return ResponseEntity.ok(responseData);
                }
            } else {
                // 사용자가 존재하지 않을 경우
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("error", "사용자를 찾을 수 없습니다.");
                return ResponseEntity.badRequest().body(errorResponse);
            }
        } else {
            // 토큰이 없거나 유효하지 않은 경우
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "유효하지 않은 토큰입니다.");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}
