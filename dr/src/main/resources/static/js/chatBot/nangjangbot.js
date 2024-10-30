$(document).ready(function () {
    $('#nangjangbot-chating').submit(function (e) {
        e.preventDefault();

        // 유저 메세지 가져오기
        let userMsg = $('#nangjangbot-sendMsg').val();
        if (userMsg.trim() === "") {
            return; // 공백 입력은 무시
        }

        // 인풋창 비활성화
        $('#nangjangbot-sendMsg').prop('disabled', true);

        // 유저 메세지를 화면에 바로 추가
        $('#nangjangbot-conversationContainer').append(
            `<div class="nangjangbot-myMsg">
                 <div class="userMsgBox">${userMsg}</div>
            </div>`
        );

        // 로딩중 표시
        $('#loading-spinner').css('display', 'flex');

        // AJAX 요청을 통해 챗봇 응답 가져오기
        $.ajax({
            type: "POST",
            url: "/api/chatbot/chat",
            contentType: "application/json",
            data: JSON.stringify({message: userMsg}),
            success: function (response) {
                $('#loading-spinner').css('display', 'none'); // 로딩중 숨김

                console.log(response.reply);

                // 챗봇 응답을 화면에 추가
                $('#nangjangbot-conversationContainer').append(`
                        <img src="/image/nangjangbot/apple.png" class="nangjangbot-mascot" alt="챗봇 마스코트">
                        <div class="botMsgBox">${response.reply}</div>
                `);

                // 새로운 메시지가 추가된 후 페이지 스크롤을 최하단으로 이동
                // window.scrollTo({
                //     top: document.body.scrollHeight,
                //     behavior: 'smooth'
                // });
                $('html, body').animate({
                    scrollTop: $(document).height()  // 페이지 전체 높이로 스크롤
                }, 500);

                // 인풋창 다시 활성화하고 포커스 설정
                $('#nangjangbot-sendMsg').prop('disabled', false).focus();

            },
            error: function () {
                // 로딩중 숨김
                $('#loading-spinner').css('display', 'none');
                alert("죄송합니다. 서버 오류가 발생했습니다. 다시 시도해주세요.");

                // 인풋창 다시 활성화하고 포커스 설정
                $('#nangjangbot-sendMsg').prop('disabled', false).focus();
            }
        });

        // 인풋창 비우기
        $('#nangjangbot-sendMsg').val('');

        // 유저가 처음 메세지를 입력했을 때 숨길 요소 설정
        $('.nangjangbot-pageTitle, #nangjangbot-text').css('display', 'none');
        $('.nangjangbot-block, .nangjangbot-myMsg, .nangjangbot-chatBotMsg').css('display', 'block');

    });
});

// //비동기로 데이터 저장
// function sendDB() {
//     // 입력된 메시지 가져오기
//     let message = $('#chatRoom-chatArea').val();
//     if (message.trim() === '') {
//         return; // 빈 메시지는 전송하지 않음
//     }
//
//     // 채팅방 정보 가져오기
//     let sessionNumber = $('#chatRoom-info').text();
//
//     // Ajax를 사용하여 서버로 데이터 전송
//     $.ajax({
//         url: '/sendOkController.doccl', // 전송할 서블릿의 URL
//         type: 'POST',
//         data: {
//             message: message,
//             sessionNumber: sessionNumber
//         },
//         success: function() {
//             // 입력 창 비우기
//             $('#chatRoom-chatArea').val('');
//         },
//         error: function() {
//             alert('메시지 전송에 실패했습니다.');
//         }
//     });
// }


//a태그 post로 전송
function postRequest(url) {
    const form = document.createElement('form');
    form.method = 'post';
    form.action = url;
    document.body.appendChild(form);
    form.submit();
}


const sidebar = document.getElementById('nangjangbot-sideBar');
const button = document.getElementById('nangjangbot-btnContainer');
const closeBtn = document.getElementById('nangjangbot-closeBtn');
let isOpen = true; // 사이드바가 열려있는 상태

button.addEventListener('click', () => {
    if (isOpen) {
        sidebar.style.left = '-350px'; // 사이드바 닫기
    } else {
        sidebar.style.left = '0'; // 사이드바 열기
    }
    isOpen = !isOpen; // 상태 토글
});

closeBtn.addEventListener('click', () => {
    sidebar.style.left = '-400px'; // 사이드바 닫기
    isOpen = false; // 상태를 닫힘으로 설정
});

document.addEventListener("DOMContentLoaded", function () {
    // 모든 .nangjangbot-imgFrame 요소를 선택
    const deleteButtons = document.querySelectorAll('.nangjangbot-imgFrame');

    deleteButtons.forEach(button => {
        button.addEventListener('click', function () {
            if (confirm("정말로 삭제하시겠습니까?\n삭제된 채팅은 복구가 불가능합니다.")) {
                alert("삭제되었습니다.");
                // 삭제 관련 로직 필요
            }
        });
    });
});
