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

//CART Logic

// Mock cart data (replace with actual cart data)
const cartData = [
  {
    id: 1,
    name: "Console 1",
    price: 499,
    imgSrc: "https://i.imgur.com/sVPL9xD.png",
  }
];


// Function to render cart items
function renderCartItems() {
  const cartList = document.querySelector(".cart-list");
  const totalPriceElement = document.querySelector(".total-price");

  let totalPrice = 0;

  cartData.forEach((item) => {
    const listItem = document.createElement("li");
    listItem.innerHTML = `
      <div class="item-info">
        <img class="item-img" src="${item.imgSrc}" alt="${item.name}">
        <span>${item.name}</span>
      </div>
      <span class="item-price">$${item.price}</span>
    `;

    totalPrice += item.price;
    cartList.appendChild(listItem);
  });

  totalPriceElement.textContent = `$${totalPrice}`;
}

renderCartItems();

//Post request functionality to create an invoice:
document.getElementById("invoice-form").addEventListener("submit", async (event) => {
  event.preventDefault();
  const invoiceData = {
    customerName: document.getElementById("name").value,
    street: document.getElementById("street").value,
    city: document.getElementById("city").value,
    state: document.getElementById("state").value,
    zipcode: document.getElementById("zipcode").value,
    item_type: document.getElementById("item_type").value,
    item_id: document.getElementById("item_id").value,
    quantity: document.getElementById("quantity").value,
  };

  await createInvoice(invoiceData);
});

async function createInvoice(invoiceData) {
  try {
    const response = await fetch("https://your-api-url/invoices", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(invoiceData),
    });

    if (response.ok) {
      const invoice = await response.json();
      console.log("Invoice created:", invoice);
      // Add success message or redirect user to another page
    } else {
      console.error("Error creating invoice:", response.statusText);
      // Add error handling, e.g., show an error message
    }
  } catch (error) {
    console.error("Error creating invoice:", error);
    // Add error handling, e.g., show an error message
  }
}