import java.util.ArrayList;

public class BudgetManager implements Summarizable {
    private ArrayList<Transaction> transactions;

    public BudgetManager() {
        transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public double totalForCategory(String category) {
        double total = 0;
        for (Transaction t : transactions) {
            if (t.getCategory().equalsIgnoreCase(category)) {
                total += t.getAmount();
            }
        }
        return total;
    }

    public double totalAll() {
        double total = 0;
        for (Transaction t : transactions) {
            total += t.getAmount();
        }
        return total;
    }

    @Override
    public String summary() {
        return "Transactions: " + transactions.size() + ", Total Spent: $" + String.format("%.2f", totalAll());
    }
}
