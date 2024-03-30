//package hello.hellospring.entity;
////상품
//
//import jakarta.persistence.*;
//import lombok.AccessLevel;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import static jakarta.persistence.FetchType.*;
//
//@Entity
//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED) //생성 보안점 막음 -> build 없이 생성 불가능
//public class Product extends Base {
//    @Id //상품 id
//    @GeneratedValue(strategy = GenerationType.IDENTITY) //값 자동 증가
//    @Column(name = "Product_id")
//    private Long id;
//
//    @ManyToOne(fetch = LAZY) //하나의 주문은 여러개의 상품을 가짐
//    @JoinColumn(name = "order_id")
//    private Orders orders;
//
//    @Column(name = "Product_name") //상품 이름
//    private String name;
//
//    @Column(name = "Product_price") //상품 금액
//    private int price;
//}
