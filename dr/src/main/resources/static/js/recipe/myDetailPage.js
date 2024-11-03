const heartImage = document.getElementById('heartImage');

heartImage.addEventListener('click', function() {
  if (heartImage.src.includes('heartGray.png')) {
    heartImage.src = './../../image/heartColor.png'; // 빨간 하트 이미지 경로
  } else {
    heartImage.src = './../../image/heartGray.png'; // 검정 하트 이미지 경로
  }
});

document.getElementById('deleteButton').addEventListener('click', function () {
  if (confirm("정말로 삭제하시겠습니까?")) {
    alert("삭제완료되었습니다.");
  } else {
    return;
  }
});

// 댓글 등록 버튼 클릭 시
function submitComment() {
  const confirmation = confirm("댓글을 등록하시겠습니까?");
  if (confirmation) {
    alert("댓글이 등록되었습니다.");
  } else {
    return;
  }
}

// 댓글 수정 기능
function editComment(commentId) {
  var commentText = document.getElementById('commentText' + commentId);
  var editInput = document.getElementById('editInput' + commentId);
  var editTextarea = document.getElementById('editTextarea' + commentId);
  var buttonGroup = document.getElementById('buttonGroup' + commentId);

  editTextarea.value = commentText.innerText;

  commentText.style.display = 'none';
  editInput.style.display = 'block';
  buttonGroup.style.display = 'none';
}

function saveComment(commentId) {
  var commentText = document.getElementById('commentText' + commentId);
  var editInput = document.getElementById('editInput' + commentId);
  var editTextarea = document.getElementById('editTextarea' + commentId);
  var buttonGroup = document.getElementById('buttonGroup' + commentId);

  if (confirm("수정하시겠습니까?")) {
    commentText.innerText = editTextarea.value;

    editInput.style.display = 'none';
    commentText.style.display = 'block';
    buttonGroup.style.display = 'flex';

    alert("수정완료되었습니다.");
  } else {
    cancelEdit(commentId);
  }
}

function cancelEdit(commentId) {
  var commentText = document.getElementById('commentText' + commentId);
  var editInput = document.getElementById('editInput' + commentId);
  var buttonGroup = document.getElementById('buttonGroup' + commentId);

  editInput.style.display = 'none';
  commentText.style.display = 'block';
  buttonGroup.style.display = 'flex';
}

// 댓글 삭제 기능
function deleteComment(commentId) {
  if (confirm("정말로 삭제하시겠습니까?")) {
    alert("댓글이 삭제되었습니다.");
    // AJAX 요청 또는 폼 제출 로직을 추가하여 서버에서 댓글을 삭제하도록 구현할 수 있습니다.
  }
}
