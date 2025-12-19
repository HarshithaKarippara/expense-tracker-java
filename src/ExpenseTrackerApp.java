import javax.swing.*;
import java.io.IOException;

public class ExpenseTrackerApp {
    private static int nextId = 1000;
    private static final String DATA_FILE = "test-files/transactions.txt";

    public static void main(String[] args) {
        BudgetManager manager = new BudgetManager();

        while (true) {
            String choice = (String) JOptionPane.showInputDialog(
                    null,
                    "Choose an option:",
                    "Personal Finance Expense Tracker",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    new String[]{
                            "Add One-Time Expense",
                            "Add Recurring Expense",
                            "List Expenses",
                            "Show Summary",
                            "Save",
                            "Load",
                            "Exit"
                    },
                    "Add One-Time Expense"
            );

            // User closed the dialog or clicked Cancel on the menu
            if (choice == null) {
                continue; // return to menu
            }

            if (choice.equals("Exit")) {
                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to exit?",
                        "Confirm Exit",
                        JOptionPane.YES_NO_OPTION
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Goodbye!");
                    break;
                } else {
                    continue;
                }
            }

            try {
                switch (choice) {
                    case "Add One-Time Expense": {
                        OneTimeExpense e = createOneTime();
                        if (e != null) {
                            manager.addTransaction(e);
                            JOptionPane.showMessageDialog(null, "Added:\n" + e);
                        }
                        break;
                    }

                    case "Add Recurring Expense": {
                        RecurringExpense r = createRecurring();
                        if (r != null) {
                            manager.addTransaction(r);
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Added:\n" + r + "\n\nProjected cost over 12 months: $" +
                                            String.format("%.2f", r.projectedCost(12))
                            );
                        }
                        break;
                    }

                    case "List Expenses": {
                        showScrollableMessage("All Expenses", formatTransactions(manager));
                        break;
                    }

                    case "Show Summary": {
                        JOptionPane.showMessageDialog(
                                null,
                                manager.summary(),
                                "Summary",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                        break;
                    }

                    case "Save": {
                        FileManager.saveTransactions(DATA_FILE, manager.getTransactions());
                        JOptionPane.showMessageDialog(null, "Saved to:\n" + DATA_FILE);
                        break;
                    }

                    case "Load": {
                        manager.setTransactions(FileManager.loadTransactions(DATA_FILE));
                        JOptionPane.showMessageDialog(null, "Loaded from:\n" + DATA_FILE);
                        break;
                    }

                    default:
                        JOptionPane.showMessageDialog(null, "Unknown option.");
                }
            } catch (IOException ioe) {
                JOptionPane.showMessageDialog(
                        null,
                        "File error: " + ioe.getMessage(),
                        "File I/O Error",
                        JOptionPane.ERROR_MESSAGE
                );
            } catch (RuntimeException re) {
                // Covers user-cancel actions we intentionally throw in helper functions
                JOptionPane.showMessageDialog(
                        null,
                        re.getMessage(),
                        "Action Cancelled",
                        JOptionPane.INFORMATION_MESSAGE
                );
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        null,
                        "Error: " + ex.getMessage(),
                        "Something went wrong",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    private static String makeId() {
        nextId++;
        return "T" + nextId;
    }

    private static OneTimeExpense createOneTime() {
        String date = prompt("Date (YYYY-MM-DD):");
        if (date == null) return null;

        Double amount = promptDouble("Amount:");
        if (amount == null) return null;

        String category = prompt("Category (Food/Transportation/Subscriptions/Shopping/etc.):");
        if (category == null) return null;

        String desc = prompt("Description:");
        if (desc == null) return null;

        return new OneTimeExpense(makeId(), date, amount, category, desc);
    }

    private static RecurringExpense createRecurring() {
        String date = prompt("Start date (YYYY-MM-DD):");
        if (date == null) return null;

        Double amount = promptDouble("Amount:");
        if (amount == null) return null;

        String category = prompt("Category:");
        if (category == null) return null;

        String desc = prompt("Description:");
        if (desc == null) return null;

        Integer interval = promptInt("Interval months (e.g., 1 for monthly):");
        if (interval == null) return null;

        return new RecurringExpense(makeId(), date, amount, category, desc, interval);
    }

    private static String formatTransactions(BudgetManager manager) {
        if (manager.getTransactions().isEmpty()) return "No expenses yet.";

        StringBuilder sb = new StringBuilder();
        for (Transaction t : manager.getTransactions()) {
            sb.append(t).append("\n");
        }
        return sb.toString();
    }

    // === Professional UI helper: scrollable output ===
    private static void showScrollableMessage(String title, String message) {
        JTextArea area = new JTextArea(message, 18, 45);
        area.setEditable(false);
        JScrollPane pane = new JScrollPane(area);
        JOptionPane.showMessageDialog(null, pane, title, JOptionPane.INFORMATION_MESSAGE);
    }

    // === Input helpers: return null if user cancels ===
    private static String prompt(String message) {
        while (true) {
            String input = JOptionPane.showInputDialog(null, message);
            if (input == null) return null; // user cancelled
            input = input.trim();
            if (!input.isEmpty()) return input;
            JOptionPane.showMessageDialog(null, "Please enter something.");
        }
    }

    private static Double promptDouble(String message) {
        while (true) {
            String input = JOptionPane.showInputDialog(null, message);
            if (input == null) return null;
            input = input.trim();
            try {
                double val = Double.parseDouble(input);
                if (val < 0) throw new NumberFormatException();
                return val;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Enter a valid non-negative number.");
            }
        }
    }

    private static Integer promptInt(String message) {
        while (true) {
            String input = JOptionPane.showInputDialog(null, message);
            if (input == null) return null;
            input = input.trim();
            try {
                int val = Integer.parseInt(input);
                if (val <= 0) throw new NumberFormatException();
                return val;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Enter a valid positive integer.");
            }
        }
    }
}
