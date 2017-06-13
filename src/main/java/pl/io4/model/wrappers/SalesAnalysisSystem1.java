package pl.io4.model.wrappers;

import out.sales_analysis.SalesAnalysis1;
import pl.io4.model.transactions.SaleTransaction;

/**
 * Created by jperek on 12.06.2017.
 */
public final class SalesAnalysisSystem1 implements SalesAnalysisSystem {
    public void addTransaction(SaleTransaction st) {
        SalesAnalysis1.addTransaction(st);
    }

    public String generateReport() {
        return SalesAnalysis1.getReport();
    }
}
