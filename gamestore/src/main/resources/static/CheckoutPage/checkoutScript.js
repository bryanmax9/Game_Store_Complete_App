//Sticky navigation for background changing when scrolling through page
const header = document.querySelector("header");
const headerOriginalPosition = header.offsetTop;

function checkHeaderPosition() {
  if (window.pageYOffset > headerOriginalPosition) {
    header.classList.add("scrolled");
  } else {
    header.classList.remove("scrolled");
  }
}

window.addEventListener("scroll", checkHeaderPosition);

// preview buttons
function showImage() {
  document.getElementById("itemImage").style.display = "block";
  document.getElementById("itemVideo").style.display = "none";
}

function showVideo() {
  document.getElementById("itemImage").style.display = "none";
  document.getElementById("itemVideo").style.display = "block";
}
