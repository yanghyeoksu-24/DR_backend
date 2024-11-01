document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var events = []; // 출석 체크된 날짜 배열
    var userNumber = 1; // 사용자 번호 설정 (예시)

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
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            }
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('출석 체크 요청 실패');
                }
                return response.text();
            })
            .then(data => {
                window.location.href = '/myPage/myPageCheck';
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

        // 이벤트 추가 및 표시
        calendar.addEvent({
            title: '', // 제목 없음
            start: todayDateString,
            display: 'auto'
        });

        events.push({ start: todayDateString }); // 출석 체크된 날짜 추가

        alert("출석 체크가 완료되었습니다. 10 포인트가 적립되었습니다.");
        checkInAttendance(); // AJAX 요청
    });
});
