// Sliding menu
const menuBtn = document.querySelector(".menu-btn");
const closeBtn = document.querySelector(".close-btn");
const slidingMenu = document.querySelector(".sliding-menu");

menuBtn.addEventListener("click", function () {
  slidingMenu.classList.toggle("active");
});

closeBtn.addEventListener("click", function () {
  slidingMenu.classList.toggle("active");
});

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

//Carousel functionality
document.addEventListener("DOMContentLoaded", function () {
  // Initialize Swiper
  const professionalCarousel = new Swiper(".swiper-container", {
    effect: "fade", // Modern fade effect between slides
    slidesPerView: 1,
    spaceBetween: 10,
    navigation: {
      nextEl: ".swiper-button-next",
      prevEl: ".swiper-button-prev",
    },
    pagination: {
      el: ".swiper-pagination",
      clickable: true,
    },
    autoplay: {
      delay: 7000, // Auto transitions every 3 seconds
      disableOnInteraction: false, // Autoplay will not stop after user interactions
    },
    loop: true, // Enables infinite loop mode
  });
});
