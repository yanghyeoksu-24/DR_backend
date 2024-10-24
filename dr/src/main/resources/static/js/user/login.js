document.addEventListener('DOMContentLoaded', function () {
  const passwordInput = document.getElementById('login-loginPw');
  const toggleIcon1 = document.querySelector('.login-formTogleOne');
  const toggleIcon2 = document.querySelector('.login-formTogleTwo');

  let isPasswordVisible = false;

  // 초기 상태 설정
  toggleIcon2.style.display = 'none'; // 처음에 비밀번호가 보이지 않도록 설정

  // 토글 기능
  toggleIcon1.addEventListener('click', function () {
      isPasswordVisible = true;
      passwordInput.type = 'text'; // 비밀번호 보이기
      toggleIcon1.style.display = 'none'; // 첫 번째 아이콘 숨기기
      toggleIcon2.style.display = 'block'; // 두 번째 아이콘 보이기
  });

  toggleIcon2.addEventListener('click', function () {
      isPasswordVisible = false;
      passwordInput.type = 'password'; // 비밀번호 숨기기
      toggleIcon1.style.display = 'block'; // 첫 번째 아이콘 보이기
      toggleIcon2.style.display = 'none'; // 두 번째 아이콘 숨기기
  });
});

document.addEventListener('DOMContentLoaded', function () {
  const loginForm = document.getElementById('login-formOne');
  const loginButton = document.getElementById('login-loginButton');

  loginButton.addEventListener('click', function (event) {
      // event.preventDefault(); // 기본 폼 제출 방지

      const loginId = document.getElementById('login-loginId').value.trim();
      const loginPw = document.getElementById('login-loginPw').value.trim();

      // 이메일 또는 비밀번호가 비어있는지 확인
      if (!loginId || !loginPw) {
          alert("이메일이나 비밀번호를 확인해주세요.");
      } else {
          // 입력값이 모두 유효할 경우 폼 제출
          // loginForm.submit(); // 주석 해제하여 실제 제출할 경우 사용
          alert("로그인이 완료되었습니다."); // 로그인 처리 로직 추가

      }
  });
});

