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

    document.getElementById('deleteButtons').addEventListener('click', function () {
      if (confirm("정말로 삭제하시겠습니까?")) {
        alert("삭제완료되었습니다.");
      } else {
        return;
      }
    });

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

// 초기 추천 수를 0으로 설정
let recommendCount = 0;

// recommend 이미지를 클릭할 때 색상 토글 및 추천 수 증가/감소 기능을 적용
document.getElementById('recommendImg').addEventListener('click', function() {
  var recommendImg = document.getElementById('recommendImg'); // 추천 이미지 요소
  var recommendCountDisplay = document.getElementById('recommendCount'); // 추천 수 표시 요소

  // 추천 이미지가 파란색 활성화 상태인 경우
  if (recommendImg.classList.contains('recommend-active')) {
    // 파란색에서 회색으로 전환 (추천 해제)
    recommendImg.classList.remove('recommend-active');
    recommendImg.classList.add('recommend-inactive');
    if (recommendCount > 0) {
      recommendCount--; // 추천 수 감소
    }
  }
  // 회색 상태인 경우 (또는 아무 색상도 없을 경우)
  else if (recommendImg.classList.contains('recommend-inactive')) {
    // 회색에서 파란색으로 전환 (추천 추가)
    recommendImg.classList.remove('recommend-inactive');
    recommendImg.classList.add('recommend-active');
    recommendCount++; // 추천 수 증가
  }
  // 처음 상태일 때 파란색으로 전환 (기본 초록색에서 파란색으로)
  else {
    recommendImg.classList.add('recommend-active');
    recommendCount++; // 추천 수 증가
  }

  // 추천 수 업데이트
  recommendCountDisplay.innerText = `추천수: ${recommendCount}`;
});