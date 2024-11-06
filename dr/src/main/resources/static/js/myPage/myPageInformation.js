// --------사진 이미지 업로드 ------- //

// 수레바퀴 이미지를 클릭하면 파일 업로드 창이 열림
document.getElementById('uploadIcon').addEventListener('click', function() {
    document.getElementById('imageUpload').click();
});

// 파일이 선택되면 이미지를 변경하는 함수
document.getElementById('imageUpload').addEventListener('change', function(event) {
    const file = event.target.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = function(e) {
            document.getElementById('profileImage').src = e.target.result + `?timestamp=${Date.now()}`;
        };
        reader.readAsDataURL(file);
    }
});

// ------ 수정 완료 alert 창 ----- //
document.getElementById('completeWriteBtn').addEventListener('click', function() {
    if (!confirm('정말 수정하시겠습니까?')) {
        return;
    }

    const nickname = document.getElementById('nicknameInput').value.trim();
    const profileImage = document.getElementById('imageUpload').files[0];

    const nicknameRegex = /^[a-zA-Z0-9가-힣]{2,5}$/;
    if (!nicknameRegex.test(nickname)) {
        alert('닉네임을 다시 확인해주세요.');
        return;
    }

    const formData = new FormData();
    formData.append('nickname', nickname);

    // 프로필 이미지가 있는 경우에만 FormData에 추가
    if (profileImage) {
        formData.append('profileImage', profileImage);
    }

    fetch('/myPage/updateProfile', {
        method: 'POST',
        body: formData,
    })
        .then(response => response.json())
        .then(data => {
            if (data.photoPath) {
                // 서버에서 반환된 photoPath로 이미지 URL을 업데이트
                document.getElementById('profileImage').src = `/static/image/photo/${data.photoPath}?timestamp=${Date.now()}`;
                alert('수정이 완료되었습니다.\n상단 프로필은 다음 로그인 시 적용됩니다.');
            }
        })
        .catch(error => {
            console.error('수정 요청 중 오류가 발생했습니다:', error);
            alert('닉네임 또는 이미지를 다시 확인해주세요.');
        });
});

// DOM 요소 가져오기
const nicknameDisplay = document.getElementById('nicknameDisplay');
const nicknameInput = document.getElementById('nicknameInput');
const nicknameChangeButton = document.querySelector('.myPage-nickNameChange');
const nicknameError = document.getElementById('nicknameError');

// 닉네임 텍스트 클릭 시 input 필드 보여주기
nicknameDisplay.addEventListener('click', function() {
    nicknameDisplay.style.display = 'none';
    nicknameInput.style.display = 'inline';
    nicknameInput.value = nicknameDisplay.textContent;
    nicknameInput.focus();
    nicknameError.style.display = 'none';
    nicknameInput.style.border = '1px solid #317FF0';
});

// 닉네임 변경 클릭 시 input 필드 보여주기
nicknameChangeButton.addEventListener('click', function() {
    nicknameDisplay.style.display = 'none';
    nicknameInput.style.display = 'inline'; // 입력 필드 보이기
    nicknameInput.focus(); // 입력 필드에 포커스 주기
    nicknameInput.style.border = '1px solid #317FF0'; // 입력 필드 테두리 색상 변경
});



// 닉네임 중복 확인 및 유효성 검사 //
nicknameInput.addEventListener('blur', function() {
    const newNickname = nicknameInput.value.trim(); // 입력 값 가져오기
    const currentNickname = nicknameDisplay.textContent.trim(); // 현재 닉네임 가져오기

    // 닉네임 유효성 검사
    const nicknameRegex = /^[a-zA-Z0-9가-힣]{2,5}$/; // 2자 이상 5자 이내의 한글, 영문, 숫자
    if (!nicknameRegex.test(newNickname)) {
        nicknameError.textContent = "2자 이상 5자 이내의 한글, 영문, 숫자만 허용됩니다.";
        nicknameError.style.display = 'block';
        nicknameError.style.marginLeft = '13px';
        nicknameInput.style.border = '1px solid red';
        nicknameError.style.color = 'red'; // 유효성 검사 실패 시 빨간색 표시
        return;
    }

    // 현재 닉네임과 비교
    if (newNickname === currentNickname) {
        nicknameError.textContent = "현재 닉네임과 동일합니다.";
        nicknameError.style.display = 'block';
        nicknameError.style.marginLeft = '13px';
        nicknameInput.style.border = '1px solid orange'; // 주의 색상 표시
        nicknameError.style.color = 'orange'; // 주의 색상
        return;
    }

    // 닉네임이 비어있지 않은 경우에만 중복 확인 요청
    if (newNickname) {
        fetch('/myPage/checkNickname', {
            method: 'POST', // POST 메서드로 요청
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ nickname: newNickname })
        })
            .then(response => response.json()) // boolean 값을 JSON 형태로 받음
            .then(isDuplicate => {
                if (isDuplicate) {
                    nicknameError.textContent = "이미 사용 중인 닉네임입니다.";
                    nicknameError.style.marginLeft = '13px';
                    nicknameError.style.display = 'block';
                    nicknameError.style.color = 'red'; // 빨간색으로 변경
                    nicknameInput.style.border = '1px solid red'; // 빨간색 테두리
                } else {
                    nicknameError.textContent = "닉네임 사용 가능합니다.";
                    nicknameError.style.marginLeft = '13px';
                    nicknameError.style.display = 'block';
                    nicknameError.style.color = 'green'; // 초록색으로 유지
                    nicknameInput.style.border = '1px solid #317FF0'; // 정상 테두리
                }
            })
            .catch(error => {
                console.error('다시 입력해주세요:', error);
            });
    }
});
