// DOMContentLoaded 이벤트: DOM이 완전히 로드된 후 이벤트 리스너 추가
document.addEventListener("DOMContentLoaded", function() {
    // + 버튼에 대한 클릭 이벤트 설정
    const numberUpButton = document.getElementById('numberUp');
    if (numberUpButton) {
        numberUpButton.addEventListener('click', addInputField);
    }

});

// + 버튼 클릭 시 새로운 input 필드를 추가하는 함수
function addInputField() {
    // 네 번째 .register-right-con div 선택 (querySelectorAll로 3번째 인덱스 선택)
    const targetDiv = document.querySelectorAll('.register-right-con')[2];

    // 현재 div 내 input 필드 개수를 확인
    const inputCount = targetDiv.querySelectorAll('input').length;

    // input 필드가 10개 이상이면 추가되지 않도록 함
    if (inputCount >= 10) {
        alert('최대 10개의 입력 필드만 추가할 수 있습니다.');
        return;
    }

    // 새로운 input 요소 생성 및 속성 설정
    const newInput = document.createElement('input');
    newInput.setAttribute('type', 'text');
    newInput.setAttribute('name', 'productCode');  // 동일한 name 속성 설정

    // 생성한 input 태그를 div의 마지막에 추가
    targetDiv.appendChild(newInput);
}
