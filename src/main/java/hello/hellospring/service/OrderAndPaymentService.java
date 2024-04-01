package hello.hellospring.service;
//주문하기 버튼을 클릭하면 자동으로 상품을 주문

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.response.IamportResponse;
import hello.hellospring.dto.PaymentCallbackRequest;
import hello.hellospring.dto.RequestPayDto;
import hello.hellospring.entity.Orders;
import hello.hellospring.entity.Payment;
import hello.hellospring.entity.PaymentStatus;
import hello.hellospring.entity.User;
import hello.hellospring.repository.OrdersRepository;
import hello.hellospring.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderAndPaymentService {
    private final OrdersRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final IamportClient iamportClient;

    public Orders autoOrder(User user) {
        // 임시 결제내역 생성
        Payment payment = Payment.builder()
                .price(100L)
                .status(PaymentStatus.READY)
                .build();

        paymentRepository.save(payment);

        // 주문 생성
        Orders order = Orders.builder()
                .user(user)
                .price(100L)
                .itemName("자바스크립트 기초")
                .orderUuid(UUID.randomUUID().toString())
                .payment(payment)
                .build();

        orderRepository.save(order);

        return order;
    }

    public RequestPayDto findRequestDto(String orderUuid) {
        Orders order = orderRepository.findByOrderUuid(orderUuid)
                .orElseThrow(() -> new IllegalArgumentException("주문이 없습니다."));

        return RequestPayDto.builder()
                .buyerName(order.getUser().getUserName())
                .buyerEmail(order.getUser().getEmail())
                .paymentPrice(order.getPayment().getPrice())
                .itemName(order.getItemName())
                .orderUuid(order.getOrderUuid())
                .build();
    }

    public IamportResponse<com.siot.IamportRestClient.response.Payment> paymentByCallback(PaymentCallbackRequest request) {
        try {
            // 결제 단건 조회(아임포트)
            IamportResponse<com.siot.IamportRestClient.response.Payment> iamportResponse = iamportClient.paymentByImpUid(request.getPaymentUuid());
            // 주문내역 조회
            Orders order = orderRepository.findByOrderUuid(request.getOrderUuid())
                    .orElseThrow(() -> new IllegalArgumentException("주문 내역이 없습니다."));

            // 결제 완료가 아니면
            if (!iamportResponse.getResponse().getStatus().equals("paid")) {
                // 주문, 결제 삭제
                orderRepository.delete(order);
                paymentRepository.delete(order.getPayment());

                throw new RuntimeException("결제 미완료");
            }

            // DB에 저장된 결제 금액
            Long price = order.getPayment().getPrice();
            // 실 결제 금액
            int iamportPrice = iamportResponse.getResponse().getAmount().intValue();

            // 결제 금액 검증
            if (iamportPrice != price) {
                // 주문, 결제 삭제
                orderRepository.delete(order);
                paymentRepository.delete(order.getPayment());

                // 결제금액 위변조로 의심되는 결제금액을 취소(아임포트)
                iamportClient.cancelPaymentByImpUid(new CancelData(iamportResponse.getResponse().getImpUid(), true, new BigDecimal(iamportPrice)));

                throw new RuntimeException("결제금액 위변조 의심");
            }

            // 결제 상태 변경
            order.getPayment().changePaymentBySuccess(PaymentStatus.OK, iamportResponse.getResponse().getImpUid());

            return iamportResponse;

        } catch (IamportResponseException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}

