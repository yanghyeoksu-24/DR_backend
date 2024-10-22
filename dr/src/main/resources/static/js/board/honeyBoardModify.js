$(document).ready(function() {
  // 수정 버튼 클릭 시 확인창 표시
  $('.honeyboardmodify-writeButton').on('click', function(e) {
    // "수정" 버튼에만 적용
    if ($(this).text() === "수정") {
      e.preventDefault(); // 기본 동작(페이지 이동) 방지

      // 확인 창 띄우기
      if (confirm("수정하시겠습니까?")) {
        // 사용자가 "확인"을 누르면 해당 페이지로 이동
        window.location.href = './honeyBoardDetail.html';
      } else {
        // 사용자가 "취소"를 누르면 아무 동작도 하지 않음
        return false;
      }
    }
  });
});
