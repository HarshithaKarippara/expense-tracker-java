

public class OneTimeExpense extends Transaction {
    public OneTimeExpense(String id, String date, double amount, String category, String description) {
        super(id, date, amount, category, description);
    }

    @Override
    public String toFileString() {
        return String.join("|",
                "ONE_TIME",
                getId(),
                getDate(),
                String.valueOf(getAmount()),
                getCategory(),
                getDescription()
        );
    }
}
