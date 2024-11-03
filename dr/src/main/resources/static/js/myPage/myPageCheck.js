document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var events = []; // 출석 체크된 날짜 배열

    // 사용자 번호 가져오기
    var userNumber = document.getElementById('userInfo').getAttribute('data-user-number'); // data-user-number에서 가져오기

    // 캘린더 생성
    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        locale: 'ko',
        events: events,
        eventContent: function(arg) {
            return {
                html: `<div style="display: flex; justify-content: center; align-items: center; height: 100%;">
                          <img src="./../../image/myPage/checkBox.png" style="width: 30px; height: 25px;" />
                      </div>`
            };
        }
    });
    calendar.render();

    // 오늘 날짜 문자열 생성 함수
    function getTodayDateString() {
        var today = new Date();
        return today.toISOString().split('T')[0]; // 'YYYY-MM-DD' 포맷으로 반환
    }

    // 출석 체크 AJAX 요청 함수
    function checkInAttendance() {
        var todayDateString = getTodayDateString();
        return fetch(`/myPage/myPageCheck?userNumber=${userNumber}&date=${todayDateString}`, {
            method: 'GET' // GET 방식
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('출석 체크 요청 실패');
                }
                return response.json(); // JSON 형태로 응답을 받음
            })
            .then(data => {
                if (data === "출석 체크가 완료되었습니다.") { // 서버에서 받은 메시지를 체크
                    alert("출석 체크가 완료되었습니다. 10 포인트가 적립되었습니다.");
                    calendar.addEvent({
                        title: '', // 제목 없음
                        start: todayDateString,
                        display: 'auto'
                    });
                    events.push({ start: todayDateString }); // 출석 체크된 날짜 추가
                } else {
                    alert(data || "출석 체크 중 문제가 발생했습니다.");
                }
            })
            .catch(error => {
                console.error('Error checking in attendance:', error);
                alert("출석 체크 중 오류가 발생했습니다.");
            });
    }

    // 출석 체크 버튼 클릭 이벤트
    document.getElementById('checkInButton').addEventListener('click', function() {
        var todayDateString = getTodayDateString();

        // 이미 출석 체크된 날짜인지 확인
        if (events.some(event => event.start === todayDateString)) {
            alert("이미 출석 체크가 완료되었습니다.");
            return;
        }

        // 출석 체크 AJAX 요청
        checkInAttendance();
    });
});
