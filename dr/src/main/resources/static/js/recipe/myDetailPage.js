const heartImage = document.getElementById('heartImage');

heartImage.addEventListener('click', function () {
    if (heartImage.src.includes('heartGray.png')) {
        heartImage.src = './../../image/heartColor.png'; // 빨간 하트 이미지 경로
    } else {
        heartImage.src = './../../image/heartGray.png'; // 검정 하트 이미지 경로
    }
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

// 댓글 수정 기능
function editComment(commentId) {
    var commentText = document.getElementById('commentText' + commentId);
    var editInput = document.getElementById('editInput' + commentId);
    var editTextarea = document.getElementById('editTextarea' + commentId);
    var buttonGroup = document.getElementById('buttonGroup' + commentId);

    editTextarea.value = commentText.innerText;

    commentText.style.display = 'none';
    editInput.style.display = 'block';
    buttonGroup.style.display = 'none';
}

function saveComment(commentId) {
    var commentText = document.getElementById('commentText' + commentId);
    var editInput = document.getElementById('editInput' + commentId);
    var editTextarea = document.getElementById('editTextarea' + commentId);
    var buttonGroup = document.getElementById('buttonGroup' + commentId);

    if (confirm("수정하시겠습니까?")) {
        commentText.innerText = editTextarea.value;

        editInput.style.display = 'none';
        commentText.style.display = 'block';
        buttonGroup.style.display = 'flex';

        alert("수정완료되었습니다.");
    } else {
        cancelEdit(commentId);
    }
}

function cancelEdit(commentId) {
    var commentText = document.getElementById('commentText' + commentId);
    var editInput = document.getElementById('editInput' + commentId);
    var buttonGroup = document.getElementById('buttonGroup' + commentId);

    editInput.style.display = 'none';
    commentText.style.display = 'block';
    buttonGroup.style.display = 'flex';
}

// 댓글 삭제 기능
document.addEventListener("DOMContentLoaded", function () {
    // 모든 삭제 버튼 선택
    const deleteButtons = document.querySelectorAll(".myDetailPage-deleteBtn");

    deleteButtons.forEach(button => {
        // 각 삭제 버튼에 클릭 이벤트 리스너 추가
        button.addEventListener("click", function () {
            if (confirm("댓글을 삭제하시겠습니까?")) {
                // replyNumber 값을 숨겨진 input에서 가져오기
                const replyNumber = button.closest('.myDetailPage-comment')
                    .querySelector('input[name="replyNumber"]').value;

                // 서버에 삭제 요청 보내기
                fetch('/deleteComment', {
                    method: 'DELETE',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    body: 'replyNumber=' + replyNumber
                })
                    .then(response => {
                        if (response.ok) {
                            alert("댓글이 삭제되었습니다.");
                            window.location.href = "/recipe/myDetailPage"; // 삭제 후 페이지 리다이렉트
                        } else {
                            alert("댓글 삭제에 실패했습니다.");
                        }
                    })
                    .catch(error => {
                        console.error("Error:", error);
                    });
            }
        });
    });
});

// 초기 추천 수를 0으로 설정
let recommendCount = 0;

// recommend 이미지를 클릭할 때 색상 토글 및 추천 수 증가/감소 기능을 적용
document.getElementById('recommendImg').addEventListener('click', function() {
    var recommendImg = document.getElementById('recommendImg'); // 추천 이미지 요소
    var recommendCountDisplay = document.getElementById('recommendCount'); // 추천 수 표시 요소
    const recipeNumber = document.querySelector("input[name='recipeNumber']").value; // 레시피 번호
    const userNumber = document.querySelector("input[name='userNumber']").value; // 사용자 번호

    // 추천 이미지가 파란색 활성화 상태인 경우
    if (recommendImg.classList.contains('recommend-active')) {
        // 파란색에서 회색으로 전환 (추천 해제)
        recommendImg.classList.remove('recommend-active');
        recommendImg.classList.add('recommend-inactive');
        if (recommendCount > 0) {
            recommendCount--; // 추천 수 감소
            // AJAX 요청으로 추천 수 감소
            $.post(`/recipes/myDetailPage/${recipeNumber}/good/remove`, { userNumber: userNumber })
                .done(function() {
                    // 서버에서 성공적으로 처리된 경우
                    recommendCountDisplay.innerText = `추천수: ${recommendCount}`;
                });
        }
    }
    // 회색 상태인 경우 (또는 아무 색상도 없을 경우)
    else if (recommendImg.classList.contains('recommend-inactive')) {
        // 회색에서 파란색으로 전환 (추천 추가)
        recommendImg.classList.remove('recommend-inactive');
        recommendImg.classList.add('recommend-active');
        recommendCount++; // 추천 수 증가
        // AJAX 요청으로 추천 수 증가
        $.post(`/recipes/myDetailPage/${recipeNumber}/good/add`, { userNumber: userNumber })
            .done(function() {
                // 서버에서 성공적으로 처리된 경우
                recommendCountDisplay.innerText = `추천수: ${recommendCount}`;
            });
    }
    // 처음 상태일 때 파란색으로 전환 (기본 초록색에서 파란색으로)
    else {
        recommendImg.classList.add('recommend-active');
        recommendCount++; // 추천 수 증가
        // AJAX 요청으로 추천 수 증가
        $.post(`/recipes/myDetailPage/${recipeNumber}/good/add`, { userNumber: userNumber })
            .done(function() {
                // 서버에서 성공적으로 처리된 경우
                recommendCountDisplay.innerText = `추천수: ${recommendCount}`;
            });
    }
});

