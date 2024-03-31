회원가입 화면
![signup](https://github.com/exceptionError-jin/hello-spring/assets/129862445/f0ed5a9d-21b3-4307-895f-3430eeaa47e8)

회원가입 성공 시
![signupSuccess](https://github.com/exceptionError-jin/hello-spring/assets/129862445/ea6612f7-ff98-422b-86a3-77ec87732ad2)

회원가입 시 비밀번호 암호화
![password](https://github.com/exceptionError-jin/hello-spring/assets/129862445/55654fd0-6109-45cf-888c-f4cf9420d421)

회원가입 유효성 검사(이메일 중복 검사, 이메일&비밀번호 null 검사, 이메일 형식 검사, 비밀번호 유효성 검사(숫자, 문자 포함 8자리 이상), 비밀번호와 비밀번호 확인 일치 검사)
![signupEmail](https://github.com/exceptionError-jin/hello-spring/assets/129862445/7fdaba05-55a9-48c9-aa81-7537a20591db)

회원가입 실패 시
![signupFailed](https://github.com/exceptionError-jin/hello-spring/assets/129862445/934db6c2-45c9-4c5e-b6bf-1277ff29240a)

로그인 화면
![login](https://github.com/exceptionError-jin/hello-spring/assets/129862445/9721cc3b-aad9-42b4-b406-320df676d9cc)

로그인 성공 시(토큰 발급 됨)
![loginSuccess](https://github.com/exceptionError-jin/hello-spring/assets/129862445/77b837c9-cd70-4fa2-aee4-c71b872e0db9)
![loginSuccessToken](https://github.com/exceptionError-jin/hello-spring/assets/129862445/e6b4b427-9e5f-460a-82d9-9a80ef1e2a55)

로그인 실패 시
![loginFailed](https://github.com/exceptionError-jin/hello-spring/assets/129862445/1e27bb0f-f938-4301-b20d-dcef325f35d9)

회원정보 조회(토큰이 존재할 때만 조회 가능)
![userDetailSuccess](https://github.com/exceptionError-jin/hello-spring/assets/129862445/2337c3a5-6e32-4858-ab2e-f3cd5e4e19d9)
![userDetailToken](https://github.com/exceptionError-jin/hello-spring/assets/129862445/367f21b6-8674-4fac-9977-7289edfedb14)

회원정보 조회(토큰이 없을 경우)
![userDetailFailed](https://github.com/exceptionError-jin/hello-spring/assets/129862445/52a07a1c-246f-4e6d-ad1f-fa872af9ddeb)






구현 기능

회원가입(사용자정보(이름, 이메일, 비밀번호))
  유효성검사(이메일 형식, 비밀번호 길이)
  중복 가입 체크
  회원 정보 저장
  성공 / 실패 응답

로그인(이메일, 비밀번호)
  정보 일치 확인
  로그인 성공 시 토큰 발급
  로그인 실패 시 에러 응답

회원 정보 조회(토큰 검증)
  회원 정보 조회 및 제공
  정보 조회 실패 시 에러 응답


구현 예정

결제 요청(상품 정보, 결제 금액, 결제 방식 입력)
  결제 승인 요청
  결제 승인 성공 시 결제 정보 저장 및 응답
  결제 승인 실패 시 에러 응답

결제 취소(결제 정보(주문번호) 입력)
  결제 취소 요청
  결제 취소 성공 시 응답
  결제 취소 실패 시 에러 응답
