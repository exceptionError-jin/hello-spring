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
