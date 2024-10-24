$(document).ready(function () {
  function setupMenuToggle(menuId, listClass) {
    // 메뉴 클릭 시 하위 메뉴 토글
    $(menuId).on('click', function (event) {
      // 다른 모든 하위 메뉴 닫기
      $('.main-recipeList, .main-boardList, .main-myPageList').not(listClass).slideUp();

      // 현재 하위 메뉴 토글
      $(listClass).slideToggle();

      event.stopPropagation(); // 클릭 이벤트 전파 방지
    });
  }

  // 각 메뉴에 대해 함수 호출
  setupMenuToggle('#main-recipe', '.main-recipeList');
  setupMenuToggle('#main-board', '.main-boardList');
  setupMenuToggle('#main-myPage', '.main-myPageList');

  // 다른 곳 클릭 시 모든 하위 메뉴 닫기
  $(document).on('click', function () {
    $('.main-recipeList, .main-boardList, .main-myPageList').slideUp(); // 모든 하위 메뉴 닫기
  });

  // header-goLogin 클래스를 가진 요소에 클릭 이벤트 추가
  $('.header-goLogin').on('click', function (event) {
    event.preventDefault(); // 기본 링크 이동 방지
    alert('로그인이 필요합니다'); // 알림 메시지 표시
    window.location.href = $(this).attr('href'); // 로그인 페이지로 이동
  });

  // 로그아웃 안내창
  $('.main-logout').on('click', function () {
    let check = confirm('로그아웃 하시겠습니까?');
    if (check) {
      alert = ('로그아웃 되었습니다');
    } else {
      return;
    }
  })
});
