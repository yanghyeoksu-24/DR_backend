document.getElementById("myPage-CButton").addEventListener("click", function() {
    const checkbox = document.getElementById("myPage-input"); // 안내 문구
    if (!checkbox.checked) { //안내 문구를 체크하지 않았을 경우 안내 문구 체크 if문
        alert("안내 문구를 체크해주시기 바랍니다.");
    } else {
        // 회원 탈퇴가 완료되었을 때 알림창 표시 후 페이지 이동
        if (confirm("정말 진심으로 찐막으로 탈퇴를 진행하시겠습니까?")) {
            alert("회원 탈퇴가 완료되었습니다.");
            window.location.href = "./../../html/myPage/myPageDelete.html";
        }
    }
});