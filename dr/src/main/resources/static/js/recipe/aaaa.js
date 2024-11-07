// 댓글 수정 버튼을 눌렀을 때 실행되는 함수
document.querySelectorAll('.myDetailPage-editBtn').forEach(function (button) {
    button.addEventListener('click', function () {
        var commentContainer = button.closest('.myDetailPage-comment'); // 부모 댓글 컨테이너 선택
        var commentText = commentContainer.querySelector('.myDetailPage-commentText'); // 댓글 텍스트 선택
        var editInput = commentContainer.querySelector('.myDetailPage-commentInput'); // 수정 입력창 선택
        var editTextarea = editInput.querySelector('textarea'); // textarea 선택
        var buttonGroup = commentContainer.querySelector('.myDetailPage-buttonGroup'); // 버튼 그룹 선택

        // 댓글 원본 텍스트를 data-original-text 속성에 저장
        commentText.setAttribute('data-original-text', commentText.innerText);


        // 기존 댓글 텍스트를 textarea에 삽입
        editTextarea.value = commentText.innerText;

        // 댓글 텍스트와 버튼 그룹을 숨기고 수정 입력창을 보이게 함
        commentText.style.display = 'none';
        buttonGroup.style.display = 'none';
        editInput.style.display = 'block';
    });
});

// 댓글 저장 버튼을 눌렀을 때 실행되는 함수
document.querySelectorAll('.myDetailPage-saveBtn').forEach(function (button) {
    button.addEventListener('click', function () {
        var commentContainer = button.closest('.myDetailPage-comment'); // 댓글 컨테이너 선택
        var replyNumber = commentContainer.querySelector('input[name="replyNumber"]').value; // 댓글 번호
        var editTextarea = commentContainer.querySelector('textarea').value; // 수정된 댓글 내용

        if (confirm("수정하시겠습니까?")) {
            // AJAX 요청을 통해 서버에 수정된 댓글을 전송
            $.ajax({
                type: 'POST',
                url: '/recipe/updateReply',  // 서버의 댓글 수정 요청 URL
                data: {
                    replyNumber: replyNumber,
                    replyText: editTextarea
                },
                success: function () {
                    // 페이지를 리다이렉트하여 수정된 댓글을 다시 불러옴
                    window.location.reload(); // 페이지를 새로고침하여 업데이트된 내용 표시
                },
                error: function (xhr, status, error) {
                    alert("댓글 수정에 실패했습니다.");
                }
            });
        }
    });
});

// 댓글 수정 취소 버튼을 눌렀을 때 실행되는 함수
document.querySelectorAll('.myDetailPage-cancelBtn').forEach(function (button) {
    button.addEventListener('click', function () {
        let boardNumber = document.querySelector('[name="recipeNumber"]').value;  // boardNumber 값 가져오기
        window.location.href = '/recipe/myDetailPage?recipeNumber=' + recipeNumber;  // detail 페이지로 리다이렉트
    });
});
