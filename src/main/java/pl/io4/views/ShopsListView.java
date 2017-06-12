package pl.io4.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import static pl.io4.NextGen.V_WIDTH;
import pl.io4.model.Model;
import pl.io4.model.entities.Shop;

/**
 * Created by Zax37 on 30.05.2017.
 */
public final class ShopsListView extends View {
    @Override
    protected void addViewElements() {
        Skin skin = getSkin();
        Table table = getTable();
        Table inner = new Table(skin);
        inner.defaults().pad(PAD_SMALL);
        ScrollPane scroll = new ScrollPane(inner);

        table.center().background("gray");

        Label title = new Label(Model.getString("CHOOSE_SHOP"),
                new Label.LabelStyle(skin.getFont("heading"), Color.WHITE));
        table.add(title).padTop(PAD_SMALL);
        table.row();
        table.add(scroll).expand().left().width(V_WIDTH);

        for (Shop shop : Model.getShopsMachine().getShops()) {
            TextButton shopI = new TextButton(shop.getName(), skin);
            inner.add(shopI).fillX();
            addElement(Integer.toString(shop.getId()), shopI);
            inner.row();
        }
        TextButton logout = new TextButton(
                Model.getString("LOGOUT"), skin);
        inner.add(logout).fillX();
        addElement("logout", logout);
    }
}
