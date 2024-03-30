package hello.hellospring.dto;

import lombok.Getter;

@Getter
public class MsgResponseDto {
    private String msg;
    private int statusCode;

    public MsgResponseDto(String msg, int statusCode) {
        this.msg = msg; //메세지
        this.statusCode = statusCode; //상태코드
    }
}
