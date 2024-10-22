$(document).ready(function () {
  // 이메일 형식 유효성 검사
  const userIdInput = $("#userId");
  const userIdError = $("#userIdError");

  // 이메일 정규표현식
  const userIdRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

  // 이메일 입력 필드 blur 이벤트
  userIdInput.on("blur", function () {
    const userIdValue = $(this).val();

    if (!userIdRegex.test(userIdValue)) {
      userIdError.text("형식에 맞게 이메일을 입력해주세요.")
        .css({ "color": "red", "display": "block" });  // 에러 메시지 표시
    } else {
      userIdError.text("").hide();  // 에러 메시지 숨김
    }
  });

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
      $('#phoneError').text("형식에 맞는 휴대폰 번호를 입력하세요.").css('color', 'red');
      return;
    }

    // 인증 요청 로직 추가 (Ajax 또는 다른 인증 처리)
    alert("인증이 요청되었습니다");

    // 인증번호 입력 필드 활성화
    $('#authCode').prop('disabled', false);
  });

  // 인증번호 확인 버튼 클릭 시 이벤트 처리
  $('#verifyCode').on('click', function () {
    const authCode = $('#authCode').val().trim();
    if (!authCode) {
      $('#authCodeError').text("인증번호를 입력하세요.").css('color', 'red');
      return;
    }

    // 인증번호 확인 로직 추가 (서버와 연동)
    alert("인증이 확인되었습니다");

    // 인증 성공 시 처리 (예: 인증 완료 메시지 표시)
    $('#authCodeError').text("인증이 완료되었습니다.").css('color', 'green');
  });

  // 인증완료 버튼 클릭 시 유효성 검사 후 페이지 이동
  $('.pwfind-finishButton').on('click', function (event) {
    event.preventDefault(); // 기본 폼 제출 방지

    // 유효성 검사 플래그
    let isValid = true;

    // 이메일 유효성 검사
    const userIdValue = $("#userId").val();
    if (!userIdRegex.test(userIdValue)) {
      isValid = false;
      $('#userIdError').text("형식에 맞는 이메일을 입력해주세요.").css('color', 'red');
    }

    // 휴대폰 번호 유효성 검사
    const phoneValue = $("#phone").val().trim();
    if (!phonePattern.test(phoneValue)) {
      isValid = false;
      $('#phoneError').text("형식에 맞는 휴대폰 번호를 입력해주세요.").css('color', 'red');
    }

    // 인증번호 유효성 검사
    const authCodeValue = $("#authCode").val().trim();
    if (authCodeValue === "") {
      isValid = false;
      $('#authCodeError').text("인증번호를 입력하세요.").css('color', 'red');
    }

    // 유효성 검사가 통과되었는지 확인
    if (isValid) {
      alert("비밀번호 변경 페이지로 이동합니다.");
      window.location.href = './PwReset.html'; // 비밀번호 재설정 페이지로 이동
    } else {
      alert("형식에 맞지 않습니다. 다시 입력해주세요.");
    }
  });
});
