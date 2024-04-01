package hello.hellospring.entity;
//상품

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED) //생성 보안점 막음 -> build 없이 생성 불가능
@AllArgsConstructor
public class Payment extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price")
    private Long price;

    @Column(name = "status")
    private PaymentStatus status;

    @Column(name = "payment_uuid")
    private String paymentUuid; // 결제 고유 번호

    public void changePaymentBySuccess(PaymentStatus status, String paymentUuid) {
        this.status = status;
        this.paymentUuid = paymentUuid;
    }
}
