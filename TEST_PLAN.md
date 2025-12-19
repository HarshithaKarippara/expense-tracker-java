
---

# 2️⃣ TEST_PLAN.md

Last updated: December 2025


```markdown
# Test Plan – Personal Finance Expense Tracker

## Test Environment
- Java JDK 17
- VS Code
- Windows / macOS

---

## Test Case 1: Add One-Time Expense
**Input**
- Date: 2025-12-01
- Amount: 15.50
- Category: Food
- Description: Lunch

**Expected Output**
- Expense is added and displayed in the list

**Actual Result**
- Expense added successfully and shown in expense list

---

## Test Case 2: Add Recurring Expense
**Input**
- Date: 2025-12-01
- Amount: 10.00
- Category: Subscriptions
- Description: Streaming Service
- Interval: 1 month

**Expected Output**
- Expense added
- Projected 12-month cost = $120.00

**Actual Result**
- Expense added
- Projected cost calculated correctly

---

## Test Case 3: Recursive Cost Calculation
**Input**
- Monthly recurring expense of $5 for 6 months

**Expected Output**
- Total projected cost = $30.00

**Actual Result**
- Recursive method returned correct total

---

## Test Case 4: Save Transactions
**Input**
- Save with existing expenses

**Expected Output**
- Text file created with correct data

**Actual Result**
- File saved successfully

---

## Test Case 5: Load Transactions
**Input**
- Load previously saved file

**Expected Output**
- Expenses restored correctly

**Actual Result**
- Expenses loaded correctly

---

## Test Case 6: Invalid Numeric Input
**Input**
- Non-numeric value for amount

**Expected Output**
- Error message and reprompt

**Actual Result**
- User prompted again until valid input entered
 ---

## Additional Test Cases

### Test Case 7: Cancel Input Using Dialog
**Input**
- User clicks “Cancel” on an input dialog

**Expected Output**
- Program safely exits the current action or returns to menu without crashing

**Actual Result**
- (Fill after running)

---

### Test Case 8: Negative Amount Entry
**Input**
- Amount entered: `-25`

**Expected Output**
- Error shown and user is prompted again

**Actual Result**
- (Fill after running)

---

### Test Case 9: Interval Months = 0 for Recurring Expense
**Input**
- Interval months entered: `0`

**Expected Output**
- Error shown and user is prompted again

**Actual Result**
- (Fill after running)

---

### Test Case 10: Empty List Summary
**Input**
- Choose “Show Summary” when no expenses exist

**Expected Output**
- Summary shows 0 transactions and total $0.00 (or similar)

**Actual Result**
- (Fill after running)

---

### Test Case 11: Load When File Missing
**Input**
- Load when file does not exist

**Expected Output**
- Program does not crash; loads empty list and informs user

**Actual Result**
- (Fill after running)

---

### Test Case 12: Save Then Load Round Trip
**Input**
- Add 2 expenses → Save → Restart program → Load

**Expected Output**
- Same expenses restored exactly

**Actual Result**
- (Fill after running)

---

### Test Case 13: Recursive Boundary Case
**Input**
- Projected cost for `0` months

**Expected Output**
- Result is `0.0`

**Actual Result**
- (Fill after running)
