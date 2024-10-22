// 휴대폰 번호 유효성 검사 정규표현식 (하이픈 없이 숫자만 10~11자리)
const phonePattern = /^[0-9]{10,11}$/;

// 휴대폰 번호 입력 필드에 대한 blur 이벤트
$('#phone').on('blur', function () {
  const phone = $(this).val().trim();
  if (!phone) {
    $('#phoneError').text("휴대폰 번호를 입력하세요.").css('color', 'red');
    return;
  }
  if (!phonePattern.test(phone)) {
    $('#phoneError').text("형식에 맞게 입력하세요.").css('color', 'red');
  } else {
    $('#phoneError').text("");
  }
});

// 인증요청 버튼 클릭 시 이벤트 처리
$('#sendCode').on('click', function () {
  const phone = $('#phone').val().trim();
  if (!phonePattern.test(phone)) {
    $('#phoneError').text("형식에 맞게 입력하세요.").css('color', 'red');
    return;
  }
  
  // 여기에 인증 요청 로직을 추가
  // 예: 서버로 인증 요청을 보내는 Ajax 호출 등
  alert("인증이 요청되었습니다");

  // 인증 요청 후 처리할 코드 (예: 인증번호 입력 필드 활성화)
  $('#authCode').prop('disabled', false);
});

// 인증번호 확인 버튼 클릭 시 이벤트 처리
$('#verifyCode').on('click', function () {
  const authCode = $('#authCode').val().trim();
  if (!authCode) {
    $('#authCodeError').text("인증번호를 입력하세요.").css('color', 'red');
    return;
  }

  // 여기에 인증번호 확인 로직 추가
  // 예: 서버로 인증번호 확인 요청을 보내는 Ajax 호출 등
  alert("인증이 확인되었습니다");

  // 인증 성공 시 처리할 코드 (예: 인증 완료 메시지 표시)
  $('#authCodeError').text("인증이 완료되었습니다.").css('color', 'green');
});

$('#finishButton').on('click', function() {
  window.location.href = './emailFindFinish.html';
});


