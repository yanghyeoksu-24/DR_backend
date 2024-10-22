// + 버튼에 대한 클릭 이벤트 설정
document.getElementById('numberUp').addEventListener('click', function() {
  // + 버튼이 있는 네 번째 div 선택 (네 번째 .register-right-con)
  const targetDiv = document.querySelectorAll('.register-right-con')[3];  // 4번째 div 선택
  
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

// 파일 선택 input에 대한 change 이벤트 설정
document.querySelector('.real-upload').addEventListener('change', function(event) {
  // 선택된 파일들을 가져옴
  const files = event.target.files;
  const uploadDiv = document.querySelector('.upload');

  // 이전에 있던 이미지들 제거 (새로운 파일 선택 시 갱신)
  uploadDiv.innerHTML = '';

  // 각 파일에 대해 이미지 미리보기 생성
  Array.from(files).forEach(file => {
    if (file.type.startsWith('image/')) { // 이미지 파일만 처리
      const reader = new FileReader();

      reader.onload = function(e) {
        // 이미지 엘리먼트 생성
        const img = document.createElement('img');
        img.src = e.target.result;
        img.style.width = '120px';  // 원하는 크기로 설정
        img.style.margin = '10px';  // 이미지 간격 설정
        img.alt = 'Uploaded Image';

        // upload div에 이미지 추가
        uploadDiv.appendChild(img);
      };

      // 파일을 읽어서 이미지로 변환
      reader.readAsDataURL(file);
    }
  });
});
