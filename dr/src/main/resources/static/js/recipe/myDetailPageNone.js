const heartImage = document.getElementById('heartImage');
  
heartImage.addEventListener('click', function() {
  if (heartImage.src.includes('heartGray.png')) {
    heartImage.src = './../../image/heartColor.png'; // 빨간 하트 이미지 경로
  } else {
    heartImage.src = './../../image/heartGray.png'; // 검정 하트 이미지 경로
  }
});