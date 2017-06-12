package pl.io4.model.wrappers;

import pl.io4.model.transactions.SaleTransaction;

/**
 * Created by jperek on 12.06.2017.
 */
public interface SalesAnalysisSystem {
    void addTransaction(SaleTransaction st);
    String generateReport();
}
