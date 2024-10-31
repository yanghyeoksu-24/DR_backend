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

        //sessionNumber 가져오기
        let sessionNumber = $('#sessionNumber').val();

        // AJAX 요청을 통해 챗봇 응답 가져오기
        $.ajax({
            type: "POST",
            url: "/api/chatbot/chat",
            contentType: "application/json",
            data: JSON.stringify({
                message: userMsg,
                sessionNumber: sessionNumber
            }),
            success: function (response) {
                $('#loading-spinner').css('display', 'none'); // 로딩중 숨김

                //sessionNumber가 0이라면 번호 변경
                if (response.sessionNumber != 0) {
                    // AJAX 응답에서 sessionNumber 업데이트
                    $('#sessionNumber').val(response.sessionNumber);
                }

                console.log(response.reply);

                // 챗봇 응답을 화면에 추가
                $('#nangjangbot-conversationContainer').append(`
                        <img src="/image/nangjangbot/apple.png" class="nangjangbot-mascot" alt="챗봇 마스코트">
                        <div class="botMsgBox">${response.reply}</div>
                `);

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


        // 삭제 버튼 클릭 이벤트 처리
        $(document).on('click', '.nangjangbot-imgFrame', function () {
            const sessionNumber = $(this).siblings('.lastChat').val(); // sessionNumber 가져오기

            if (confirm("정말로 삭제하시겠습니까?\n삭제된 채팅은 복구가 불가능합니다.")) {
                $.ajax({
                    type: "GET",
                    url: "/api/chatbot/delete",
                    contentType: "application/json",
                    data: JSON.stringify(sessionNumber), // sessionNumber를 JSON 형태로 전송
                    success: function (newChatList) {
                        alert("삭제되었습니다.");

                        // 기존 채팅 목록을 초기화
                        $('#nangjangbot-sideBar').find('.nangjangbot-lastChat').remove();

                        // 새로운 채팅 목록으로 업데이트
                        newChatList.forEach(chat => {
                            $('#nangjangbot-sideBar').append(`
                            <div class="nangjangbot-lastChat">
                                <span class="nangjangbot-lastChatTitle">${chat.sessionTitle}</span>
                                <span class="nangjangbot-lastChatDate">${chat.createDate}</span>
                                <button class="nangjangbot-imgFrame">
                                    <img th:src="@{/image/nangjangbot/delete.png}">
                                </button>
                                <input type="hidden" class="lastChat" value="${chat.sessionNumber}" />
                            </div>
                        `);
                        });
                    },
                    error: function () {
                        alert("삭제 중 오류가 발생했습니다.");
                    }
                });
            }
        });

    });
});


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
