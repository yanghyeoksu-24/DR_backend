// + 버튼에 대한 클릭 이벤트 설정
document.getElementById('numberUp').addEventListener('click', function() {
  const targetDiv = document.querySelectorAll('.register-right-con')[1];  // 3번째 div 선택

  const inputCount = targetDiv.querySelectorAll('input').length;

  if (inputCount >= 10) {
    alert('최대 10개의 입력 필드만 추가할 수 있습니다.');
    return;
  }

  const newInput = document.createElement('input');
  newInput.setAttribute('type', 'text');
  newInput.setAttribute('name', 'productCode[]');  // 배열 형태로 name 속성 설정

  targetDiv.appendChild(newInput);
});

// 상품 등록 버튼 클릭 이벤트
document.querySelector("#productButton").addEventListener('click', function() {
  const productName = document.querySelector('input[name="productName"]').value;
  const productCodes = Array.from(document.querySelectorAll('input[name="productCode[]"]'))  // 수정된 부분
      .map(input => input.value)
      .filter(code => code.trim() !== "");  // 빈 값 제거

  const productPrice = document.querySelector('input[name="productPoint"]').value;

  const formData = productCodes.map(code => ({
    productName: productName,
    productCode: code,
    productPrice: productPrice
  }));

  fetch('/manager/registerProduct', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(formData)
  })
      .then(response => {
        if (!response.ok) {
          throw new Error("등록에 실패했습니다.");
        }
        return response.json();
      })
      .then(data => {
        alert('상품이 성공적으로 등록되었습니다.');
        window.location.href = "/manager/manageProduct";
      })
      .catch(error => {
        alert('상품 등록 중 오류가 발생했습니다.');
      });
});
