// 모든 '찜 삭제' 버튼에 대해 이벤트 리스너 추가
document.addEventListener("DOMContentLoaded", function() {
    // DOM이 완전히 로드된 후 실행되는 코드 블록

    // 모든 찜 삭제 버튼을 선택
    const deleteButtons = document.querySelectorAll(".myPage-pictureListDelete");

    // 각 버튼에 클릭 이벤트 추가
    deleteButtons.forEach(function(button) {
        // 버튼 클릭 시 실행되는 이벤트 리스너 추가
        button.addEventListener("click", function() {
            // 삭제 확인을 위한 확인 창 표시
            const result = confirm("찜 삭제하시겠습니까?");
            if (result) { // 사용자가 "확인"을 클릭한 경우
                alert("삭제되었습니다."); // 삭제 완료 알림 표시
            } else { // 사용자가 "취소"를 클릭한 경우
                alert("삭제가 취소되었습니다."); // 삭제 취소 알림 표시
            }
        });
    });
});


$(function() {
    const items = $('.myPage-pictureprideOne'); // 게시글 항목 선택

    // 처음 9개 항목만 보이게 하고 나머지는 숨김
    items.hide().slice(0, 9).show(); // 첫 9개 항목만 표시

    // 페이지네이션 설정
    const container = $('#pagination');
    container.pagination({
        dataSource: items.toArray(), // 게시글 항목을 배열로 변환
        pageSize: 9, // 한 페이지에 보여줄 항목 수 (9개씩)
        callback: function(data, pagination) {
            items.hide(); // 기존에 보여진 항목 숨김
            $.each(data, function(index, item) {
                $(item).show(); // 현재 페이지에 해당하는 항목만 표시
            });
        }
    });

    // 처음 로드 시 첫 번째 페이지의 항목만 보여주기
    container.pagination('goToPage', 1);
});