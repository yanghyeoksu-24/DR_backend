// 댓글 등록 함수
function submitComment() {
  if (confirm("댓글을 등록하시겠습니까?")) {
    document.getElementById("commentForm").submit();
    alert("댓글이 등록되었습니다.");
  }
}

// 댓글 수정 버튼을 눌렀을 때 실행되는 함수
function editComment(commentId) {
  var commentText = document.getElementById('commentText' + commentId);
  var editInput = document.getElementById('editInput' + commentId);
  var editTextarea = document.getElementById('editTextarea' + commentId);
  var buttonGroup = document.getElementById('buttonGroup' + commentId);

  // 기존 댓글 텍스트를 textarea에 삽입
  editTextarea.value = commentText.innerText;

  // 댓글 텍스트를 숨기고 수정창을 보이게 함
  commentText.style.display = 'none';
  editInput.style.display = 'block';

  // 수정/삭제 버튼 숨김
  buttonGroup.style.display = 'none';
}

// 댓글 저장 버튼을 눌렀을 때 실행되는 함수
function saveComment(commentId) {
  var commentText = document.getElementById('commentText' + commentId);
  var editInput = document.getElementById('editInput' + commentId);
  var editTextarea = document.getElementById('editTextarea' + commentId);
  var buttonGroup = document.getElementById('buttonGroup' + commentId);

  if (confirm("수정하시겠습니까?")) {
    // 수정된 내용을 댓글 텍스트에 적용
    commentText.innerText = editTextarea.value;

    // 수정창을 숨기고, 댓글 텍스트를 다시 보이게 함
    editInput.style.display = 'none';
    commentText.style.display = 'block';

    // 수정/삭제 버튼 다시 보이게 함
    buttonGroup.style.display = 'flex';
    alert("수정완료되었습니다.");
  } else {
    cancelEdit(commentId);
  }
}

// 댓글 수정 취소 버튼을 눌렀을 때 실행되는 함수
function cancelEdit(commentId) {
  var commentText = document.getElementById('commentText' + commentId);
  var editInput = document.getElementById('editInput' + commentId);
  var buttonGroup = document.getElementById('buttonGroup' + commentId);

  // 수정창을 숨기고, 댓글 텍스트를 다시 보이게 함
  editInput.style.display = 'none';
  commentText.style.display = 'block';

  // 수정/삭제 버튼 다시 보이게 함
  buttonGroup.style.display = 'flex';
}

// 댓글 삭제 버튼을 눌렀을 때 실행되는 함수
function deleteComment(commentId) {
  if (confirm("정말로 삭제하시겠습니까?")) {
    // 삭제 완료 후 알림
    alert("댓글이 삭제되었습니다.");
    document.getElementById('comment' + commentId).remove();
  }
}
