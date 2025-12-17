import java.util.Scanner;

public class ExpenseTrackerApp {
    private static int nextId = 1000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BudgetManager manager = new BudgetManager();

        while (true) {
            System.out.println("\n=== Personal Finance Expense Tracker ===");
            System.out.println("1) Add One-Time Expense");
            System.out.println("2) Add Recurring Expense");
            System.out.println("3) List Expenses");
            System.out.println("4) Show Summary");
            System.out.println("5) Exit");
            System.out.print("Choose: ");

            String choice = sc.nextLine().trim();

            if (choice.equals("1")) {
                OneTimeExpense e = createOneTime(sc);
                manager.addTransaction(e);
                System.out.println("Added: " + e);

            } else if (choice.equals("2")) {
                RecurringExpense r = createRecurring(sc);
                manager.addTransaction(r);
                System.out.println("Added: " + r);
                System.out.println("Projected cost over 12 months: $" + String.format("%.2f", r.projectedCost(12)));

            } else if (choice.equals("3")) {
                if (manager.getTransactions().isEmpty()) {
                    System.out.println("No expenses yet.");
                } else {
                    for (Transaction t : manager.getTransactions()) {
                        System.out.println(t);
                    }
                }

            } else if (choice.equals("4")) {
                System.out.println(manager.summary());

            } else if (choice.equals("5")) {
                System.out.println("Goodbye!");
                break;

            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }

        sc.close();
    }

    private static String makeId() {
        nextId++;
        return "T" + nextId;
    }

    private static OneTimeExpense createOneTime(Scanner sc) {
        System.out.print("Date (YYYY-MM-DD): ");
        String date = sc.nextLine().trim();

        System.out.print("Amount: ");
        double amount = Double.parseDouble(sc.nextLine().trim());

        System.out.print("Category: ");
        String category = sc.nextLine().trim();

        System.out.print("Description: ");
        String desc = sc.nextLine().trim();

        return new OneTimeExpense(makeId(), date, amount, category, desc);
    }

    private static RecurringExpense createRecurring(Scanner sc) {
        System.out.print("Start date (YYYY-MM-DD): ");
        String date = sc.nextLine().trim();

        System.out.print("Amount: ");
        double amount = Double.parseDouble(sc.nextLine().trim());

        System.out.print("Category: ");
        String category = sc.nextLine().trim();

        System.out.print("Description: ");
        String desc = sc.nextLine().trim();

        System.out.print("Interval months (e.g., 1): ");
        int interval = Integer.parseInt(sc.nextLine().trim());

        return new RecurringExpense(makeId(), date, amount, category, desc, interval);
    }
}
