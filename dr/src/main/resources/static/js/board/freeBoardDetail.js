// 댓글 수정 버튼을 눌렀을 때 실행되는 함수
document.querySelectorAll('.freeBoardDetail-editBtn').forEach(function (button) {
    button.addEventListener('click', function () {
        var commentContainer = button.closest('.freeBoardDetail-comment'); // 부모 댓글 컨테이너 선택
        var commentText = commentContainer.querySelector('.freeBoardDetail-commentText'); // 댓글 텍스트 선택
        var editInput = commentContainer.querySelector('.freeBoardDetail-commentInput'); // 수정 입력창 선택
        var editTextarea = commentContainer.querySelector('textarea'); // textarea 선택
        var buttonGroup = commentContainer.querySelector('.freeBoardDetail-buttonGroup'); // 버튼 그룹 선택

        // 기존 댓글 텍스트를 textarea에 삽입
        editTextarea.value = commentText.innerText;

        // 댓글 텍스트와 버튼 그룹을 숨기고 수정창을 보이게 함
        commentText.style.display = 'none';
        buttonGroup.style.display = 'none'; // 버튼 그룹 숨김
        editInput.style.display = 'block'; // 수정 입력창 보임
    });
});

// 댓글 저장 버튼을 눌렀을 때 실행되는 함수
document.querySelectorAll('.freeBoardDetail-saveBtn').forEach(function (button) {
    button.addEventListener('click', function () {
        var commentContainer = button.closest('.freeBoardDetail-comment');
        var replyNumber = commentContainer.querySelector('input[name="replyNumber"]').value;
        var editTextarea = commentContainer.querySelector('textarea').value;

        if (confirm("수정하시겠습니까?")) {
            // AJAX 요청을 통해 서버에 수정된 댓글을 전송
            $.ajax({
                type: 'POST',
                url: '/board/updateReply',  // 서버의 댓글 수정 요청 URL
                data: {
                    replyNumber: replyNumber,
                    replyText: editTextarea
                },
                success: function () {
                    // 페이지를 리다이렉트하여 수정된 댓글을 다시 불러옴
                    window.location.reload(); // 페이지를 새로고침하여 업데이트된 내용 표시
                },
                error: function () {
                    alert("댓글 수정에 실패했습니다.");
                }
            });
        }
    });
});

// 댓글 수정 취소 버튼을 눌렀을 때 실행되는 함수
function cancelEdit(replyNumber) {
    var commentContainer = document.getElementById('comment' + replyNumber); // 해당 댓글 요소 선택
    var commentText = commentContainer.querySelector('.freeBoardDetail-commentText'); // 댓글 텍스트 선택
    var editInput = commentContainer.querySelector('.freeBoardDetail-commentInput'); // 수정 입력창 선택
    var editTextarea = editInput.querySelector('textarea'); // 수정 textarea 선택
    var buttonGroup = commentContainer.querySelector('.freeBoardDetail-buttonGroup'); // 버튼 그룹 선택

    // 수정창을 숨기고, 댓글 텍스트를 다시 보이게 함
    editInput.style.display = 'none';
    commentText.style.display = 'block';
    buttonGroup.style.display = 'block'; // 버튼 그룹 다시 보이게 함

    // textarea의 값을 초기화
    editTextarea.value = ''; // 수정 전의 텍스트를 지웁니다

    // 댓글 텍스트를 원래 내용으로 복구하기
    commentText.innerText = commentText.getAttribute('data-original-text'); // data-original-text에서 원래 내용을 가져와 복구
}

// 댓글 삭제 버튼을 눌렀을 때 실행되는 함수
document.querySelectorAll('.freeBoardDetail-deleteBtn').forEach(function (button) {
    button.addEventListener('click', function () {
        var replyNumber = button.getAttribute('data-reply-number');
        deleteComment(replyNumber);
    });
});

function deleteComment(replyNumber) {
    if (confirm("정말로 이 댓글을 삭제하시겠습니까?")) {
        // AJAX 요청을 통해 서버에 댓글 삭제 요청
        $.ajax({
            type: 'POST',
            url: '/board/deleteReply', // 서버의 댓글 삭제 요청 URL
            data: { replyNumber: replyNumber },
            success: function () {
                // 댓글 요소를 제거
                var commentElement = document.getElementById('comment' + replyNumber);
                commentElement.remove();
                alert("댓글이 삭제되었습니다.");
            },
            error: function () {
                alert("댓글 삭제에 실패했습니다.");
            }
        });
    }
}
