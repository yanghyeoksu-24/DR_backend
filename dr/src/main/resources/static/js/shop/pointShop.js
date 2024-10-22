$(document).ready(function () {
  let currentPosition = 10; // 슬라이드 시작 위치를 10px로 설정
  const $productFrame = $('.pointShop-productFrame'); // 보이는 영역
  const $productList = $('.pointShop-productList'); // 전체 상품 리스트
  const $products = $('.pointShop-product'); // 각각의 상품들
  const productWidth = $products.outerWidth(true); // 상품 하나의 너비 (마진 포함)
  const visibleCount = Math.floor($productFrame.width() / productWidth); // 한 번에 보이는 상품 개수
  const totalProducts = $products.length; // 총 상품 개수

  // 최대 이동할 수 있는 위치: 보이지 않는 상품들만큼 이동 가능
  const maxPosition = 10 - productWidth * (totalProducts - visibleCount);

  // 왼쪽 버튼 클릭 시
  $('.pointShop-leftBtn').click(function () {
    if (currentPosition < 10) { // 10px 기준으로 왼쪽 끝을 넘어가지 않도록 제한
      currentPosition += productWidth; // 한 상품 너비만큼 오른쪽으로 이동
      $productList.animate({ left: currentPosition + 'px' }, 300); // 애니메이션으로 이동
    }
  });

  // 오른쪽 버튼 클릭 시
  $('.pointShop-rightBtn').click(function () {
    if (currentPosition > maxPosition) { // maxPosition을 넘어가지 않도록 제한
      currentPosition -= productWidth; // 한 상품 너비만큼 왼쪽으로 이동
      $productList.animate({ left: currentPosition + 'px' }, 300); // 애니메이션으로 이동
    }
  });

  // 상품구매 클릭 시 갯수 검사 후 구매 완료 alert
  $('.pointShop-buyBtn').click(function (e) {
    e.preventDefault(); // 기본 폼 제출 방지
    const quantityInput = $(this).siblings('div').find('.pointShop-buyEa'); // 버튼의 형제 요소에서 input 찾기
    const quantity = parseInt(quantityInput.val(), 10); // input 값 가져오기

    if (quantity <= 0) {
      alert('0 이하는 구매할 수 없습니다');
    } else if (isNaN(quantity)) {
      alert('구매 갯수를 입력해 주세요');
    }
    else {
      const confirmPurchase = confirm('정말로 구매 하시겠습니까?\n해당 포인트가 차감됩니다');
      if (confirmPurchase) {
        // 실제 구매 로직을 여기서 진행
        alert(`${quantity}개 구매 완료!`);
      }
    }
  });
});