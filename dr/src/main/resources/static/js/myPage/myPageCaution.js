document.getElementById("myPageDeleteForm").addEventListener("submit", function(event) {
    const checkbox = document.getElementById("myPage-input");
    if (!checkbox.checked) {
        alert("안내 사항에 동의해야 회원 탈퇴가 가능합니다.");
        event.preventDefault(); // 폼 제출 막기
    }
});