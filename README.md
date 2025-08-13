# Java Bank Management — Project Documentation

**Last updated:** 2025-08-13 09:25  
**Tech stack:** Java (Swing/AWT), Java SE 8+, File‑based persistence (plain text)

---

## 1) Overview

A lightweight desktop **banking simulation** built with Java Swing. It lets a user create an account, log in, and perform common wallet/banking actions using local text files as storage. It’s ideal for learning GUI programming, file I/O, and simple state management without databases.

**Key modules**
- **Authentication:** `login`, `signup`, `changePass`
- **Dashboard & Profile:** `home`
- **Transactions:** `addMoney`, `sendMoney`, `withdraw`, `payment`, `recharge`
- **History:** `history`

---

## 2) Features

- **Signup & Login** with account number + password
- **Profile info** (name, DOB, addresses, NID, blood group, gender, phone)
- **Balance management** (add money, withdraw)
- **Peer transfers** (send money to another account number)
- **Payments** (generic bill-like payment)
- **Mobile recharge** (operator selection + phone number)
- **Transaction history** per account (type, amount, counterparty/number, timestamp)
- **Change password**
- **Simple refresh** to re-read on-disk balance

> ⚠️ This is **not** production banking software. All data is stored in plain text without encryption and there’s no concurrency control. Use for learning/demo only.

---

## 3) Project Structure

```
Java_bank_management-java-main/
├─ addMoney.java
├─ changePass.java
├─ history.java
├─ home.java
├─ login.java
├─ payment.java
├─ recharge.java
├─ sendMoney.java
├─ signup.java
├─ withdraw.java
├─ balance/      # one file per account: <account>.txt containing current balance
├─ history/      # one file per account: <account>.txt transaction log
├─ userinfo/     # one file per account: <account>.txt profile + password
└─ README.md
```

### Storage conventions (plain text files)
- **`userinfo/<account>.txt`** — user profile + credentials  
  Expected fields (one per line or delimited):  
  `name, dob, presentAddress, permanentAddress, nid, blood, gender, phone, password`
- **`balance/<account>.txt`** — **single numeric** value representing current balance.
- **`history/<account>.txt`** — line‑oriented log; each transaction appended as:  
  `TYPE | AMOUNT | NUMBER/TO | TIMESTAMP`  
  (exact separators depend on implementation; keep consistent across writers)
  
> If a file does not exist yet, the corresponding screen typically creates it or shows a friendly error.

---

## 4) Application Flow

### 4.1 Authentication
1. **Login (`login`)**  
   - Input: *Account Number*, *Password*  
   - Reads `userinfo/<acc>.txt`, verifies password.  
   - On success → opens **Home** with loaded profile & balance.

2. **Signup (`signup`)**  
   - Collects profile fields and password; generates or asks for an **account number** (depending on UI).  
   - Creates `userinfo/<acc>.txt` with profile + password.  
   - Initializes `balance/<acc>.txt` with `0` and creates an empty `history/<acc>.txt`.

3. **Change Password (`changePass`)**  
   - Input: old password, new password, confirm.  
   - Verifies old password by reading `userinfo/<acc>.txt`, then overwrites with new one.

### 4.2 Dashboard (`home`)
- Displays **Name**, **Phone**, **Blood**, **Gender**, and **Current Balance**.
- Buttons route to: *Add Money*, *Send Money*, *Payment*, *Recharge*, *Withdraw*, *History*, *Change password*, *Logout*.
- **Refresh** button re-reads `balance/<acc>.txt`.

### 4.3 Transactions
For all money operations below, the pattern is:
- Read `balance/<acc>.txt` → validate sufficient funds (if debit) → compute new balance → write back.
- Append a line in `history/<acc>.txt` with the action summary.

1. **Add Money (`addMoney`)**
   - Inputs: *Amount* (+ optional password depending on screen)  
   - Updates balance of **current account**.  
   - History type: `ADD`

2. **Withdraw (`withdraw`)**
   - Inputs: *Amount*, *Password*  
   - Checks sufficient funds → debits balance.  
   - History type: `WITHDRAW`

