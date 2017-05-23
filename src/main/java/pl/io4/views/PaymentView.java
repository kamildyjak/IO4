package pl.io4.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import static pl.io4.NextGen.V_WIDTH;
import pl.io4.model.Model;
import pl.io4.model.machines.LocalizationMachine;

/**
 * Created by Zax37 on 23.05.2017.
 */
public final class PaymentView extends View {
    private static final int PAD_SMALL = 10;
    private double totalPrice = 0;
    private Label total;

    @Override
    protected void addViewElements() {
        Skin skin = getSkin();
        Table table = getTable().background("gray");
        Table inner = new Table(skin);
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

        TextButton payByCash = new TextButton(
                Model.getString("PAY_BY_CASH"), skin);
        inner.add(payByCash);
        inner.row();
        addElement("payByCash", payByCash);

        TextButton payWithCard = new TextButton(
                Model.getString("PAY_WITH_CARD"), skin);
        inner.add(payWithCard);
        addElement("payWithCard", payWithCard);
    }

    public void setTotalPrice(double totalPrice) {
        total.setText(Model.getString("TOTAL") + ": "
                + LocalizationMachine.formatPrice(totalPrice, true));
    }
}
