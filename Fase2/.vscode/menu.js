const hamBurger = document.querySelector(".toggle-btn");
var iframe = document.querySelector("#iframeContent");

hamBurger.addEventListener("click", function () {
  document.querySelector("#sidebar").classList.toggle("expand");
  iframe.classList.toggle('retrair')
});