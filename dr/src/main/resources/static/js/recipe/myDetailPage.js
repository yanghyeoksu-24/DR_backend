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

// 댓글 삭제 버튼을 눌렀을 때 실행되는 함수
document.querySelectorAll('.myDetailPage-deleteBtn').forEach(function (button) {
    button.addEventListener('click', function () {
        var replyNumber = button.getAttribute('data-reply-number');
        deleteComment(replyNumber);
    });
});

// 댓글 삭제 함수
function deleteComment(replyNumber) {
    if (confirm("정말로 이 댓글을 삭제하시겠습니까?")) {
        // AJAX 요청을 통해 서버에 댓글 삭제 요청
        $.ajax({
            type: 'POST',
            url: '/recipe/deleteReply', // 서버의 댓글 삭제 요청 URL
            data: { replyNumber: replyNumber },
            success: function () {
                // 댓글 요소를 제거
                var commentElement = document.getElementById('comment' + replyNumber);
                commentElement.remove();
                alert("댓글이 삭제되었습니다.");
            },
            error: function () {
                alert("댓글 삭제에 실패했습니다.");
            }
        });
    }
}




// 추천 기능
document.addEventListener("DOMContentLoaded", function () {
    const recommendImg = document.getElementById("recommendImg");
    const recipeNumber = document.getElementById("recipeNumber").value;

    let isActive = localStorage.getItem(`recommend-active-${recipeNumber}`) === 'true';
    recommendImg.classList.toggle("recommend-active", isActive);
    recommendImg.classList.toggle("recommend-inactive", !isActive);

    let isRequestInProgress = false;

    const handleClick = function () {
        if (isRequestInProgress) return;

        isRequestInProgress = true;
        const url = isActive ? "/recipe/goodMinus" : "/recipe/goodPlus";

        fetch(url, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ boardNumber: boardNumber })
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error("요청 실패: " + response.statusText);
                }

                // 추천 상태 업데이트 및 localStorage 저장
                isActive = !isActive;
                recommendImg.classList.toggle("recommend-active", isActive);
                recommendImg.classList.toggle("recommend-inactive", !isActive);
                localStorage.setItem(`recommend-active-${recipeNumber}`, isActive);

                // 요청 성공 시 페이지 새로 고침
                location.reload();
            })
            .catch(error => {
                console.error("Error:", error);
                alert("요청 처리 중 오류가 발생했습니다.");
            })
            .finally(() => {
                // 요청 완료 후 상태 초기화
                isRequestInProgress = false;
            });
    };

    recommendImg.addEventListener("click", handleClick);
});

