$(document).ready(function () {
    // 전체 동의 체크박스 클릭 시 나머지 체크박스 모두 체크 또는 해제
    $('#checkAll').on('change', function () {
        var isChecked = $(this).is(':checked');
        $('.drjoin-checkboxInput').not('#checkAll').prop('checked', isChecked);
    });

    // 개별 체크박스 상태 변경 시 전체 동의 체크박스 상태도 변경
    $('.drjoin-checkboxInput').not('#checkAll').on('change', function () {
        var allChecked = $('.drjoin-checkboxInput').not('#checkAll').length === $('.drjoin-checkboxInput:checked').not('#checkAll').length;
        $('#checkAll').prop('checked', allChecked);
    });

    // 아이디 정규표현식: 이메일 형식 검사
    const userIdRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    // 아이디(이메일) blur 시 정규표현식 검사
    $('#userEmail').on("blur", function () {
        const userIdValue = $(this).val();
        if (!userIdRegex.test(userIdValue)) {
            $("#userIdError").text("형식에 맞게 입력해주세요.").css({"color": "red", "display": "block"});
        } else {
            $("#userIdError").text("").hide();
        }
    });

    // 중복확인 버튼 클릭 시 이벤트 처리
    $('#checkDuplicate').on('click', function () {
        const userIdValue = $('#userEmail').val().trim();
        if (!userIdRegex.test(userIdValue)) {
            $("#userIdError").text("형식에 맞게 입력해주세요.").css({"color": "red", "display": "block"});
            return;
        }

        // 여기는 중복 체크를 서버에 요청하는 부분이 필요하지만, 예시로 성공 메시지 표시
        $("#userIdError").text("사용 가능한 이메일입니다.").css({"color": "green", "display": "block"});
    });

    // 비밀번호 정규표현식 검사 함수
    function validatePassword(password) {
        const regex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
        return regex.test(password);
    }

    const pw = document.getElementById("userPw");
    const confirmPw = document.getElementById("confirmPassword");

    const passwordError = document.getElementById("passwordError");
    const confirmPasswordError = document.getElementById("confirmPasswordError");

    // 첫 번째 비밀번호 필드 blur 이벤트
    pw.addEventListener("blur", function () {
        const password = pw.value;
        if (!validatePassword(password)) {
            passwordError.innerHTML = "비밀번호는 최소 8자 이상이어야 하며, 문자, 숫자, 특수문자를 포함해야 합니다.<br>";
            passwordError.style.color = "red";
        } else {
            passwordError.innerHTML = ""; // 오류 메시지 초기화
        }
    });

    // 두 번째 비밀번호 필드 blur 이벤트
    confirmPw.addEventListener("blur", function () {
        const password = pw.value;
        const confirmPassword = confirmPw.value;
        if (password === confirmPassword) {
            confirmPasswordError.innerHTML = "비밀번호가 일치합니다.<br>";
            confirmPasswordError.style.color = "green";
        } else {
            confirmPasswordError.innerHTML = "비밀번호가 일치하지 않습니다.<br>";
            confirmPasswordError.style.color = "red";
        }
    });

    // 휴대폰 번호 유효성 검사 정규표현식 (하이픈 없이 숫자만 10~11자리)
    const phonePattern = /^[0-9]{10,11}$/;

    $('#sendCode').on('click', function () {
        const phone = $('#userPhone').val().trim();
        $.ajax({
            url: '/api/sms/send',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({phoneNumber: phone}),
            success: function (response) {
                alert(response);
            },
            error: function (xhr, status, error) {
                console.error("에러 발생: " + error);
                alert("서버와의 통신 중 오류가 발생했습니다.");
            }
        });
    });

    // 인증번호 확인
    $('#verifyCode').on('click', function () {
        const authCode = $('#authCode').val().trim();
        if (!authCode) {
            $('#authCodeError').text("인증번호를 입력하세요.").css('color', 'red');
            return;
        }

        $.ajax({
            url: '/api/sms/verify',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({authCode: authCode}),
            success: function (response) {
                if (response === "인증이 완료되었습니다.") {
                    $('#authCodeError').text(response).css('color', 'green');
                } else if (response === "인증번호가 만료되었습니다. 다시 시도해 주세요.") {
                    $('#authCodeError').text(response).css('color', 'orange');
                    alert("인증번호가 만료되었습니다. 다시 요청해주세요.");
                } else {
                    $('#authCodeError').text(response).css('color', 'red');
                }
            },
            error: function (xhr, status, error) {
                console.log("에러 발생: " + error);
                alert("서버와의 통신 중 오류가 발생했습니다.");
            }
        });
    });

    // 회원가입 버튼 클릭 시 AJAX 요청
    $('#submitForm').on('click', function (event) {
        let isValid = true;

        // 유효성 검사
        const userIdValue = $('#userEmail').val().trim();
        if (!userIdRegex.test(userIdValue)) {
            isValid = false;
            alert("올바른 이메일 형식을 입력하세요.");
            return;
        }

        const passwordValue = $('#userPw').val().trim();
        if (!validatePassword(passwordValue)) {
            isValid = false;
            alert("비밀번호는 최소 8자 이상이어야 하며, 문자, 숫자, 특수문자를 포함해야 합니다.");
            return;
        }

        const confirmPasswordValue = $('#confirmPassword').val().trim();
        if (passwordValue !== confirmPasswordValue) {
            isValid = false;
            alert("비밀번호가 일치하지 않습니다.");
            return;
        }

        const nameValue = $('#userNickName').val().trim();
        if (nameValue === "") {
            isValid = false;
            alert("닉네임을 입력하세요.");
            return;
        }

        const phoneValue = $('#userPhone').val().trim();
        if (!phonePattern.test(phoneValue)) {
            isValid = false;
            alert("올바른 휴대폰 번호를 입력하세요.");
            return;
        }
    });

    // 비밀번호 토글 기능 추가
    document.getElementById('passwordToggle').addEventListener('click', function () {
        const passwordInput = document.getElementById('userPw');
        const toggleIcon = document.getElementById('passwordToggle');
        togglePasswordVisibility(passwordInput, toggleIcon);
    });

    document.getElementById('confirmPasswordToggle').addEventListener('click', function () {
        const confirmPasswordInput = document.getElementById('confirmPassword');
        const toggleIcon = document.getElementById('confirmPasswordToggle');
        togglePasswordVisibility(confirmPasswordInput, toggleIcon);
    });

    // 비밀번호 보이기/숨기기 기능
    function togglePasswordVisibility(input, toggleIcon) {
        if (input.type === 'password') {
            input.type = 'text'; // 비밀번호 보이기
            toggleIcon.src = "/image/view.png"; // 아이콘 변경 (예시)
        } else {
            input.type = 'password'; // 비밀번호 숨기기
            toggleIcon.src = "/image/noView.png"; // 아이콘 변경 (예시)
        }
    }
});
