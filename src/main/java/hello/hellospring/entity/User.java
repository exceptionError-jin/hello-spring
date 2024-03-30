package hello.hellospring.entity;
//유저

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED) //생성 보안점 막음 -> build 없이 생성 불가능
@AllArgsConstructor
public class User extends Base {
    @Id //유저 id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //값 자동 증가
    private Long id;

    // 유저가 삭제되면 주문정보도 삭제되는가?? 논의 해볼 필요 있음
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true) // 하나의 유저는 여러개의 주문을 가짐
//    private List<Orders> orders = new ArrayList<>();

    // 위와 같음
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true) //하나의 유저는 여러개의 환불을 가짐
//    private List<Refund> refunds = new ArrayList<>();

    @Column(name = "user_name") // 유저 이름
    private String userName;

    @Column(name = "email", nullable = false, unique = true) // 이메일
    private String email;

    @Column(name = "password", nullable = false) // 비밀번호
    private String password;

    @Column(name = "phone_number") // 핸드폰 번호
    private String phone;

    @Column(name = "status") // 상품사용 상태
    private String status;
}

