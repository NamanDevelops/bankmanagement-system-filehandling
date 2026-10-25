# Bank Management System (File Handling)

## Project Description
A console-based Bank Management System built in Java that allows users to manage bank accounts, perform transactions, and persistently store account details using Java File Handling (`accounts.txt`).

## Features
* **Create Account:** Open a new bank account with initial details and balance.
* **Deposit Money:** Add funds to an existing account.
* **Withdraw Money:** Deduct funds from an account with sufficient balance checks.
* **View Details:** Check current account balance and holder information.
* **Transfer Money:** Transfer Money From 1 Account to another account if both are valid.
* **Persistent Storage:** Automatically saves and loads account records from `accounts.txt` so data is not lost when the program closes.
* **Input Validation:** Ensures valid account numbers, amounts, and user inputs using `ValidationUtil`.

## Technologies Used
* **Language:** Java (JDK)
* **IDE:** Visual Studio Code (VS Code)
* **Data Storage:** Text File (`accounts.txt`)
* **Version Control:** Git & GitHub

## Java Concepts Used
* **Object-Oriented Programming (OOP):** Classes, Objects, Encapsulation, and Modularity (`Account`, `Bank`, `Main`).
* **File Handling (I/O):** Reading from and writing to text files using `File`, `FileReader`, `FileWriter`, `BufferedReader`, and `BufferedWriter` (implemented in `AccountFileHandler.java`).
* **Exception Handling:** `try-catch` blocks to handle `IOException` and invalid user inputs gracefully.
* **Collections Framework:** Managing multiple account records dynamically in memory.
* **Utility Classes & String Manipulation:** Parsing file data and validating user input (`ValidationUtil.java`).

## How to Run the Project
1. **Clone or Download the Repository:**
   ```bash
   git clone [https://github.com/NamanDevlops/bankmanagement-system-filehandling.git](https://github.com/NamanDevlops/bankmanagement-system-filehandling.git)

## Sample Output

*********************************
Banking Management System
*********************************
1: Create Account
2: View All Account
3: Search Account
4: Deposit Money
5: Withdraw Money
6: Transfer Money
7: Delete Account
8: Exit
--------------------------------
Enter Your Choice:
1
Enter Account Number :
123456789
Enter Account holder Name :
MS Dhoni
Enter Account holder Age :
45
Enter Initial Balance in rs (No Unit):
2000

Account Created Successfully
