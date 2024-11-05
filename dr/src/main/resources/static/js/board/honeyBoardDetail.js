// 댓글 수정 버튼을 눌렀을 때 실행되는 함수
function editComment(commentId) {
  var commentText = document.getElementById('commentText' + commentId);
  var editInput = document.getElementById('editInput' + commentId);
  var editTextarea = document.getElementById('editTextarea' + commentId);
  var buttonGroup = document.getElementById('buttonGroup' + commentId); // 수정/삭제 버튼 그룹

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
  var buttonGroup = document.getElementById('buttonGroup' + commentId); // 수정/삭제 버튼 그룹

  // "수정하시겠습니까?" 확인 창
  if (confirm("수정하시겠습니까?")) {
    // 수정된 내용을 댓글 텍스트에 적용
    commentText.innerText = editTextarea.value;

    // 수정창을 숨기고, 댓글 텍스트를 다시 보이게 함
    editInput.style.display = 'none';
    commentText.style.display = 'block';

    // 수정/삭제 버튼 다시 보이게 함
    buttonGroup.style.display = 'flex';

    // "수정완료되었습니다" 알림 창
    alert("수정완료되었습니다.");
  } else {
    // 사용자가 수정 확인을 취소한 경우
    cancelEdit(commentId);
  }
}

// 댓글 수정 취소 버튼을 눌렀을 때 실행되는 함수
function cancelEdit(commentId) {
  var commentText = document.getElementById('commentText' + commentId);
  var editInput = document.getElementById('editInput' + commentId);
  var buttonGroup = document.getElementById('buttonGroup' + commentId); // 수정/삭제 버튼 그룹

  // 수정창을 숨기고, 댓글 텍스트를 다시 보이게 함
  editInput.style.display = 'none';
  commentText.style.display = 'block';

  // 수정/삭제 버튼 다시 보이게 함
  buttonGroup.style.display = 'flex';
}


// 글 삭제 버튼 클릭 시 동작
document.getElementById('deleteButton').addEventListener('click', function(event) {
  event.preventDefault(); // 기본 동작(페이지 이동)을 막습니다.

  // 삭제 확인 alert 창을 띄움
  // alert("정말로 삭제하시겠습니까?");

  // 사용자가 확인을 눌렀을 경우에만 삭제 동작 수행
  if (confirm("정말로 삭제를 하시겠습니까?")) {
    // 삭제 완료 후 게시글 목록으로 이동
    window.location.href = "./../board/honeyBoardList.html";
  } else {
    // 사용자가 취소를 누른 경우
    alert("삭제가 취소되었습니다.");
  }
});


function submitComment() {
  const confirmation = confirm("댓글을 등록하시겠습니까?");
  if (confirmation) {
      // 댓글 등록 로직을 여기에 추가하세요.
      alert("댓글이 등록되었습니다."); // 예시: 댓글 등록 후 알림
  } else {
      // 취소 시 아무것도 하지 않음
      return;
  }
}

function submitComment() {
  const confirmation = confirm("댓글을 등록하시겠습니까?");
  if (confirmation) {
      // 댓글 등록 로직을 여기에 추가하세요.
      alert("댓글이 등록되었습니다."); // 예시: 댓글 등록 후 알림
  } else {
      // 취소 시 아무것도 하지 않음
      return;
  }
}


document.addEventListener("DOMContentLoaded", function () {
    const recommendImg = document.getElementById("recommendImg");
    const boardNumber = document.getElementById("boardNumber").value;

    // 저장된 추천 상태 로드
    let isActive = localStorage.getItem(`recommend-active-${boardNumber}`) === 'true'; // boolean으로 변환
    recommendImg.classList.toggle("recommend-active", isActive);
    recommendImg.classList.toggle("recommend-inactive", !isActive);

    let isRequestInProgress = false; // 요청 진행 상태

    recommendImg.addEventListener("click", function () {
        if (isRequestInProgress) return; // 요청이 진행 중이면 클릭 무시

        isRequestInProgress = true; // 요청 시작
        const url = isActive ? "/board/goodMinus" : "/board/goodPlus"; // 현재 상태에 따라 URL 결정

        fetch(url, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ boardNumber: boardNumber })
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error("요청 실패: " + response.statusText);
                }
                // 상태 토글
                isActive = !isActive;
                recommendImg.classList.toggle("recommend-active", isActive);
                recommendImg.classList.toggle("recommend-inactive", !isActive);

                // 상태 저장
                localStorage.setItem(`recommend-active-${boardNumber}`, isActive); // 상태 저장

                location.reload(); // 페이지를 새로 고침하여 추천 수 업데이트
            })
            .catch(error => {
                console.error("Error:", error);
                alert("요청 처리 중 오류가 발생했습니다.");
            })
            .finally(() => {
                isRequestInProgress = false; // 요청 완료
            });
    });
});

