package pl.io4.views;

import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import static pl.io4.NextGen.V_HEIGHT;
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
        Table inner = new Table(skin).background("gray");
        inner.defaults().pad(PAD_SMALL);
        ScrollPane scroll = new ScrollPane(inner);
        table.add(scroll).expand().left().bottom().width(V_WIDTH).height(V_HEIGHT);

        TextButton startNewSaleTransaction = new TextButton(
                Model.getString("START_NEW_SALE_TRANSACTION"), skin);
        inner.add(startNewSaleTransaction);
        addElement("startNewSaleTransaction", startNewSaleTransaction);
    }
}
