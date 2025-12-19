
import java.io.*;
import java.util.ArrayList;

public class FileManager {

    public static void saveTransactions(String filename, ArrayList<Transaction> transactions) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
            for (Transaction t : transactions) {
                out.println(t.toFileString());
            }
        }
    }

    public static ArrayList<Transaction> loadTransactions(String filename) throws IOException {
        ArrayList<Transaction> list = new ArrayList<>();
        File f = new File(filename);
        if (!f.exists()) return list;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                // IMPORTANT: must escape | in Java regex split
                String[] parts = line.split("\\|");
                if (parts.length < 6) continue;

                String type = parts[0];
                String id = parts[1];
                String date = parts[2];
                double amount = Double.parseDouble(parts[3]);
                String category = parts[4];
                String desc = parts[5];

                if (type.equals("ONE_TIME")) {
                    list.add(new OneTimeExpense(id, date, amount, category, desc));
                } else if (type.equals("RECURRING") && parts.length >= 7) {
                    int interval = Integer.parseInt(parts[6]);
                    list.add(new RecurringExpense(id, date, amount, category, desc, interval));
                }
            }
        }

        return list;
    }
}
