package out.sales_analysis;

import pl.io4.model.transactions.SaleTransaction;

import java.util.ArrayList;

/**
 * Created by jperek on 12.06.2017.
 */
public class SalesAnalysis2 {
    static ArrayList<SaleTransaction> transactions = new ArrayList<>();

    public static void add(SaleTransaction st) { transactions.add(st); }
    public static void clearTransactions() { transactions.clear(); }
    public static String generateReport() {
        double val = 0.0;
        for (SaleTransaction st : transactions)
            val += st.calculateTotalPrice();
        String ret = "Total total price:" + String.valueOf(val);
        return ret;
    }
}
