package out.sales_analysis;

import pl.io4.model.transactions.SaleTransaction;

import java.util.ArrayList;

/**
 * Created by jperek on 12.06.2017.
 */
public class SalesAnalysis1 {
    static ArrayList<SaleTransaction> transactions = new ArrayList<>();

    public static void addTransaction(SaleTransaction st) { transactions.add(st); }
    public static String getReport() {
        double val = 0.0;
        for (SaleTransaction st : transactions)
            val += st.calculateTotalPrice();
        String ret = "Overall sum = " + String.valueOf(val);
        return ret;
    }
}
