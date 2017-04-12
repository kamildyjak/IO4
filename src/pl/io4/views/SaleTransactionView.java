package pl.io4.views;

import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import pl.io4.model.transactions.Product;
import pl.io4.model.transactions.TransactionItem;

import java.util.List;
import java.text.DecimalFormat;

import static pl.io4.NextGen.V_HEIGHT;
import static pl.io4.NextGen.V_WIDTH;

/**
 * Created by Zax37 on 14.03.2017.
 */

public class SaleTransactionView extends View {
    Table inner;

    private String formatQuantity(double quantity){
        int iQuantity = (int)quantity;
        if(quantity == iQuantity) return Integer.toString(iQuantity);
        return new DecimalFormat("0.00").format(quantity);
    }

    public void setProductsList(List<TransactionItem> list, double priceTotal) {
        inner.clear();
        inner.add().expandY().row();
        for(TransactionItem item : list) {
            Product product = item.getProduct();
            inner.add(product.getName()).expandX().left();
            inner.add(new DecimalFormat("0.00").format(item.getProduct().getPrice())+" zł").expandX().center();
            inner.add("*").center();
            inner.add(formatQuantity(item.getQuantity())).expandX().center();
            inner.add("=").center();
            inner.add(new DecimalFormat("0.00").format(item.getTotalPrice())+" zł").expandX().right();
            inner.row();
        }
        inner.add("Suma").padTop(20).expandX().left();
        inner.add("").colspan(4);
        inner.add(new DecimalFormat("0.00").format(priceTotal)+" zł").padTop(20).expandX().right();
        inner.row();
    }

    @Override
    protected void addViewElements() {
        inner = new Table(skin).background("white");
        inner.defaults().pad(10);
        ScrollPane scroll = new ScrollPane(inner);
        table.add(scroll).expand().left().bottom().width((int)(V_WIDTH*0.6)).height(V_HEIGHT);
        Table menu = new Table(skin).background("gray").bottom().padBottom(10);
        TextButton addButton = new TextButton("Dodaj", skin);
        menu.add(addButton);
        TextButton endButton = new TextButton("Zakończ transakcję", skin);
        menu.add(addButton);
        table.add(menu).expand().right().bottom().width((int)(V_WIDTH*0.4)).height(V_HEIGHT);
        table.row();

        interactiveElements.put("addButton", addButton);
        interactiveElements.put("scroll", scroll);
    }


}
