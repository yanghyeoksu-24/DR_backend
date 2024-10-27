const buttons = document.querySelectorAll('.myRecipeList-rightButton button');

// 기본적으로 첫 번째 버튼에 스타일을 적용합니다.
buttons[0].classList.add('active');

// 버튼 클릭 시 이벤트 핸들러
buttons.forEach(button => {
    button.addEventListener('click', function() {
        // 모든 버튼에서 active 클래스를 제거합니다.
        buttons.forEach(btn => btn.classList.remove('active'));

        // 클릭한 버튼에 active 클래스를 추가합니다.
        this.classList.add('active');
    });
});

$(function () {
    const items = $('.myRecipeList-outbox .myRecipeList-listbox'); // 게시글 항목 선택

    // 처음 20개 항목만 보이게 하고 나머지는 숨김
    items.hide().slice(0, 20).show(); // 첫 20개 항목만 표시

    // 페이지네이션 설정
    const container = $('#pagination');
    container.pagination({
        dataSource: items.toArray(), // 게시글 항목을 배열로 변환
        pageSize: 20, // 한 페이지에 보여줄 항목 수 (20개)
        callback: function (data, pagination) {
            items.hide(); // 기존에 보여진 항목 숨김
            $.each(data, function (index, item) {
                $(item).show(); // 현재 페이지에 해당하는 항목만 표시
            });
        }
    });

    // 처음 로드 시 첫 번째 페이지의 항목만 보여주기
    container.pagination('goToPage', 1);
});


