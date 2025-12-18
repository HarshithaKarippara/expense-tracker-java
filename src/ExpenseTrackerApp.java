import javax.swing.*;

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

            if (choice == null || choice.equals("Exit")) {
                JOptionPane.showMessageDialog(null, "Goodbye!");
                break;
            }

            try {
                switch (choice) {
                    case "Add One-Time Expense":
                        OneTimeExpense e = createOneTime(manager);
                        manager.addTransaction(e);
                        JOptionPane.showMessageDialog(null, "Added:\n" + e);
                        break;

                    case "Add Recurring Expense":
                        RecurringExpense r = createRecurring(manager);
                        manager.addTransaction(r);
                        JOptionPane.showMessageDialog(null,
                                "Added:\n" + r + "\n\nProjected cost over 12 months: $" +
                                        String.format("%.2f", r.projectedCost(12)));
                        break;

                    case "List Expenses":
                        JOptionPane.showMessageDialog(null, formatTransactions(manager),
                                "All Expenses", JOptionPane.INFORMATION_MESSAGE);
                        break;

                    case "Show Summary":
                        JOptionPane.showMessageDialog(null, manager.summary(),
                                "Summary", JOptionPane.INFORMATION_MESSAGE);
                        break;

                    case "Save":
                        FileManager.saveTransactions(DATA_FILE, manager.getTransactions());
                        JOptionPane.showMessageDialog(null, "Saved to " + DATA_FILE);
                        break;

                    case "Load":
                        manager.setTransactions(FileManager.loadTransactions(DATA_FILE));
                        JOptionPane.showMessageDialog(null, "Loaded from " + DATA_FILE);
                        break;
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,
                        "Error: " + ex.getMessage(),
                        "Something went wrong",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static String makeId() {
        nextId++;
        return "T" + nextId;
    }

    private static OneTimeExpense createOneTime(BudgetManager manager) {
        String date = prompt("Date (YYYY-MM-DD):");
        double amount = promptDouble("Amount:");
        String category = prompt("Category (Food/Transportation/Subscriptions/Shopping/etc.):");
        String desc = prompt("Description:");
        return new OneTimeExpense(makeId(), date, amount, category, desc);
    }

    private static RecurringExpense createRecurring(BudgetManager manager) {
        String date = prompt("Start date (YYYY-MM-DD):");
        double amount = promptDouble("Amount:");
        String category = prompt("Category:");
        String desc = prompt("Description:");
        int interval = promptInt("Interval months (e.g., 1 for monthly):");
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

    // ===== Helper input methods (makes it feel polished) =====

    private static String prompt(String message) {
        while (true) {
            String input = JOptionPane.showInputDialog(null, message);
            if (input == null) throw new RuntimeException("Cancelled by user.");
            input = input.trim();
            if (!input.isEmpty()) return input;
            JOptionPane.showMessageDialog(null, "Please enter something.");
        }
    }

    private static double promptDouble(String message) {
        while (true) {
            String input = JOptionPane.showInputDialog(null, message);
            if (input == null) throw new RuntimeException("Cancelled by user.");
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

    private static int promptInt(String message) {
        while (true) {
            String input = JOptionPane.showInputDialog(null, message);
            if (input == null) throw new RuntimeException("Cancelled by user.");
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
