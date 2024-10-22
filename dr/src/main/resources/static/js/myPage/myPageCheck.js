document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var isCheckedIn = false; // 출석 체크 상태 저장 변수

    // 캘린더 생성
    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth', // 캘린더 초기 표시 형태 : 월 단위
        locale: 'ko', // 한국어
        events: [], // 출석 체크된 날짜 배열 사용 (초기값 비워두기)
        eventContent: function(arg) {
            // 이벤트에 표시할 내용 설정 (이미지만 표시)
            return {
                html: `<div style="display: flex; justify-content: center; align-items: center; height: 100%; background-color: #fffadf; border: none !important; outline: none !important;">
                      <img src="./../../image/myPage/checkBox.png" style="width: 30px; height: 25px;" />
                      </div>`
            };
        }
    });
    calendar.render();

    // 오늘 날짜 문자열 생성 함수
    function getTodayDateString() {
        var today = new Date();
        return today.getFullYear() + '-' +
            String(today.getMonth() + 1).padStart(2, '0') + '-' +
            String(today.getDate()).padStart(2, '0');
    }

    // 출석 체크 버튼 클릭 이벤트
    document.getElementById('checkInButton').addEventListener('click', function() {
        if (isCheckedIn) {
            alert("이미 출석 체크가 완료되었습니다.");
        } else {
            var todayDateString = getTodayDateString(); // 오늘 날짜 문자열 가져오기

            // 해당 날짜 셀을 찾아 체크 이미지 추가
            var todayElements = document.querySelectorAll(`.fc-daygrid-day[data-date="${todayDateString}"]`);

            if (todayElements.length === 0) {
                alert("오늘 날짜 셀을 찾을 수 없습니다.");
                return;
            }

            todayElements.forEach(function(element) {
                // 캘린더에 출석 체크 이벤트 추가 (제목 없음)
                calendar.addEvent({
                    start: todayDateString, // 이벤트 날짜
                    display: 'auto' // 자동으로 표시
                });

                // 체크 이미지가 추가되어 있지 않은 경우에만 추가
                if (!element.querySelector('.check-image')) {
                    var checkImageDiv = document.createElement('div');
                    checkImageDiv.className = 'check-image'; // 이미지에 스타일을 적용하기 위한 클래스명
                    checkImageDiv.style.position = 'absolute';
                    checkImageDiv.style.top = '50%';
                    checkImageDiv.style.left = '50%';
                    checkImageDiv.style.transform = 'translate(-50%, -50%)';
                    checkImageDiv.style.width = '40px';
                    checkImageDiv.style.height = '40px';
                    checkImageDiv.style.backgroundImage = "url('./../../image/myPage/checkBoxRed.png')"; // 빨간 체크 이미지 경로
                    checkImageDiv.style.backgroundSize = 'contain';
                    checkImageDiv.style.backgroundRepeat = 'no-repeat';

                    // 셀에 체크 이미지 추가
                    element.appendChild(checkImageDiv);
                }
            });

            isCheckedIn = true; // 출석 체크 상태 업데이트
            alert("10 포인트가 적립되었습니다.");
        }
    });
});