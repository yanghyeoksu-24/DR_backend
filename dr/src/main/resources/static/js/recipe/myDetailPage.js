const heartImage = document.getElementById('heartImage');

heartImage.addEventListener('click', function() {
    if (heartImage.src.includes('heartGray.png')) {
        heartImage.src = './../../image/heartColor.png'; // 빨간 하트 이미지 경로
    } else {
        heartImage.src = './../../image/heartGray.png'; // 검정 하트 이미지 경로
    }
});

const heartImage1 = document.getElementById('heartImage1'); // 빨간색 하트
const heartImage2 = document.getElementById('heartImage2'); // 회색 하트

// 회색 하트 이미지를 클릭했을 때 빨간색으로 전환
heartImage2.addEventListener('click', function(event) {
    event.preventDefault(); // 기본 a 태그 동작 막기
    heartImage1.style.display = 'block'; // 빨간색 하트를 보이게 함
    heartImage2.style.display = 'none';  // 회색 하트를 숨김
});

// 빨간 하트 이미지를 클릭했을 때 회색으로 전환
heartImage1.addEventListener('click', function(event) {
    event.preventDefault(); // 기본 a 태그 동작 막기
    heartImage1.style.display = 'none';  // 빨간색 하트를 숨김
    heartImage2.style.display = 'block'; // 회색 하트를 보이게 함
});

document.getElementById('deleteButton').addEventListener('click', function () {
    if (confirm("정말로 삭제하시겠습니까?")) {
        alert("삭제완료되었습니다.");
    } else {
        return;
    }
});

// 댓글 등록 버튼 클릭 시
function submitComment() {
    const confirmation = confirm("댓글을 등록하시겠습니까?");
    if (confirmation) {
        alert("댓글이 등록되었습니다.");
    } else {
        return;
    }
}





// 추천 기능
$(document).ready(function() {
    const recommendImg = $('#recommendImg');
    let isLiked = false;

    // 숨겨진 input에서 recipeNumber 및 userNumber 가져오기
    const recipeNumber = $('input[name="recipeNumber"]').val();
    const userNumber = $('input[name="userNumber"]').val();

    recommendImg.on('click', function () {
        // 상태 토글
        isLiked = !isLiked;

        // 추천 상태에 따라 클래스 변경
        if (isLiked) {
            recommendImg.addClass('recommend-active').removeClass('recommend-inactive');
        } else {
            recommendImg.addClass('recommend-inactive').removeClass('recommend-active');
        }

        // URL 설정
        const url = isLiked ? `/recipe/goodPlus` : `/recipe/goodMinus`;

        // 서버에 보낼 데이터 구성
        const requestData = JSON.stringify({
            recipeNumber: recipeNumber,
            userNumber: userNumber
        });

        // Ajax 요청
        $.ajax({
            url: url,
            type: 'POST',
            contentType: 'application/json',
            data: requestData,
            success: function (response) {
                console.log(isLiked ? '추천 등록 완료' : '추천 취소 완료');
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.error('추천 상태 변경 오류:', textStatus, errorThrown);
                alert('추천 상태 변경 중 오류가 발생했습니다.');
            }
        });
    });
});
