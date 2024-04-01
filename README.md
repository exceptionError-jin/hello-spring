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

결제 요청(상품 정보, 결제 금액, 결제 방식 입력)
  결제 승인 요청
  결제 승인 성공 시 결제 정보 저장 및 응답
  결제 승인 실패 시 에러 응답

결제 취소(결제 정보(주문번호) 입력)
  결제 취소 요청
  결제 취소 성공 시 응답
  결제 취소 실패 시 에러 응답

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

상품주문(OrderAndPaymentService에서 임시로 값이 저장되도록 하였습니다.)
![상품주문1](https://github.com/exceptionError-jin/hello-spring/assets/129862445/57bf2da2-06a6-4e7d-89e9-a07e0e6ba075)
![상품주문2](https://github.com/exceptionError-jin/hello-spring/assets/129862445/88ed8e56-0d1b-4056-bd15-15257170dc35)

상품주문시 DB에 추가
![상품주문DB추가](https://github.com/exceptionError-jin/hello-spring/assets/129862445/6f2f3000-c861-4d9f-a8dc-331fe1fd38ea)

상품주문 회원정보 토큰확인
![상품주문에도 토큰확인](https://github.com/exceptionError-jin/hello-spring/assets/129862445/ebe72866-bb2f-4ff9-92ba-388c53a0da6a)

결제페이지(결제 기능은 전부 html로 구현하였습니다.)
![결제페이지](https://github.com/exceptionError-jin/hello-spring/assets/129862445/85051b3b-080b-44fb-b2aa-0b37ea2dde6a)

결제 카드 선택
![결제카드선택](https://github.com/exceptionError-jin/hello-spring/assets/129862445/f2a29098-f939-464f-9503-3d58d49339c9)
![결제성공화면](https://github.com/exceptionError-jin/hello-spring/assets/129862445/8fa67d8f-fd48-46d3-9687-62313afec728)

현대카드결제화면
![현대카드결제화면](https://github.com/exceptionError-jin/hello-spring/assets/129862445/ef29a00c-0c9f-4de1-bcd2-e15257369757)

잘못된 주문 결제 시
![잘못된주문시에러](https://github.com/exceptionError-jin/hello-spring/assets/129862445/be0a7bbb-e14b-4932-9197-e8934d8b4f01)
