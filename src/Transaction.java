
public abstract class Transaction implements Summarizable {
    private String id;
    private String date;        // YYYY-MM-DD
    private double amount;
    private String category;
    private String description;

    public Transaction(String id, String date, double amount, String category, String description) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.description = description;
    }

    public String getId() { return id; }
    public String getDate() { return date; }
    public double getAmount() { return amount; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }

    public abstract String toFileString();

    @Override
    public String toString() {
        return String.format("[%s] %s $%.2f (%s) - %s", id, date, amount, category, description);
    }

    @Override
    public String summary() {
        return toString();
    }
}
