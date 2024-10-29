$(document).ready(function () {
    // 이메일 형식 정규표현식
    const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    // 휴대폰 번호 형식 정규표현식 (하이픈 없이 숫자만 10~11자리)
    const phonePattern = /^[0-9]{10,11}$/;

    // 이메일 입력란 blur 이벤트: 유효성 검사
    $('#userEmail').on('blur', function () {
        const userEmailValue = $(this).val().trim();
        if (!emailPattern.test(userEmailValue)) {
            $('#userEmailError').text("올바른 이메일 형식을 입력하세요.").css('color', 'red');
        } else {
            $('#userEmailError').text(""); // 오류 메시지 초기화
        }
    });

    // 휴대폰 번호 입력란 blur 이벤트: 유효성 검사
    $('#userPhone').on('blur', function () {
        const userPhoneValue = $(this).val().trim();
        if (!phonePattern.test(userPhoneValue)) {
            $('#phoneError').text("하이픈(-) 없이 숫자만 10~11자리를 입력하세요.").css('color', 'red');
        } else {
            $('#phoneError').text(""); // 오류 메시지 초기화
        }
    });

    // 인증번호 입력란 blur 이벤트: 유효성 검사
    $('#authCode').on('blur', function () {
        const authCodeValue = $(this).val().trim();
        if (!authCodeValue) {
            $('#authCodeError').text("인증번호를 입력하세요.").css('color', 'red');
        } else {
            $('#authCodeError').text(""); // 오류 메시지 초기화
        }
    });

    // 인증요청 버튼 클릭 이벤트 처리
    $('#sendCode').on('click', function () {
        const userPhone = $('#userPhone').val().trim();

        // 휴대폰 번호 형식 검사
        if (!phonePattern.test(userPhone)) {
            $('#phoneError').text("하이픈(-) 없이 숫자만 10~11자리를 입력하세요.").css('color', 'red');
            return;
        } else {
            $('#phoneError').text(""); // 오류 메시지 초기화
        }

        // 유효한 번호일 경우에만 인증 요청
        $.ajax({
            url: '/api/sms/send',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({phoneNumber: userPhone}),
            success: function (response) {
                alert("인증 코드가 발송되었습니다.");
                $('#authCode').prop('disabled', false); // 인증번호 입력 필드 활성화
            },
            error: function (xhr, status, error) {
                console.error("에러 발생: " + error);
                alert("서버와의 통신 중 오류가 발생했습니다.");
            }
        });
    });

    // 인증번호 확인 버튼 클릭 이벤트 처리
    $('#verifyCode').on('click', function () {
        const authCode = $('#authCode').val().trim();

        // 인증번호 입력 확인
        if (!authCode) {
            $('#authCodeError').text("인증번호를 입력하세요.").css('color', 'red');
            return;
        } else {
            $('#authCodeError').text(""); // 오류 메시지 초기화
        }

        // 인증번호 확인 요청
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
                console.error("에러 발생: " + error);
                alert("서버와의 통신 중 오류가 발생했습니다.");
            }
        });

        window.onload = function() {
            const errorMessage = /*[[${errorMessage}]]*/ null; // Thymeleaf 사용 시
            const message = /*[[${message}]]*/ null; // Thymeleaf 사용 시

            if (errorMessage) {
                alert(errorMessage);
            } else if (message) {
                alert(message);
            }
        };
    });
});