3. **Send Money (`sendMoney`)**
   - Inputs: *Recipient Account Number*, *Amount*, *Password*  
   - Debits **sender** balance, credits `balance/<recipient>.txt`.  
   - Appends history to **both** accounts:  
     - Sender: `SEND | amount | to:<recipient>`  
     - Recipient: `RECEIVE | amount | from:<sender>`

4. **Payment (`payment`)**
   - Inputs: *Biller/Number/Ref*, *Amount*, *Password*
   - Debits balance, appends history: `PAYMENT | amount | ref:<id>`

5. **Recharge (`recharge`)**
   - Inputs: *Phone number*, *Amount*, *Operator* (Grameen/Banglalink/Robi/Airtel), *Password*
   - Debits balance, history: `RECHARGE | amount | number:<msisdn> | operator:<op>`

### 4.4 History (`history`)
- Opens `history/<acc>.txt` and renders in a scrollable text area with columns: **Type**, **Amount**, **Number/Ref**.

---

## 5) Building & Running

### Prerequisites
- **JDK 8+** (OpenJDK or Oracle JDK)
- OS: Windows/Linux/macOS

### Compile (from project root)
```bash
javac login.java
```

### Run
```bash
# Start from the login screen
java login
```

### First‑run setup
Ensure directories exist:
```bash
mkdir -p balance history userinfo
```
On Windows, create these folders manually if needed.

---

## 6) Data & Validation Rules

- **Account number** is a string; used as the base filename for that user.
- **Passwords** are stored in **plain text** inside `userinfo/<acc>.txt` (for learning only).
- **Amounts** are parsed as integers (or doubles) from text fields; validate that:
  - amount > 0
  - for debits: current balance ≥ amount
- File paths use platform separators (code often assumes Windows `\`), so prefer running on Windows or normalize paths if you port.

---

## 7) Error Handling & Edge Cases

- Missing files → show message dialogs and/or create default files.
- Incorrect password → show error, do not proceed.
- Non‑numeric amount → show validation message.
- Recipient not found (send money) → abort and show error.
- Concurrent writes are **not** handled; avoid running multiple instances altering same account.

---

## 8) Extending the Project

- **Use a DB**: replace plain‑text files with SQLite/H2. Map tables: `users`, `balances`, `transactions`.
- **Hash passwords**: apply SHA‑256/BCrypt + salt; never store plain text.
- **Service layer**: extract file I/O into `AccountService`, `TransactionService` to decouple UI from persistence.
- **Input masks**: validate phone/DOB/NID formats.
- **Currency & decimals**: use `BigDecimal` for money; add currency symbol/locale.
- **Unit tests**: JUnit tests for balance arithmetic and file I/O.
- **Cross‑platform paths**: use `Paths.get(...)` and `File.separator`.

---

## 9) Screens & Responsibilities (by class)

| Class         | Purpose |
|---------------|---------|
| `login`       | Authenticates by reading `userinfo/<acc>.txt`; launches `home` |
| `signup`      | Collects profile & password; writes `userinfo`, initializes `balance`, `history` |
| `home`        | Dashboard; displays profile & balance; navigation hub |
| `addMoney`    | Credit current account; updates `balance`; logs `ADD` |
| `withdraw`    | Debit current account; password check; logs `WITHDRAW` |
| `sendMoney`   | Transfer to another account; updates both accounts; logs `SEND`/`RECEIVE` |
| `payment`     | Generic bill payment; logs `PAYMENT` |
| `recharge`    | Mobile top‑up; operator selection; logs `RECHARGE` |
| `history`     | Renders transaction log from `history/<acc>.txt` |
| `changePass`  | Verifies old password; writes new password to `userinfo` |

---

## 10) Known Limitations

- No encryption or secure storage
- No input sanitization against malicious filenames
- No multi‑user locking; race conditions possible
- UI uses **absolute positioning**; not responsive
- Windows‑style paths used in places

---

## 11) Quick Start (Demo)

1. Create folders: `balance`, `history`, `userinfo`
2. Compile & run `login`
3. Click **Signup**, create a new user (note the **account number** you used)
4. Login with that account & password
5. Use **Add Money** to seed funds
6. Try **Send Money** to another test account
7. Open **History** to verify entries

---

## 12) License & Credits

Educational project for demonstrating Java Swing + File I/O.  
Original authorship: Project files inside `Java_bank_management-java-main`.
