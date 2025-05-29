# 🥪 Sandwich Shop CLI

A Java-based Command-Line Interface (CLI) deli ordering system. Users can order sandwiches, drinks, and chips, view their cart, and checkout. Orders are saved to individual files and transaction logs for manager viewing. This is another capstone and an extenion of my Capstone Repository(https://github.com/AbrahamGhani/Capstones) most of my commits were made to that repository instead of this one by my error.

---


## ✨ Features

- Full custom sandwich builder with:
  - Bread type, size, toasted option
  - Meats & cheeses (extra optional)
  - Regular toppings (e.g. lettuce, tomato)
- Drink & chip selection with size or type options
- Role-based login:
  - Guest: Place order only
  - User: Save order to history
  - Manager: View all transaction logs
- Persistent logging:
  - Each order saved to its own file
  - Transaction log for manager
- File-based storage (CSV-style .txt)

---

## 🧪 Technologies Used

- Java 17+
- CLI (System.in / System.out)
- Object-Oriented Design (OOP)
- File I/O (BufferedReader/Writer)
- JUnit 5 (for tests)
- Maven

---

## 🚀 Installation

1. Clone the repository

```bash
git clone https://github.com/yourusername/Sandwich-Shop.git
cd Sandwich-Shop
```

2. Run via Maven

```bash
mvn compile exec:java -Dexec.mainClass="com.pluralsight.Main"
```

---

## 📖 Usage

- Navigate the menu to view items
- Build a sandwich or select combos
- Add to cart, then view and checkout
- At checkout, confirm and save transaction
- Orders are stored with timestamps
- Managers can view full transaction logs

---

## 🧠 Interesting Code Example

```java
double price = basePrice;
for (Topping topping : toppings) {
    if (isExtra(topping.getName())) {
        price += topping.isCheese() ? getCheesePrice(size, true) : getMeatPrice(size, true);
    }
}
```

This logic adjusts sandwich pricing based on topping type and size.

---


## 👤 Author

**Abraham Ghani**  
[GitHub Profile](https://github.com/AbrahamGhani)
