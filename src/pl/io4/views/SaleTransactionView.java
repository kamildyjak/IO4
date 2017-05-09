package pl.io4.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import pl.io4.model.transactions.*;

import java.util.List;
import java.text.DecimalFormat;

import static pl.io4.NextGen.V_HEIGHT;
import static pl.io4.NextGen.V_WIDTH;

/**
 * Created by Zax37 on 14.03.2017.
 */

public class SaleTransactionView extends View {
    Table inner;
    private TextField productCodeTextField;
    private TextField productQuantityTextField;
    private TextField discountCodeTextField;
    private TextField discountQuantityTextField;

    private String formatQuantity(double quantity){
        int iQuantity = (int)quantity;
        if(quantity == iQuantity) return Integer.toString(iQuantity);
        return new DecimalFormat("0.00").format(quantity);
    }

    public void setProductsList(List<TransactionItem> transactionItems, List<DiscountItem> discountsItems, double priceTotal) {
        inner.clear();
        inner.add().expandY().row();
        for(TransactionItem item : transactionItems) {
            Product product = item.getProduct();
            inner.add(product.getName()).expandX().left();
            inner.add(new DecimalFormat("0.00").format(item.getProduct().getPrice())+" zł").expandX().center();
            inner.add("*").center();
            inner.add(formatQuantity(item.getQuantity())).expandX().center();
            inner.add("=").center();
            inner.add(new DecimalFormat("0.00").format(item.getTotalPrice())+" zł").expandX().right();
            inner.row();
        }

        for(DiscountItem discountItem : discountsItems) {
            String suffix = discountItem.getDiscount().getType() == DiscountType.PERCENTAGE ? " %" : " zł";
            inner.add(discountItem.getDiscount().getId()).expandX().left();
            inner.add("-" + new DecimalFormat("0.00").format(discountItem.getDiscount().getValue()) + suffix).expandX().center();
            inner.add("*").center();
            inner.add(formatQuantity(discountItem.getQuantity())).expandX().center();
            inner.add("=").center();
            inner.add("- " + new DecimalFormat("0.00").format(discountItem.getTotalDiscount()) + suffix).expandX().right();
            inner.row();
        }

        inner.add("Suma").padTop(20).expandX().left();
        inner.add("").colspan(4);
        inner.add(new DecimalFormat("0.00").format(priceTotal)+" zł").padTop(20).expandX().right();
        inner.row();
    }

    @Override
    protected void addViewElements() {
        Gdx.input.setInputProcessor(stage);

        inner = new Table(skin).background("white");
        inner.defaults().pad(10);
        ScrollPane scroll = new ScrollPane(inner);
        table.add(scroll).expand().left().bottom().width((int)(V_WIDTH*0.6)).height(V_HEIGHT);
        Table menu = new Table(skin).background("gray").bottom().padBottom(10);

        skin.add("textfieldback", new Texture("assets/raw/bg.png"));
        skin.add("cursor", new Texture("assets/raw/cursor.png"));
        skin.add("selection", new Texture("assets/raw/selection.png"));

        TextField.TextFieldStyle textfieldstyle = new TextField.TextFieldStyle();
        textfieldstyle.background = skin.getDrawable("textfieldback");
        textfieldstyle.disabledFontColor= Color.BLACK;
        textfieldstyle.font = skin.getFont("default");
        textfieldstyle.fontColor = Color.WHITE;
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

        table.add(menu).expand().right().bottom().width((int)(V_WIDTH*0.4)).height(V_HEIGHT);
        table.row();

        interactiveElements.put("addDiscountButton", addDiscountButton);
        interactiveElements.put("addProductButton", addProductButton);
        interactiveElements.put("scroll", scroll);
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
        Dialog popup = new Dialog("Błąd", skin);
        popup.text(message).pad(30, 30, 30, 30);
        popup.button("OK");
        popup.background("gray");
        popup.show(stage);
    }

    public void clearTextFields() {
        productCodeTextField.setText("");
        productQuantityTextField.setText("");
        discountCodeTextField.setText("");
        discountQuantityTextField.setText("");
    }



}
