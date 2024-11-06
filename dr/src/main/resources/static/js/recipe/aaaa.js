// 댓글 수정 버튼을 눌렀을 때 실행되는 함수
document.querySelectorAll('.myDetailPage-editBtn').forEach(function (button) {
    button.addEventListener('click', function () {
        var commentContainer = button.closest('.myDetailPage-comment'); // 부모 댓글 컨테이너 선택
        var commentText = commentContainer.querySelector('.myDetailPage-commentText'); // 댓글 텍스트 선택
        var editInput = commentContainer.querySelector('.myDetailPage-commentInput'); // 수정 입력창 선택
        var editTextarea = editInput.querySelector('textarea'); // textarea 선택
        var buttonGroup = commentContainer.querySelector('.myDetailPage-buttonGroup'); // 버튼 그룹 선택

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
                    // 수정 후에 수정 입력창을 숨기고, 댓글 텍스트를 업데이트
                    commentContainer.querySelector('.myDetailPage-commentText').innerText = editTextarea;
                    commentContainer.querySelector('.myDetailPage-commentText').style.display = 'block';
                    commentContainer.querySelector('.myDetailPage-commentInput').style.display = 'none';
                    commentContainer.querySelector('.myDetailPage-buttonGroup').style.display = 'block';
                },
                error: function (xhr, status, error) {
                    console.error("오류 발생: " + error);
                    alert("댓글 수정에 실패했습니다.");
                }
            });
        }
    });
});

// 댓글 수정 취소 버튼을 눌렀을 때 실행되는 함수
document.querySelectorAll('.myDetailPage-cancelBtn').forEach(function (button) {
    button.addEventListener('click', function () {
        var commentContainer = button.closest('.myDetailPage-comment');
        var commentText = commentContainer.querySelector('.myDetailPage-commentText');
        var editInput = commentContainer.querySelector('.myDetailPage-commentInput');
        var editTextarea = editInput.querySelector('textarea');
        var buttonGroup = commentContainer.querySelector('.myDetailPage-buttonGroup');

        // 수정창을 숨기고, 댓글 텍스트와 버튼 그룹을 다시 보이게 함
        editInput.style.display = 'none';
        commentText.style.display = 'block';
        buttonGroup.style.display = 'block';
        editTextarea.value = ''; // textarea 내용을 비움
    });
});
