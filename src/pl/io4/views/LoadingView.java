package pl.io4.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import pl.io4.model.Model;
import pl.io4.model.parsers.GifDecoder;
import pl.io4.views.elements.AnimatedActor;
import pl.io4.views.elements.AnimationDrawable;

/**
 * Created by Zax37 on 14.03.2017.
 */

public class LoadingView extends View {

    private TextButton button;
    private AnimatedActor animation;
    private float time = 0;

    @Override
    public void render(float delta) {
        super.render(delta);
        time += 2.5*delta;
    }

    @Override
    protected void addViewElements() {
        BitmapFont def_font = skin.getFont("default"),
                title_font = skin.getFont("heading");

        Animation ani = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP,
                Gdx.files.internal("ui/loading.gif").readBytes());
        animation = new AnimatedActor(new AnimationDrawable(ani, 2.5f));
        table.add(animation);
        table.row();
        interactiveElements.put("animation", animation);

        Label title = new Label("NextGen",
                new Label.LabelStyle(title_font, Color.WHITE));
        table.add(title).padTop(10);
        table.row();

        Label testLabel = new Label(Model.getString("DATABASE_CONNECTING"),
                new Label.LabelStyle(def_font, Color.WHITE));
        table.add(testLabel).padTop(10);
        table.row();
        interactiveElements.put("testLabel", testLabel);
    }

}
