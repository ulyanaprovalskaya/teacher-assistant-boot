let countDownDate = new Date("Apr 6, 2022 19:17:25").getTime();
let x = setInterval(function () {
  countdown(countDownDate)
}, 1000);
let y = setInterval(function () {
  let today = new Date();
  document.getElementById("clock").innerHTML = " / " + appendTime(today.getHours(), today.getMinutes(), today.getSeconds())
}, 1000)

function countdown(countDownDate) {
  let now = new Date().getTime();
  let distance = countDownDate - now;

  let days = Math.floor(distance / (1000 * 60 * 60 * 24));
  let hours = days * 24 + Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
  let minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
  let seconds = Math.floor((distance % (1000 * 60)) / 1000);

  document.getElementById("timer").innerHTML = appendTime(hours, minutes, seconds);
  if (distance < 0) {
    clearInterval(x);
    document.getElementById("timer").innerHTML = "00:00:00";
  }
}

function appendTime(hours, minutes, seconds) {
  return convertTime(hours) + ":" + convertTime(minutes) + ":" + convertTime(seconds);
}

function convertTime(time) {
  if (time < 10) {
    return '0' + time.toString();
  }
  return time;
}