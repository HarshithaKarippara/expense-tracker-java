# Test Plan – Personal Finance Expense Tracker

This test plan includes public tests (using the provided sample files) and release tests (additional instructor-style tests and edge cases). Each test case includes input, expected output, and actual results.

> **How to run tests manually:** Compile and run the program:
> ```bash
> javac src/*.java
> java -cp src ExpenseTrackerApp
> ```

---

## Public Tests (Files included in repo)

### Public Test Files
These files are included in the repository for grading and repeatable testing:
- `test-files/public_transactions.txt` (known-good file to load)
- `test-files/public_transactions_empty.txt` (empty file)
- `test-files/public_transactions_mixed.txt` (mixed one-time/recurring set)

---

### Public Test 1: Load Known-Good File (public_transactions.txt)
**Input (User Actions)**
1. Choose **Load**
2. Program loads from `test-files/transactions.txt` OR choose the file you set as your load path.
3. (If your program loads a fixed path, copy `public_transactions.txt` to that path name before running.)

**Expected Output**
- Message confirms loaded successfully
- Transactions list contains exactly the entries from the file
- No crash, no missing transactions

**Actual Results**
- PASS (loaded correctly) / FAIL (describe what happened): ______________________

---

### Public Test 2: List Expenses After Loading
**Input (User Actions)**
1. Load known-good file
2. Choose **List Expenses**

**Expected Output**
- A scrollable list appears
- Each transaction appears on its own line with correct ID/date/amount/category/description

**Actual Results**
- PASS / FAIL: ______________________

---

### Public Test 3: Summary After Loading
**Input (User Actions)**
1. Load known-good file
2. Choose **Show Summary**

**Expected Output**
- Summary shows correct number of transactions
- Summary total matches sum of amounts in file

**Actual Results**
- PASS / FAIL: ______________________

---

### Public Test 4: Save Then Load Round Trip
**Input (User Actions)**
1. Add a one-time expense:
   - Date: 2025-12-01
   - Amount: 15.50
   - Category: Food
   - Description: Lunch
2. Add a recurring expense:
   - Start date: 2025-12-01
   - Amount: 10.00
   - Category: Subscriptions
   - Description: Music
   - Interval months: 1
3. Choose **Save**
4. Close program
5. Re-run program and choose **Load**
6. Choose **List Expenses**

**Expected Output**
- The two expenses reappear exactly after load
- No data corruption (same fields)

**Actual Results**
- PASS / FAIL: ______________________

---

## Release Tests (Additional grading/edge-case tests)

### Release Test Files
These files are also included in the repo (instructor-style and edge cases):
- `test-files/release_transactions_badformat.txt` (invalid lines)
- `test-files/release_transactions_large.txt` (many entries)
- `test-files/release_transactions_case.txt` (category capitalization variation)

---

### Release Test 1: Cancel on Menu / Cancel During Input (GUI Robustness)
**Input (User Actions)**
1. Choose **Add One-Time Expense**
2. When prompted for Date, click **Cancel**

**Expected Output**
- Program does not crash
- Action is cancelled and returns to main menu (or shows “Action Cancelled” message)

**Actual Results**
- PASS / FAIL: ______________________

---

### Release Test 2: Negative Amount Validation
**Input (User Actions)**
1. Choose **Add One-Time Expense**
2. Enter:
   - Date: 2025-12-01
   - Amount: -25
   - Category: Food
   - Description: Test

**Expected Output**
- Error message shown (“Enter a valid non-negative number.”)
- User is prompted again until a valid amount is entered

**Actual Results**
- PASS / FAIL: ______________________

---

### Release Test 3: Recurring Interval Validation (0 or negative)
**Input (User Actions)**
1. Choose **Add Recurring Expense**
2. Enter:
   - Start date: 2025-12-01
   - Amount: 9.99
   - Category: Subscriptions
   - Description: Test
   - Interval months: 0

**Expected Output**
- Error message shown (“Enter a valid positive integer.”)
- User is prompted again until interval is valid

**Actual Results**
- PASS / FAIL: ______________________

---

### Release Test 4: Recursive Boundary Case (0 months)
**Input (User Actions)**
1. Add recurring expense with interval 1 and amount 10
2. Trigger projection (if your program prints it automatically) OR verify by temporary debug print in code.

**Expected Output**
- `projectedCost(0)` returns `0.0`

**Actual Results**
- PASS / FAIL: ______________________

---

### Release Test 5: Load Missing File
**Input (User Actions)**
1. Ensure the load file path does not exist (rename it temporarily)
2. Choose **Load**

**Expected Output**
- No crash
- Program loads empty list or shows a friendly message

**Actual Results**
- PASS / FAIL: ______________________

---

### Release Test 6: Load File With Bad Format Lines (release_transactions_badformat.txt)
**Input (Setup + Actions)**
1. Copy `release_transactions_badformat.txt` to your program’s expected load path (or rename it to the load filename)
2. Choose **Load**
3. Choose **List Expenses**

**Expected Output**
- Program does not crash
- Invalid lines are ignored (or handled safely)
- Valid lines still load

**Actual Results**
- PASS / FAIL: ______________________

---

### Release Test 7: Large File Performance (release_transactions_large.txt)
**Input (Setup + Actions)**
1. Copy `release_transactions_large.txt` to the program load path
2. Choose **Load**
3. Choose **List Expenses** and **Show Summary**

**Expected Output**
- Program remains responsive
- Summary totals correct
- List shows all entries (scrollable)

**Actual Results**
- PASS / FAIL: ______________________

---

### Release Test 8: Category Case Handling (release_transactions_case.txt)
**Input (Setup + Actions)**
1. Load `release_transactions_case.txt`
2. Use any feature that totals by category (if included) OR visually confirm all entries display correctly.

**Expected Output**
- Categories with different capitalization still behave correctly (no missing entries due to case mismatch)

**Actual Results**
- PASS / FAIL: ______________________

---

## Notes
- Public tests are designed to be simple and repeatable for grading.
- Release tests include edge cases that ensure robustness (bad input, cancel actions, missing files, and larger datasets).
