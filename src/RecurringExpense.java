



public class RecurringExpense extends Transaction {
    private int intervalMonths; // e.g., 1 for monthly

    public RecurringExpense(String id, String date, double amount, String category, String description, int intervalMonths) {
        super(id, date, amount, category, description);
        this.intervalMonths = intervalMonths;
    }

    public int getIntervalMonths() {
        return intervalMonths;
    }

    // Recursion: total cost over N months
    public double projectedCost(int months) {
        if (months <= 0) return 0.0;
        return getAmount() + projectedCost(months - intervalMonths);
    }

    @Override
    public String toFileString() {
        return String.join("|",
                "RECURRING",
                getId(),
                getDate(),
                String.valueOf(getAmount()),
                getCategory(),
                getDescription(),
                String.valueOf(intervalMonths)
        );
    }

    @Override
    public String toString() {
        return super.toString() + " (recurring every " + intervalMonths + " month(s))";
    }
}
