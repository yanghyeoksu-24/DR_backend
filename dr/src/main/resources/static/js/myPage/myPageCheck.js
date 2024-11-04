document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var todayDateString = document.getElementById('date').value;

    // 로컬 스토리지에서 출석 체크 상태를 가져옵니다.
    var isCheckedIn = localStorage.getItem('isCheckedIn') === 'true';

    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        locale: 'ko',
        events: [],
        eventContent: function(arg) {
            return {
                html: `<div style="display: flex; justify-content: center; align-items: center; height: 100%; background-color: #fffadf; border: none !important; outline: none !important;">
                          <img src="./../../image/myPage/checkBox.png" style="width: 30px; height: 25px;" />
                      </div>`
            };
        }
    });
    calendar.render();

    // 출석 체크가 완료되었는지 서버에서 확인
    fetch(`/myPage/myPageCheckedPlease`, {
        method: 'GET',
        body: JSON.stringify({ userNumber: this.userNumber, date: todayDateString }),
        headers: {
            'Content-Type': 'application/json',
        }
    })
        .then(response => response.json())
        .then(data => {
            // 서버에서 출석 체크 여부를 받아옵니다.
            if (data.checkedIn) {
                alert("이미 출석체크가 완료되었습니다.");
                isCheckedIn = true; // 로컬 상태 업데이트
                localStorage.setItem('isCheckedIn', 'true'); // 로컬 스토리지에 상태 저장
            }
        })
        .catch(error => {
            console.error('출석 체크 상태 확인 중 오류 발생: ', error);
        });

    document.getElementById('checkInForm').addEventListener('submit', function(event) {
        event.preventDefault();

        if (isCheckedIn) {
            alert("이미 출석체크가 완료되었습니다.");
            return; // 이미 출석 체크가 완료된 경우, 더 이상 진행하지 않음
        }

        alert("출석체크가 완료되었습니다. 10포인트가 적립됩니다.");

        fetch(`/myPage/myPageCheckedPlease?date=${encodeURIComponent(todayDateString)}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'text/html',
            }
        })
            .then(response => response.text())
            .then(html => {
                const parser = new DOMParser();
                const doc = parser.parseFromString(html, 'text/html');
                const message = doc.querySelector('div.message')?.textContent;

                // 서버로부터 메시지를 받아와서 사용자에게 알림
                if (message) {
                    alert(message);
                }

                // 출석 체크가 성공적으로 이루어지면
                if (message === "출석 체크가 완료되었습니다. 10 포인트가 적립되었습니다.") {
                    setTimeout(() => {
                        // 오늘 날짜의 요소 선택
                        var todayElements = document.querySelectorAll(`.fc-daygrid-day[data-date="${todayDateString}"]`);

                        // 체크 이미지 추가
                        todayElements.forEach(function(element) {
                            if (!element.querySelector('.check-image')) {
                                var checkImageDiv = document.createElement('div');
                                checkImageDiv.className = 'check-image';
                                checkImageDiv.style.position = 'absolute';
                                checkImageDiv.style.top = '50%';
                                checkImageDiv.style.left = '50%';
                                checkImageDiv.style.transform = 'translate(-50%, -50%)';
                                checkImageDiv.style.width = '40px';
                                checkImageDiv.style.height = '40px';
                                checkImageDiv.style.backgroundImage = "url('/image/myPage/checkBoxRed.png')";
                                checkImageDiv.style.backgroundSize = 'contain';
                                checkImageDiv.style.backgroundRepeat = 'no-repeat';
                                element.appendChild(checkImageDiv);
                            }
                        });

                        // 출석 체크 상태 업데이트
                        isCheckedIn = true;
                        localStorage.setItem('isCheckedIn', 'true'); // 로컬 스토리지에 상태 저장
                    }, 100);
                }
            })
            .catch(error => {
                console.error('출석 체크 중 오류 발생: ', error);
                alert("출석 체크 중 오류가 발생했습니다. 다시 시도해 주세요.");
            });
    });
});
