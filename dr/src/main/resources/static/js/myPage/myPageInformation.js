// --------사진 이미지 업로드 ------- //  
// 수레바퀴 이미지를 클릭하면 파일 업로드 창이 열림
document.getElementById('uploadIcon').addEventListener('click', function() {
    // 숨겨진 input 요소를 클릭하여 파일 선택 창을 염
    document.getElementById('imageUpload').click();
});

// 파일이 선택되면 이미지를 변경하는 함수
document.getElementById('imageUpload').addEventListener('change', function(event) {
    // 선택한 파일을 가져옴
    const file = event.target.files[0];
    if (file) { // 파일이 선택된 경우
        const reader = new FileReader(); // FileReader 객체 생성
        reader.onload = function(e) {
            // 미리 보기 이미지 업데이트
            document.getElementById('profileImage').src = e.target.result;
        };
        // 선택한 파일을 Data URL 형식으로 읽음
        reader.readAsDataURL(file);
    }
});


// ------ 수정 완료 alert 창 ----- //
document.getElementById('completeWriteBtn').addEventListener('click', function() {
    // 수정 완료 여부를 확인하는 확인 창 표시
    if (confirm('정말 수정하시겠습니까?')) {
        // 수정 완료 알림 표시
        alert('수정이 완료되었습니다');
    }
});

// ---- 닉네임 변경 ---- //

// DOM 요소 가져오기
const nicknameDisplay = document.getElementById('nicknameDisplay');
const nicknameInput = document.getElementById('nicknameInput');
const nicknameChangeButton = document.querySelector('.myPage-nickNameChange');
const nicknameError = document.getElementById('nicknameError');

// 닉네임 텍스트 클릭 시 input 필드 보여주기
nicknameDisplay.addEventListener('click', function() {
    nicknameDisplay.style.display = 'none'; // 기존 닉네임 텍스트 숨기기
    nicknameInput.style.display = 'inline'; // 인풋 필드 보여주기
    nicknameInput.value = nicknameDisplay.textContent; // input 필드에 현재 닉네임 값 설정
    nicknameInput.focus(); // 인풋 필드에 자동 포커스
    nicknameError.style.display = 'none'; // 에러 메시지 숨기기
    nicknameInput.style.border = '1px solid #317FF0'; // 원래 스타일로 설정
});

// 실시간 닉네임 유효성 검사
nicknameInput.addEventListener('input', function() {
    const newNickname = nicknameInput.value.trim(); // 입력 값 가져오기
    const nicknameRegex = /^[가-힣a-zA-Z0-9]{2,5}$/; // 2자 이상 5자 이내의 한글, 영문, 숫자

    // 닉네임 유효성 검사
    if (!nicknameRegex.test(newNickname) || newNickname.length < 2) {
        nicknameError.style.display = 'block'; // 에러 메시지 보여주기
        nicknameInput.style.border = '1px solid red'; // 빨간색 테두리
    } else {
        nicknameError.style.display = 'none'; // 에러 메시지 숨기기
        nicknameInput.style.border = '1px solid #317FF0'; // 원래 스타일로 되돌리기
    }
});

// 닉네임 변경 버튼 클릭 시 유효성 검사 및 닉네임 변경
nicknameChangeButton.addEventListener('click', function() {
    const newNickname = nicknameInput.value.trim(); // 인풋 필드의 값 가져오기 및 앞뒤 공백 제거
    const nicknameRegex = /^[가-힣a-zA-Z0-9]{2,5}$/; // 2자 이상 5자 이내의 한글, 영문, 숫자

    // 닉네임 유효성 검사
    if (!nicknameRegex.test(newNickname)) {
        nicknameError.style.display = 'block'; // 에러 메시지 보여주기
        nicknameInput.style.border = '1px solid red'; // 빨간색 테두리
    } else {
        nicknameDisplay.textContent = newNickname; // 닉네임 텍스트 업데이트
        nicknameDisplay.style.display = 'inline'; // 닉네임 텍스트 보여주기
        nicknameInput.style.display = 'none'; // 인풋 필드 숨기기
        nicknameError.style.display = 'none'; // 에러 메시지 숨기기
        nicknameInput.style.border = '1px solid #317FF0'; // 원래 스타일로 되돌리기 (파란색 테두리)
        alert('수정완료 클릭 시 최종 변경됩니다.');
    }
});

// 페이지 전체에 클릭 이벤트 추가 (다른 곳 클릭하면 인풋창 닫기)
document.addEventListener('click', function(event) {
    // 클릭한 곳이 인풋창이나 닉네임 텍스트 또는 변경 버튼이 아니면 인풋창을 숨김
    if (!nicknameInput.contains(event.target) && !nicknameDisplay.contains(event.target) && !nicknameChangeButton.contains(event.target)) {
        nicknameInput.style.display = 'none'; // 인풋 필드 숨기기
        nicknameDisplay.style.display = 'inline'; // 닉네임 텍스트 보여주기
        nicknameError.style.display = 'none'; // 에러 메시지 숨기기
        nicknameInput.style.border = '1px solid #317FF0'; // 원래 테두리로 되돌리기
    }
});