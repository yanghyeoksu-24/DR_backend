$(document).ready(function () {
  // 비밀번호 정규표현식 검사 함수: 최소 8자, 문자, 숫자, 특수문자 포함
  function validatePassword(password) {
    const regex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
    return regex.test(password);
  }

  const newPassword = document.getElementById("newPassword");
  const confirmNewPassword = document.getElementById("confirmNewPassword");
  const passwordError = document.getElementById("passwordError");
  const confirmPasswordError = document.getElementById("confirmPasswordError");

  // 첫 번째 비밀번호 필드 input 이벤트: 비밀번호 유효성 검사
  newPassword.addEventListener("input", function () {
    const password = newPassword.value;
    if (!validatePassword(password)) {
      passwordError.innerHTML = "비밀번호는 최소 8자 이상이어야 하며, 문자, 숫자, 특수문자를 포함해야 합니다.";
      passwordError.style.color = "red";
    } else {
      passwordError.innerHTML = ""; // 오류 메시지 초기화
    }
  });

  // 두 번째 비밀번호 필드 input 이벤트: 비밀번호 일치 확인
  confirmNewPassword.addEventListener("input", function () {
    const password = newPassword.value;
    const confirmPassword = confirmNewPassword.value;
    if (password === confirmPassword) {
      confirmPasswordError.innerHTML = "비밀번호가 일치합니다.";
      confirmPasswordError.style.color = "green";
    } else {
      confirmPasswordError.innerHTML = "비밀번호가 일치하지 않습니다.";
      confirmPasswordError.style.color = "red";
    }
  });

  // 비밀번호 토글 기능: 비밀번호 보기/숨기기
  $('#passwordToggle').on('click', function () {
    const passwordInput = $('#newPassword');
    const passwordToggleIcon = $('#passwordToggle');

    if (passwordInput.attr('type') === 'password') {
      passwordInput.attr('type', 'text'); // 비밀번호 보이기
      passwordToggleIcon.attr('src', './../../image/view.png'); // 아이콘 변경
    } else {
      passwordInput.attr('type', 'password'); // 비밀번호 숨기기
      passwordToggleIcon.attr('src', './../../image/noView.png'); // 아이콘 변경
    }
  });

  // 비밀번호 확인 토글 기능: 비밀번호 확인 필드 보기/숨기기
  $('#confirmPasswordToggle').on('click', function () {
    const confirmPasswordInput = $('#confirmNewPassword');
    const confirmPasswordToggleIcon = $('#confirmPasswordToggle');

    if (confirmPasswordInput.attr('type') === 'password') {
      confirmPasswordInput.attr('type', 'text'); // 비밀번호 보이기
      confirmPasswordToggleIcon.attr('src', './../../image/view.png'); // 아이콘 변경
    } else {
      confirmPasswordInput.attr('type', 'password'); // 비밀번호 숨기기
      confirmPasswordToggleIcon.attr('src', './../../image/noView.png'); // 아이콘 변경
    }
  });
});
