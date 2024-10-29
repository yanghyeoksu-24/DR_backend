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
    const loginButton = document.getElementById('login-loginButton');

    loginButton.addEventListener('click', function (event) {
        event.preventDefault(); // 기본 폼 제출 방지

        const loginId = document.getElementById('login-loginId').value.trim();
        const loginPw = document.getElementById('login-loginPw').value.trim();

        // 이메일과 비밀번호가 모두 비어있는지 확인
        if (!loginId && !loginPw) {
            alert("이메일과 비밀번호를 모두 입력해주세요.");
            return;
        }

        // FormData 객체 생성
        const formData = new FormData();
        formData.append('userEmail', loginId);
        formData.append('userPw', loginPw);

        // 서버에 AJAX 요청 보내기
        fetch('/user/login', {
            method: 'POST',
            body: formData
        })
            .then(response => {
                return response.json().then(data => {
                    if (response.ok) {
                        // 로그인 성공 시 알림 및 메인 페이지로 리다이렉트
                        alert(data.message); // "환영합니다."
                        window.location.href = data.redirect; // 메인 페이지로 이동
                    } else {
                        // 로그인 실패 시 알림 및 로그인 페이지로 리다이렉트
                        alert(data.message); // "이메일이나 비밀번호가 잘못되었습니다."
                        window.location.href = data.redirect; // 로그인 페이지로 이동
                    }
                });
            })
            .catch(error => {
                console.error("에러 발생:", error);
                alert("서버와의 통신 중 오류가 발생했습니다.");
            });
    });
});


