package pl.io4.model.wrappers;

import pl.io4.model.transactions.SaleTransaction;

/**
 * Created by Marcin on 28.05.2017.
 */
interface ReceiptGenerator {

    void generateReceipt(SaleTransaction transaction);
}
