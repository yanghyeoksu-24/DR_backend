// $(document).ready(function () {
//     // 신고 버튼 클릭 이벤트
//     $('.right-button').on('click', function (event) {
//       event.preventDefault(); // 기본 동작 방지
//
//       // 체크박스 상태 확인
//       const isChecked = $('input[name="reason"]:checked').length > 0;
//
//       if (!isChecked) {
//         // 체크된 신고 사유가 없을 경우 경고 메시지
//         alert("신고사유를 체크해주세요.");
//         return; // 신고 절차 중단
//       }
//
//       // 신고 확인 메시지
//       const confirmReport = confirm("정말로 신고하시겠습니까?");
//
//       if (confirmReport) {
//         // 신고 진행: 원하는 URL로 이동
//         alert("신고가 완료되었습니다.");
//         window.location.href = './../recipe/myDetailPage.html'; // 신고 후 이동할 페이지
//       } else {
//         // 신고 취소
//         alert("신고가 취소되었습니다.");
//       }
//     });
//   });
//