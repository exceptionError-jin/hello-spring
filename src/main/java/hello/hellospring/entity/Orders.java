//package hello.hellospring.entity;
////주문
//
//import jakarta.persistence.*;
//import lombok.AccessLevel;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED) //생성 보안점 막음 -> build 없이 생성 불가능
//public class Orders extends Base {
//    @Id //주문 id
//    @GeneratedValue(strategy = GenerationType.IDENTITY) //값 자동 증가
//    @Column(name = "order_id")
//    private Long id;
//
////    @ManyToOne // 하나의 유저는 여러개의 주문을 가짐
////    @JoinColumn(name = "user_id")
////    private User user;
//
//    @OneToMany(mappedBy = "orders") //하나의 주문은 여러개의 상품을 가짐
//    private List<Product> products = new ArrayList<>();
//
//    @OneToOne(mappedBy = "orders") //하나의 주문은 하나의 환불을 가짐
//    private Refund refund;
//
//    @Column(name = "order_number") //주문번호
//    private String orderNumber;
//
//    @Column(name = "payment_amount") //결제 금액
//    private BigDecimal paymentAmount;
//
//    @CreationTimestamp //결제 요청 날짜
//    @Column(name = "request_date")
//    private LocalDateTime requestDate;
//
//    @UpdateTimestamp //결제 완료 날짜
//    @Column(name = "complete_date")
//    private LocalDateTime completeDate;
//
//    @Column(name = "billing_key") //빌링키
//    private String billingKey;
//
//    @Column(name = "result_status") //결과 상태
//    private String resultStatus;
//
//    @Column(name = "result_message") //결과 메세지
//    private String resultMessage;
//
//
//}
