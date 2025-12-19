# Debugging Summary – Personal Finance Expense Tracker

Bug 1: Incorrect Parsing of Saved Transaction Data
Description of the Bug

When loading transactions from the saved text file, recurring expenses were not reconstructed correctly. In some cases, recurring expenses failed to load entirely, while in other cases the program threw runtime errors during file parsing.

How the Bug Was Identified

This issue was discovered while testing the save and load functionality. After saving transactions and restarting the program, loading the file did not restore all transactions as expected. Print statements were added to display how each line of the file was being parsed.

Cause

The program used split("|") to separate fields in each line of the file. In Java, the pipe character (|) is a special regular expression symbol and must be escaped. Because it was not escaped, the line was split incorrectly.

Fix

The split operation was corrected by escaping the pipe character:

line.split("\\|");


After applying this fix, both one-time and recurring transactions loaded correctly.

Tools and Techniques Used

Print debugging using System.out.println()

Manual inspection of saved text files

Testing with sample transaction files

Bug 2: Infinite Recursion in Projected Cost Calculation
Description of the Bug

The program crashed when calculating the projected cost of a recurring expense, resulting in a stack overflow error.

How the Bug Was Identified

The issue appeared during testing of the recurring expense feature. When attempting to calculate projected costs for several months, the application froze or crashed. The recursive method was manually traced using small input values.

Cause

The recursive method did not include a proper base case to stop recursion when the number of months reached zero or below.

Fix

A correct base case was added to the recursive method:

if (months <= 0) return 0.0;


This ensured the recursion terminated correctly and returned accurate results.

Tools and Techniques Used

Manual tracing of recursive calls

Testing with small input values

Logical reasoning about base and recursive cases

Bug 3: Program Crashed When User Clicked “Cancel” in Input Dialog
Description of the Bug

When using the graphical interface, the program would crash if the user clicked the Cancel button while entering information for a new expense.

How the Bug Was Identified

This bug was discovered during manual testing of the JOptionPane-based interface. Clicking Cancel caused the program to terminate unexpectedly. The stack trace revealed a NullPointerException.

Cause

The JOptionPane.showInputDialog() method returns null when the user clicks Cancel. The program attempted to call methods such as .trim() or parse the input as a number without checking for null.

Fix

Null checks were added to all input-handling helper methods. If the input is null, the program now safely cancels the current action and returns to the main menu instead of crashing.

Example fix:

if (input == null) return null;

Tools and Techniques Used

Manual GUI testing

Stack trace inspection

Defensive programming and input validation