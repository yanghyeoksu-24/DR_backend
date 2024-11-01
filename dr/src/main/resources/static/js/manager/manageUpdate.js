// + 버튼에 대한 클릭 이벤트 설정
document.getElementById('numberUp').addEventListener('click', function () {
    // + 버튼이 있는 네 번째 div 선택 (네 번째 .register-right-con)
    const targetDiv = document.querySelectorAll('.register-right-con')[2];  // 4번째 div 선택

    // 현재 div 안에 있는 input 태그들의 수를 확인
    const inputCount = targetDiv.querySelectorAll('input').length;

    // input이 10개 이상이면 추가되지 않도록 설정
    if (inputCount >= 10) {
        alert('최대 10개의 입력 필드만 추가할 수 있습니다.');
        return;  // 추가 작업 중단
    }

    // 새로운 input 요소 생성
    const newInput = document.createElement('input');
    newInput.setAttribute('type', 'text');
    newInput.setAttribute('name', 'productCode');  // 동일한 name 속성 설정

    // 새로 생성한 input 태그를 div의 마지막에 추가
    targetDiv.appendChild(newInput);
});



// 수정 버튼 클릭 시 AJAX 요청
document.getElementById('memberUpdate').addEventListener('click', submitSelected);

function submitSelected() {
    const productName = document.getElementById('productName').value;
    const productPrice = document.getElementById('productPrice').value;

    // 상품 코드가 입력되지 않았을 경우 경고 메시지
    if (!productCode) {
        alert('상품 코드를 입력하세요.');
        return;
    }

    // AJAX 요청
    $.ajax({
        type: 'POST', // 요청 방식
        url: '/manager/manageUpdate', // 요청 URL
        data: {
            productName: productName,
            productPrice: productPrice,
        },
        success: function (response) {
            alert('수정이 완료되었습니다.'); // 성공 메시지
            window.location.href = '/manager/manageProduct'; // 적절한 페이지로 리다이렉트
        },
        error: function (xhr, status, error) {
            alert('오류가 발생했습니다: ' + error); // 오류 메시지
        }
    });
}