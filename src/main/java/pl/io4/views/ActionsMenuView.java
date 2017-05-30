package pl.io4.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import static pl.io4.NextGen.V_WIDTH;
import pl.io4.model.Model;

/**
 * Created by Zax37 on 23.05.2017.
 */
public final class ActionsMenuView extends View {
    @Override
    protected void addViewElements() {
        Skin skin = getSkin();
        Table table = getTable();
        Table inner = new Table(skin);
        inner.defaults().pad(PAD_SMALL);
        ScrollPane scroll = new ScrollPane(inner);

        table.center().background("gray");

        Label shop = new Label(Model.getString("SHOP") + ": "
                + Model.getCurrentlyChosenShop().getName(),
                new Label.LabelStyle(skin.getFont("heading"), Color.WHITE));
        Label shopAddress = new Label(Model.getCurrentlyChosenShop().getAddress(),
                new Label.LabelStyle(skin.getFont("default"), Color.WHITE));
        table.add(shop).padTop(PAD_SMALL);
        table.row();
        table.add(shopAddress);
        table.row();
        table.add(scroll).expand().left().width(V_WIDTH);

        TextButton startNewSaleTransaction = new TextButton(
                Model.getString("START_NEW_SALE_TRANSACTION"), skin);
        inner.add(startNewSaleTransaction).fillX();
        addElement("startNewSaleTransaction", startNewSaleTransaction);
        inner.row();
        TextButton logout = new TextButton(
                Model.getString("LOGOUT"), skin);
        inner.add(logout).fillX();
        addElement("logout", logout);
    }
}
