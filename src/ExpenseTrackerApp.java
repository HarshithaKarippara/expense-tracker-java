import java.util.Scanner;

public class ExpenseTrackerApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Personal Finance Expense Tracker ===");
            System.out.println("1) Add One-Time Expense (placeholder)");
            System.out.println("2) Exit");
            System.out.print("Choose: ");

            String choice = sc.nextLine().trim();

            if (choice.equals("1")) {
                System.out.println("Coming soon: add expense feature!");
            } else if (choice.equals("2")) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }

        sc.close();
    }
}