package pl.io4.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import java.util.List;
import static pl.io4.NextGen.V_HEIGHT;
import static pl.io4.NextGen.V_WIDTH;
import pl.io4.model.entities.Discount;
import pl.io4.model.entities.Product;
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
    private TextField discountQuantityTextField;

    private static final int PAD_SMALL = 10;
    private static final int PAD_BIG = 20;
    private static final int COLSPAN = 4;
    private static final double PANEL_PROPORTIONS = 0.6;

    public void setProductsList(List<TransactionItem> transactionItems, List<Discount> discounts, double priceTotal) {
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

        for (Discount discount : discounts) {
            String suffix;
            if (discount.getType() == Discount.DiscountType.PERCENTAGE) {
                suffix = " %";
            } else {
                suffix = LocalizationMachine.getCurrencySymbol();
            }
            inner.add("").colspan(COLSPAN + 1);
            inner.add("-" + LocalizationMachine.formatPrice(discount.getValue(),
                    false) + suffix).expandX().center();
            inner.row();
        }

        inner.add("Suma").padTop(PAD_BIG).expandX().left();
        inner.add("").colspan(COLSPAN);
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

        skin.add("textfieldback", new Texture("raw/bg.png"));
        skin.add("cursor", new Texture("raw/cursor.png"));
        skin.add("selection", new Texture("raw/selection.png"));

        TextField.TextFieldStyle textfieldstyle = new TextField.TextFieldStyle();
        textfieldstyle.background = skin.getDrawable("textfieldback");
        textfieldstyle.disabledFontColor = Color.BLACK;
        textfieldstyle.font = skin.getFont("default");
        textfieldstyle.fontColor = Color.BLACK;
        textfieldstyle.cursor = skin.getDrawable("cursor");
        textfieldstyle.selection = skin.getDrawable("selection");

        productCodeTextField = new TextField("", textfieldstyle);
        productCodeTextField.setPosition(100, 100);
        productCodeTextField.setSize(300, 40);
        productCodeTextField.setMessageText("Kod produktu");
        productCodeTextField.setMaxLength(20);
        menu.add(productCodeTextField).width(120).height(30).spaceTop(10);

        productQuantityTextField = new TextField("", textfieldstyle);
        productQuantityTextField.setPosition(100, 100);
        productQuantityTextField.setSize(300, 40);
        productQuantityTextField.setMessageText("Ilość");
        productQuantityTextField.setMaxLength(20);
        menu.add(productQuantityTextField).width(50).height(30).spaceTop(10);

        menu.row();

        TextButton addProductButton = new TextButton("Dodaj produkt", skin);
        menu.add(addProductButton).spaceTop(10);

        menu.row();

        discountCodeTextField = new TextField("", textfieldstyle);
        discountCodeTextField.setPosition(100, 100);
        discountCodeTextField.setSize(300, 40);
        discountCodeTextField.setMessageText("Kod zniżki");
        discountCodeTextField.setMaxLength(20);
        menu.add(discountCodeTextField).width(120).height(30).spaceTop(10);

        discountQuantityTextField = new TextField("", textfieldstyle);
        discountQuantityTextField.setPosition(100, 100);
        discountQuantityTextField.setSize(300, 40);
        discountQuantityTextField.setMessageText("Ilość");
        discountQuantityTextField.setMaxLength(20);
        menu.add(discountQuantityTextField).width(50).height(30).spaceTop(10);
        menu.row();

        TextButton addDiscountButton = new TextButton("Dodaj zniżkę", skin);
        menu.add(addDiscountButton).spaceTop(10);

        menu.row();

        TextButton endButton = new TextButton("Zakończ transakcję", skin);
        menu.add(endButton).spaceTop(10);

        table.add(menu).expand().right().bottom().width((int)(V_WIDTH * 0.4)).height(V_HEIGHT);
        table.row();

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

    public String getDiscountQuantity() {
        return discountQuantityTextField.getText();
    }

    public void addErrorMessage(String message) {
        Dialog popup = new Dialog("Błąd", getSkin());
        popup.text(message).pad(30, 30, 30, 30);
        popup.button("OK");
        popup.background("gray");
        popup.show(getStage());
    }

    public void clearTextFields() {
        productCodeTextField.setText("");
        productQuantityTextField.setText("");
        discountCodeTextField.setText("");
        discountQuantityTextField.setText("");
    }

}
