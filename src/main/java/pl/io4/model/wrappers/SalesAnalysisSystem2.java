package pl.io4.model.wrappers;

import out.sales_analysis.SalesAnalysis2;
import pl.io4.model.transactions.SaleTransaction;

/**
 * Created by jperek on 12.06.2017.
 */
public class SalesAnalysisSystem2 implements SalesAnalysisSystem {
    public void addTransaction(SaleTransaction st) {
        SalesAnalysis2.add(st);
    }

    public String generateReport() {
        return SalesAnalysis2.generateReport();
    }
}
