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

    $('#checkDuplicate').on('click', function () {
        const userIdValue = $('#userEmail').val().trim();

        // 이메일 형식 검사
        if (!userIdRegex.test(userIdValue)) {
            $("#userIdError").text("형식에 맞게 입력해주세요.").css({"color": "red", "display": "block"});
            return;
        }

        // 이메일 중복 확인 요청
        $.ajax({
            url: '/api/user/checkEmail', // 이메일 중복 확인 API 경로
            type: 'GET',
            data: {userEmail: userIdValue},
            success: function (response) {
                if (response) {
                    // 중복된 이메일일 경우
                    $("#userIdError").text("이미 존재하는 이메일입니다.").css({"color": "red", "display": "block"});
                } else {
                    // 사용 가능한 이메일일 경우
                    $("#userIdError").text("사용 가능한 이메일입니다.").css({"color": "green", "display": "block"});
                }
            },
            error: function (xhr, status, error) {
                console.error("에러 발생: " + error);
                alert("서버와의 통신 중 오류가 발생했습니다.");
            }
        });
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

        // 휴대폰 번호 형식 검사
        if (!phonePattern.test(콜)) {
            $('#phoneError').text("올바른 휴대폰 번호를 입력하세요. (하이픈 없이 10~11자리 숫자)").css("color", "red");
            return; // 유효하지 않은 번호면 요청을 보내지 않음
        } else {
            $('#phoneError').text(""); // 오류 메시지 초기화
        }

        // 유효한 번호일 경우에만 인증 요청
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
        showAlertAndRedirect();
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

    function showAlertAndRedirect() {
        alert("회원가입이 완료되었습니다.");
        return true; // 폼이 정상적으로 제출되도록 true 반환
    }


    $(document).ready(function () {
        let phoneAlertShown = false; // 중복 전화번호에 대한 alert 표시 여부
        let isRequesting = false; // 인증 요청 중복 방지 변수

        // 전화번호 중복 확인
        $('#userPhone').on('blur', function () {
            const userPhone = $(this).val().trim();
            const phonePattern = /^[0-9]{10,11}$/; // 하이픈 없이 숫자만 10~11자리

            if (!phonePattern.test(userPhone)) {
                $('#phoneError').text("올바른 형식의 휴대폰 번호를 입력하세요.").css('color', 'red');
                phoneAlertShown = false; // 오류 상태 초기화
                return;
            }

            $.ajax({
                url: '/api/user/checkPhone', // 중복 체크를 위한 API 엔드포인트
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({userPhone: userPhone}),
                success: function (response) {
                    if (response.exists) {
                        // 중복 전화번호에 대한 alert가 이미 표시되지 않았으면 표시
                        if (!phoneAlertShown) {
                            alert("전화번호가 이미 존재합니다.");
                            phoneAlertShown = true; // alert 표시 상태 업데이트
                        }
                        $('#phoneError').text("전화번호가 이미 존재합니다.").css('color', 'red');
                        // 인증 요청 버튼 비활성화
                        $('#sendCode').prop('disabled', true);
                    } else {
                        $('#phoneError').text(""); // 오류 메시지 초기화
                        phoneAlertShown = false; // 중복 상태 초기화
                        // 인증 요청 버튼 활성화
                        $('#sendCode').prop('disabled', false);
                    }
                },
                error: function (xhr, status, error) {
                    console.error("에러 발생: " + error);
                    alert("서버와의 통신 중 오류가 발생했습니다.");
                }
            });
        });

        document.addEventListener('DOMContentLoaded', function () {
            const sendCodeButton = document.getElementById('sendCode');
            let isRequestInProgress = false; // 인증 요청 진행 중 플래그

            // 인증 요청 버튼 클릭 이벤트 리스너
            sendCodeButton.addEventListener('click', function () {
                if (isRequestInProgress) {
                    return; // 이미 요청이 진행 중이면 아무 것도 하지 않음
                }

                isRequestInProgress = true; // 요청 진행 중임을 표시

                // 인증 요청 로직 (여기서는 간단한 alert로 대체)
                alert("인증 요청이 전송되었습니다.");

                // 서버에 인증 요청을 보내는 로직 추가
                const userPhone = document.getElementById('userPhone').value;

                // 예시: 서버에 인증 요청 보내기
                fetch('/api/sendAuthCode', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({userPhone: userPhone})
                })
                    .then(response => {
                        if (response.ok) {
                            // 요청 성공 시 처리
                            alert("인증 코드가 전송되었습니다.");
                        } else {
                            // 요청 실패 시 처리
                            alert("인증 요청에 실패했습니다.");
                        }
                    })
                    .catch(error => {
                        console.error("에러 발생:", error);
                        alert("서버와의 통신 중 오류가 발생했습니다.");
                    })
                    .finally(() => {
                        isRequestInProgress = false; // 요청 완료 후 플래그 초기화
                    });
            });
        });
    });
});

