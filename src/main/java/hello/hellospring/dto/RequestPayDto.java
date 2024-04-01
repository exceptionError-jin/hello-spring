package hello.hellospring.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RequestPayDto {
    private String orderUuid;
    private String itemName;
    private String buyerName;
    private Long paymentPrice;
    private String buyerEmail;

    @Builder
    public RequestPayDto(String orderUuid, String itemName, String buyerName, Long paymentPrice, String buyerEmail) {
        this.orderUuid = orderUuid;
        this.itemName = itemName;
        this.buyerName = buyerName;
        this.paymentPrice = paymentPrice;
        this.buyerEmail = buyerEmail;
    }
}