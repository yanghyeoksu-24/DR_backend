const heartImage = document.getElementById('heartImage');

heartImage.addEventListener('click', function() {
    if (heartImage.src.includes('heartGray.png')) {
        heartImage.src = './../../image/heartColor.png'; // 빨간 하트 이미지 경로
    } else {
        heartImage.src = './../../image/heartGray.png'; // 검정 하트 이미지 경로
    }
    if(num){

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

// const heartImage = document.getElementById('heartImage');
//
// heartImage.addEventListener('click', function () {
//     if (heartImage.src.includes('heartGray.png')) {
//         heartImage.src = './../../image/heartColor.png'; // 빨간 하트 이미지 경로
//     } else {
//         heartImage.src = './../../image/heartGray.png'; // 검정 하트 이미지 경로
//     }
// });

// const heartImage = document.getElementById('heartImage');
// const recipeNumber = /* 레시피 번호 */;
// const userNumber = /* 사용자 번호 */;
//
// heartImage.addEventListener('click', function () {
//     if (heartImage.src.includes('heartGray.png')) {
//         // 찜 추가 요청
//         fetch('/recipe/like', {
//             method: 'POST',
//             headers: {
//                 'Content-Type': 'application/json'
//             },
//             body: JSON.stringify({ recipeNumber: recipeNumber, userNumber: userNumber })
//         })
//             .then(response => response.ok ? heartImage.src = './../../image/heartColor.png' : alert("찜 추가에 실패했습니다."))
//             .catch(error => console.error("찜 추가 오류:", error));
//     } else {
//         // 찜 삭제 요청
//         fetch('/recipe/unlike', {
//             method: 'DELETE',
//             headers: {
//                 'Content-Type': 'application/json'
//             },
//             body: JSON.stringify({ recipeNumber: recipeNumber, userNumber: userNumber })
//         })
//             .then(response => response.ok ? heartImage.src = './../../image/heartGray.png' : alert("찜 삭제에 실패했습니다."))
//             .catch(error => console.error("찜 삭제 오류:", error));
//     }
// });


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
            // replyNumber와 recipeNumber를 가져오기
            const replyNumber = button.getAttribute('data-reply-number');
            const recipeNumber = button.closest('.myDetailPage-comment')
                .getAttribute('data-recipe-number'); // 예: data-recipe-number 속성에서 가져옴

            // CommentDeleteClick 함수 호출
            CommentDeleteClick(replyNumber, recipeNumber);
        });
    });
});

// 댓글 삭제 함수
function CommentDeleteClick(replyNumber, recipeNumber) {
    console.log(recipeNumber);
    if (confirm('댓글을 삭제하시겠습니까?')) {
        // 서버에 삭제 요청 보내기
        fetch('/deleteComment', {
            method: 'post',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: 'replyNumber=' + replyNumber + '&recipeNumber=' + recipeNumber
        })
            .then(response => {
                if (response.ok) {
                    alert("댓글이 삭제되었습니다.");
                    console.log("댓글삭제성공");
                    window.location.href = "/recipe/myDetailPage"; // 삭제 후 페이지 리다이렉트
                } else {
                    alert("댓글 삭제에 실패했습니다.");
                    console.log("댓글삭제 실패");
                }
            })
            .catch(error => {
                console.error("Error:", error);
            });
    }
}

//댓글삭제
// function CommentDeleteClick(replyNumber, callback){
//     fetch(`/recipe/myDetailPage/${recipeNumber}/${replyNumber}`,{
//         method:"DELETE"
//     }).then(response =>{
//         if(response.status === 200){
//             callback();
//         }
//     })
//
// }


$(document).ready(function() {
    const recommendImg = $('#recommendImg');
    let isLiked = false;

    // 숨겨진 input에서 recipeNumber 가져오기
    const recipeNumber = $('input[name="recipeNumber"]').val(); // 숨겨진 input의 값 가져오기
    console.log(`recipeNumber: ${recipeNumber}`);

    recommendImg.on('click', function () {
        // 상태 토글
        isLiked = !isLiked;

        // 추천 상태에 따라 클래스 변경
        if (isLiked) {
            recommendImg.addClass('recommend-active').removeClass('recommend-inactive');
        } else {
            recommendImg.addClass('recommend-inactive').removeClass('recommend-active');
        }

        const url = isLiked ? `/recipe/goodPlus` : `/recipe/goodMinus`;
        const requestData = JSON.stringify({recipeNumber: recipeNumber});

        $.ajax({
            url: url,
            type: 'POST',
            contentType: 'application/json', // JSON 형식으로 데이터 전송
            data: requestData,
            success: function (response) {
                console.log(isLiked ? '추천 등록 완료' : '추천 취소 완료');
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.error('추천 상태 변경 오류:', textStatus, errorThrown);
            }
        });
    });
});
