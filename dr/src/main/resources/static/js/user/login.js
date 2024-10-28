document.addEventListener('DOMContentLoaded', function () {
    const passwordInput = document.getElementById('login-loginPw');
    const toggleIcon1 = document.querySelector('.login-formTogleOne');
    const toggleIcon2 = document.querySelector('.login-formTogleTwo');

    let isPasswordVisible = false;

    toggleIcon2.style.display = 'none'; // 초기 설정

    toggleIcon1.addEventListener('click', function () {
        isPasswordVisible = true;
        passwordInput.type = 'text';
        toggleIcon1.style.display = 'none';
        toggleIcon2.style.display = 'block';
    });

    toggleIcon2.addEventListener('click', function () {
        isPasswordVisible = false;
        passwordInput.type = 'password';
        toggleIcon1.style.display = 'block';
        toggleIcon2.style.display = 'none';
    });

    const loginButton = document.getElementById('login-loginButton');

    loginButton.addEventListener('click', function (event) {
        const loginId = document.getElementById('login-loginId').value.trim();
        const loginPw = document.getElementById('login-loginPw').value.trim();

        if (!loginId || !loginPw) {
            alert("이메일이나 비밀번호를 확인해주세요.");
            event.preventDefault(); // 입력값이 없을 경우 폼 제출 방지
        }
    });
});
