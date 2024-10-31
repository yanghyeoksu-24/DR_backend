// 찜 삭제하시겠습니까? confirm창 띄우기 //
function confirmDelete(event) {

    const confirmDelete = confirm("찜 삭제하시겠습니까?");
    if (confirmDelete) {
        alert("찜 삭제되었습니다.");
    } else {
        event.preventDefault();
    }
}

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