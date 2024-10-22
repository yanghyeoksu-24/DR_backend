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
                // 여기서 삭제 관련 로직 추가 가능
            }
        });
    });
});

$(document).ready(function () {
    // Send button or enter key triggers sending a message
    $('#nangjangbot-chating').submit(function (e) {
        e.preventDefault(); // 기본 제출 방지

        // 유저 메세지 가져오기
        let userMsg = $('#nangjangbot-sendMsg').val();
        if (userMsg.trim() === "") {
            return; // 공백 입력은 무시
        }

        // 유저 메세지를 화면에 추가
        $('.nangjangbot-myMsg').append(`<div class="userMsgBox">${userMsg}</div>`);

        // 유저 메세지를 입력한 후 인풋창 비우기
        $('#nangjangbot-sendMsg').val('');

        // 유저가 처음 메세지를 입력했을 때만 이 부분 숨기기
        $('.nangjangbot-pageTitle, #nangjangbot-text').css('display', 'none');
        $('.nangjangbot-block, .nangjangbot-myMsg, .nangjangbot-chatBotMsg').css('display', 'block');

        // 챗봇 메세지 출력 부분 (여기서는 매번 같은 답변을 제공)
        let botResponse = `남은 돼지 목살과 사과로 일식 느낌의 요리를 만들 수 있는 좋은 방법은 간단한 돼지 목살 사과 덮밥입니다. 
      일본식 요리의 달콤짭짤한 맛을 살릴 수 있어요. 아래에 간단한 레시피를 제안할게요.<br><br>
      재료:<br>
      - 돼지 목살 100g<br>
      - 사과 반쪽 (슬라이스)<br>
      - 간장 2큰술<br>
      - 미림 1큰술 (또는 설탕 1작은술로 대체 가능)<br>
      - 다진 마늘 1작은술<br>
      - 다진 생강 1작은술<br>
      - 참기름 약간<br>
      - 식초 1작은술 (사과의 상큼함을 살리기 위해 추가)<br>
      - 밥 1공기<br>
      - 약간의 파나 깨 (선택)<br><br>
      요리 순서:<br>
      1. 재료 손질: 돼지 목살은 먹기 좋은 크기로 자르고, 사과는 얇게 슬라이스해 둡니다.<br>
      2. 양념 만들기: 작은 볼에 간장, 미림(또는 설탕), 다진 마늘, 다진 생강, 참기름, 식초를 섞어 양념장을 준비합니다.<br>
      3. 고기 굽기: 중불에서 팬에 기름을 두르고, 돼지 목살을 노릇하게 구워줍니다.<br>
      4. 사과 추가: 고기가 거의 익으면 슬라이스한 사과를 넣고 함께 볶습니다. 사과가 약간 부드러워질 때까지 익히세요.<br>
      5. 양념장 추가: 준비한 양념장을 넣고 고기와 사과에 골고루 배도록 볶아줍니다.<br>
      6. 마무리: 밥 위에 돼지 목살과 사과를 올리고, 파나 깨로 장식하면 완성!<br><br>
      이 요리는 간단하면서도 달콤하고 짭짤한 맛이 어우러져 일식 느낌을 낼 수 있을 거예요.`;

        // 챗봇 메세지를 화면에 추가
        $('.nangjangbot-chatBotMsg').append(`<div class="botMsgBox">${botResponse}</div>`);

        // 새로운 메시지가 추가된 후 페이지 스크롤을 최하단으로 이동
        window.scrollTo({
            top: document.body.scrollHeight,
            behavior: 'smooth'
        });
    });
});
