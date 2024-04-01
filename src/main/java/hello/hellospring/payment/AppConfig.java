package hello.hellospring.payment;
//결제 portone에 필요한 정보
import com.siot.IamportRestClient.IamportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    String apiKey = "7640422726136573";
    String secretKey = "sWnPYbnbRYmWoqFPx6VMPA3QlKQumJRYkkk4FSY5yx3mrJhEqK066kLuUi1ipKpCQAQLpT8dEGGlM0ia";

    @Bean
    public IamportClient iamportClient() {
        return new IamportClient(apiKey, secretKey);
    }
}
