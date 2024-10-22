// 소수점 반올림 함수
function round(value, decimals) {
  return Number(Math.round(value + 'e' + decimals) + 'e-' + decimals);
}

// 계산 함수
function convert() {
  var qnt_kg = document.getElementById("main-qnt").value;

  document.getElementById("main-portion").innerHTML = round(qnt_kg / 0.719, 1) + " 인분";
  document.getElementById("main-co2").innerHTML = round(qnt_kg * 1.7, 1) + " kgCO2eq.";
  document.getElementById("main-tree").innerHTML = round(qnt_kg / 4.87, 1) + " 그루";
  document.getElementById("main-energy").innerHTML = round(qnt_kg * 1.67, 1) + " kWh";
  document.getElementById("main-washingMachine").innerHTML = round(qnt_kg * 12, 1) + " 회";
  document.getElementById("main-transCost").innerHTML = round(qnt_kg * 182, 1) + " 원";
  document.getElementById("main-processCost").innerHTML = round(qnt_kg * 260000, 1) + " 원";

  
}

// 이벤트 리스너 추가
document.addEventListener("DOMContentLoaded", function () {
  // 무게 입력 필드에 입력할 때마다 convert 함수가 실행되도록 이벤트 리스너를 추가
  var inputField = document.getElementById("main-qnt");
  inputField.addEventListener("input", convert);
});