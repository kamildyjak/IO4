package pl.io4.views;

import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import java.util.List;
import static pl.io4.NextGen.V_HEIGHT;
import static pl.io4.NextGen.V_WIDTH;

import pl.io4.model.Model;
import pl.io4.model.entities.Discount;
import pl.io4.model.entities.Product;
import pl.io4.model.machines.DiscountsMachine;
import pl.io4.model.machines.LocalizationMachine;
import pl.io4.model.transactions.TransactionItem;

/**
 * Created by Zax37 on 14.03.2017.
 */

public final class SaleTransactionView extends View {
    private Table inner;
    private TextField productCodeTextField;
    private TextField productQuantityTextField;
    private TextField discountCodeTextField;

    private static final int COLUMNS_COUNT_PRODUCTS_LIST_DEFAULT = 6;
    private static final int COLUMNS_COUNT_PRODUCTS_LIST_SUM_ROW = 2;
    private static final int COLUMNS_COUNT_MENU = 2;
    private static final double PANEL_PROPORTIONS = 0.6;
    private static final int INPUT_MAX_LENGTH = 20;

    public void setProductsList(List<TransactionItem> transactionItems,
                                DiscountsMachine discountsMachine, double priceTotal) {
        inner.clear();
        inner.add().expandY().row();
        for (TransactionItem item : transactionItems) {
            Product product = item.getProduct();
            inner.add(product.getName()).expandX().left();
            inner.add(LocalizationMachine.formatPrice(item.getProduct().getPrice(), true)).expandX().center();
            inner.add("*").center();
            inner.add(LocalizationMachine.formatQuantity(item.getQuantity())).expandX().center();
            inner.add("=").center();
            inner.add(LocalizationMachine.formatPrice(item.getTotalPrice(), true)).expandX().right();
            inner.row();
        }

        if (!discountsMachine.getDiscounts().isEmpty()) {
            inner.add("ZNIŻKI:").expandX().left();
            inner.add("").colspan(COLUMNS_COUNT_PRODUCTS_LIST_DEFAULT - 1);
            inner.row();

            for (Discount.DiscountType type : Discount.DiscountType.values()) {
                String suffix;
                if (type == Discount.DiscountType.PERCENTAGE) {
                    suffix = " %";
                } else {
                    suffix = LocalizationMachine.getCurrencySymbol();
                }
                double value = discountsMachine.getTotalDiscount(type);
                if (value > 0) {
                    inner.add("").colspan(COLUMNS_COUNT_PRODUCTS_LIST_DEFAULT - 1);
                    inner.add("-" + LocalizationMachine.formatPrice(value,
                            false) + suffix).expandX().right();
                    inner.row();
                }
            }
        }
        inner.add("Suma").padTop(PAD_BIG).expandX().left();
        inner.add("").colspan(COLUMNS_COUNT_PRODUCTS_LIST_DEFAULT - COLUMNS_COUNT_PRODUCTS_LIST_SUM_ROW);
        inner.add(LocalizationMachine.formatPrice(priceTotal, true)).padTop(PAD_BIG).expandX().right();
        inner.row();
    }

    @Override
    protected void addViewElements() {
        Skin skin = getSkin();
        Table table = getTable();
        inner = new Table(skin).background("white");
        inner.defaults().pad(PAD_SMALL);
        ScrollPane scroll = new ScrollPane(inner);
        table.add(scroll).expand().left().bottom()
                .width((int)(V_WIDTH * PANEL_PROPORTIONS)).height(V_HEIGHT);
        Table menu = new Table(skin).background("gray").bottom().padBottom(PAD_SMALL);
        table.add(menu).expand().right().bottom()
                .width((int)(V_WIDTH * (1 - PANEL_PROPORTIONS))).height(V_HEIGHT);
        table.row();

        productCodeTextField = new TextField("", skin);
        productCodeTextField.setMessageText(Model.getString("PRODUCT_CODE"));
        productCodeTextField.setMaxLength(INPUT_MAX_LENGTH);
        menu.add(productCodeTextField).spaceTop(PAD_SMALL);

        productQuantityTextField = new TextField("", skin);
        productQuantityTextField.setMessageText(Model.getString("QUANTITY"));
        productQuantityTextField.setMaxLength(INPUT_MAX_LENGTH);
        menu.add(productQuantityTextField).padLeft(PAD_SMALL).spaceTop(PAD_SMALL);

        menu.row();

        TextButton addProductButton = new TextButton(Model.getString("ADD_PRODUCT"), skin);
        menu.add(addProductButton).colspan(COLUMNS_COUNT_MENU)
                .fillX().spaceTop(PAD_SMALL).spaceBottom(PAD_BIG);

        menu.row();

        discountCodeTextField = new TextField("", skin);
        discountCodeTextField.setMessageText(Model.getString("DISCOUNT_CODE"));
        discountCodeTextField.setMaxLength(INPUT_MAX_LENGTH);
        menu.add(discountCodeTextField).colspan(COLUMNS_COUNT_MENU)
                .fillX().spaceTop(PAD_SMALL);

        menu.row();

        TextButton addDiscountButton = new TextButton(Model.getString("ADD_DISCOUNT"), skin);
        menu.add(addDiscountButton).colspan(COLUMNS_COUNT_MENU)
                .fillX().spaceTop(PAD_SMALL);

        menu.row();

        TextButton endButton = new TextButton(Model.getString("PAY"), skin);
        menu.add(endButton).colspan(COLUMNS_COUNT_MENU)
                .fillX().spaceTop(PAD_SMALL);

        menu.row();

        addElement("addDiscountButton", addDiscountButton);
        addElement("addProductButton", addProductButton);
        addElement("scroll", scroll);
        addElement("endButton", endButton);
    }

    public String getProductCode() {
        return productCodeTextField.getText();
    }

    public String getProductQuantity() {
        return productQuantityTextField.getText();
    }

    public String getDiscountCode() {
        return discountCodeTextField.getText();
    }

    public void clearTextFields() {
        productCodeTextField.setText("");
        productQuantityTextField.setText("");
        discountCodeTextField.setText("");
    }

}
