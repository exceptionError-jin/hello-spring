//package hello.hellospring.entity;
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
//
//import static jakarta.persistence.FetchType.*;
//
//@Entity
//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED) //생성 보안점 막음 -> build 없이 생성 불가능
//public class Refund extends Base {
//
//    @Id //환불 id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "refund_id")
//    private Long id;
//
////    @ManyToOne(fetch = LAZY) //하나의 유저는 여러개의 환불을 가짐
////    @JoinColumn(name = "user_id")
////    private User user;
//
//    @OneToOne(fetch = LAZY) //하나의 주문은 하나의 환불을 가짐
//    @JoinColumn(name = "order_id")
//    private Orders orders;
//
//    @Column(name = "request_time") //취소 요청 시간
//    @CreationTimestamp
//    private LocalDateTime requestTime;
//
//    @Column(name = "completed_time") //취소 완료 시간
//    @UpdateTimestamp
//    private LocalDateTime completedTime;
//
//    @Column(name = "refund_amount") //환불 금액
//    private BigDecimal refundAmount;
//
//    @Column(name = "result_status") //결과 상태
//    private String resultStatus;
//
//    @Column(name = "result_message") //결과 메세지
//    private String resultMessage;
//}