package pl.io4.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import out.parsers.GifDecoder;
import pl.io4.model.Model;
import pl.io4.views.elements.AnimatedActor;
import pl.io4.views.elements.AnimationDrawable;

/**
 * Created by Zax37 on 14.03.2017.
 */

public final class LoadingView extends View {
    private AnimatedActor animation;

    private static final float ANIMATION_SPEED = 2.5f;

    @Override
    protected void addViewElements() {
        Skin skin = getSkin();
        Table table = getTable();
        BitmapFont defFont = skin.getFont("default"),
                titleFont = skin.getFont("heading");

        Animation ani = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP,
                Gdx.files.internal("ui/loading.gif").readBytes());
        animation = new AnimatedActor(new AnimationDrawable(ani, ANIMATION_SPEED));
        table.add(animation);
        table.row();
        addElement("animation", animation);

        Label title = new Label("NextGen",
                new Label.LabelStyle(titleFont, Color.WHITE));
        table.add(title).padTop(PAD_SMALL);
        table.row();

        Label testLabel = new Label(Model.getString("DATABASE_CONNECTING"),
                new Label.LabelStyle(defFont, Color.WHITE));
        table.add(testLabel).padTop(PAD_SMALL);
        table.row();
        addElement("testLabel", testLabel);
    }

}
