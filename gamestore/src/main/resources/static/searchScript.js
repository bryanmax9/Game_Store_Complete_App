// This script is only for reference:
// Here, we are just simulating withfull js how the games, consoles,
// and t-shirts would be displayed in the website


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



// Getting the objects from get request
// Sample data
const games = [
  { id: 1, title: 'Game', imageUrl: 'https://i.imgur.com/9dDFYB9.png', price: 49.99, description: 'Futuristic Video Game' },
  { id: 2, title: 'Game', imageUrl: 'https://i.imgur.com/9dDFYB9.png', price: 49.99, description: 'Super Mario 70' },
  { id: 1, title: 'Console', imageUrl: 'https://i.imgur.com/sVPL9xD.png', price: 450.98, description: 'Amazing Play  Station 20' },
  { id: 1, title: 'T-Shirt', imageUrl: 'https://i.imgur.com/IMKWUEB.png', price: 39.99, description: 'Ai generated T-shirt' },
  { id: 3, title: 'Game', imageUrl: 'https://i.imgur.com/9dDFYB9.png', price: 49.99, description: 'Ark Survival V' },
  { id: 2, title: 'Console', imageUrl: 'https://i.imgur.com/sVPL9xD.png', price: 450.98, description: 'Amazing Nintendo 100' },
  { id: 2, title: 'T-Shirt', imageUrl: 'https://i.imgur.com/IMKWUEB.png', price: 39.99, description: 'Future T-shirt Space' },
  { id: 3, title: 'T-Shirt', imageUrl: 'https://i.imgur.com/IMKWUEB.png', price: 39.99, description: 'Futuristic Society Space T-shirt' }
];

// Function to create a game item
function createGameItem(game) {
  const gameItem = document.createElement('div');
  gameItem.classList.add('game-item');

  const gameImage = document.createElement('img');
  gameImage.src = game.imageUrl;
  gameImage.alt = game.title;

  const gameId = document.createElement('p');
  gameId.textContent = `ID: ${game.id}`;

  const gameTitle = document.createElement('h3');
  gameTitle.textContent = game.title;

  const gamePrice = document.createElement('p');
  gamePrice.textContent = `Price: $${game.price}`;

  const gameDescription = document.createElement('p');
  gameDescription.textContent = game.description;

  const checkoutButton = document.createElement('button');
  checkoutButton.classList.add('checkout-button');// button sttyle class
  checkoutButton.textContent = 'Check Out';
  checkoutButton.addEventListener('click', () => {
    window.location.href = 'https://example.com/check-out'; // Replace with your Check Out page URL
  });

  gameItem.appendChild(gameImage);
  gameItem.appendChild(gameId);
  gameItem.appendChild(gameTitle);
  gameItem.appendChild(gamePrice);
  gameItem.appendChild(gameDescription);
  gameItem.appendChild(checkoutButton);




  return gameItem;
}


// Function to display game items
function displayGameItems(games) {
  const gameItemsContainer = document.querySelector('.game-items');
  games.forEach(game => {
    const gameItem = createGameItem(game);
    gameItemsContainer.appendChild(gameItem);
  });
}

// Display game items on page load
displayGameItems(games);