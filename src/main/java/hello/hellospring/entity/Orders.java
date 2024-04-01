package hello.hellospring.entity;
//주문

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED) //생성 보안점 막음 -> build 없이 생성 불가능
@AllArgsConstructor
public class Orders extends Base {
    @Id //주문 id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price")//가격
    private Long price;

    @Column(name = "item_name")//상품이름
    private String itemName;

    @Column(name = "order_uuid")// 주문 번호
    private String orderUuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private Payment payment;
}
