package pl.io4.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import static pl.io4.NextGen.V_WIDTH;
import pl.io4.model.Model;
import pl.io4.model.machines.LocalizationMachine;

/**
 * Created by Zax37 on 23.05.2017.
 */
public final class PaymentView extends View {
    private double totalPrice = 0;
    private Label total;
    private Table inner;
    private Skin skin;
    private Button returnSale, returnPaymentSelect, payByCash, payWithCard, acceptPayment;
    private TextField acceptCash;

    @Override
    protected void addViewElements() {
        Table table = getTable().background("gray");
        skin = getSkin();
        inner = new Table(skin);
        inner.defaults().pad(PAD_SMALL);
        BitmapFont titleFont = skin.getFont("heading");

        table.center();

        total = new Label(Model.getString("TOTAL") + ": "
                + LocalizationMachine.formatPrice(totalPrice, true),
                new Label.LabelStyle(titleFont, Color.WHITE));
        table.add(total).padTop(PAD_SMALL);
        table.row();

        ScrollPane scroll = new ScrollPane(inner);
        table.add(scroll).expand().left().width(V_WIDTH);

        returnSale = new TextButton(Model.getString("PAY_RETURN_SALE"), skin);
        addElement("returnSale", returnSale);
        returnPaymentSelect = new TextButton(Model.getString("PAY_RETURN_SELECT_METHOD"), skin);
        addElement("returnPaymentSelect", returnPaymentSelect);
        payByCash = new TextButton(Model.getString("PAY_BY_CASH"), skin);
        addElement("payByCash", payByCash);
        payWithCard = new TextButton(Model.getString("PAY_WITH_CARD"), skin);
        addElement("payWithCard", payWithCard);
        acceptCash = new TextField("", skin);
        acceptCash.setMessageText("");
        addElement("acceptCash", acceptCash);
        acceptPayment = new TextButton(Model.getString("PAY_BY_CASH"), skin);
        addElement("acceptPayment", acceptPayment);

        showPaymentMethods();
    }

    public void setTotalPrice(double totalPrice) {
        total.setText(Model.getString("TOTAL") + ": "
                + LocalizationMachine.formatPrice(totalPrice, true));
    }

    public void showPaymentMethods() {
        inner.clear();
        inner.add(payByCash).fillX();
        inner.row();
        inner.add(payWithCard).fillX();
        inner.row();
        inner.add(returnSale).fillX();
    }

    public void showPayByCash() {
        inner.clear();
        inner.add(acceptCash).fillX();
        inner.row();
        inner.add(acceptPayment).fillX();
        inner.row();
        inner.add(returnPaymentSelect).fillX();
    }

    public void showPayWithCard() {
        inner.clear();
        inner.add(returnPaymentSelect).fillX();
    }

    public void showCashBack(double amount) {
        inner.clear();
        Label label = new Label(Model.getString("PAY_GIVE_OUT") + ": " +
                LocalizationMachine.formatPrice(amount, true), skin);
        inner.add(label).fillX();
        inner.row();
        TextButton finalize = new TextButton(Model.getString("PAY_FINALIZE"), skin);
        addElement("finalize", finalize);
        inner.add(finalize).fillX();
    }
}
