package pl.io4.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.HashMap;

/**
 * Created by Zax37 on 14.03.2017.
 */

public class TestView extends View {

    private TextButton button;

    @Override
    protected void addViewElements() {
        BitmapFont font = new BitmapFont();

        Label testLabel1 = new Label("Hello world", new Label.LabelStyle(font, Color.WHITE));
        table.add(testLabel1);
        table.row();

        Label testLabel2 = new Label("2nd thread is currently connecting to database.", new Label.LabelStyle(font, Color.WHITE));
        table.add(testLabel2);
        table.row();
        interactiveElements.put("testLabel", testLabel2);

        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(atlas.findRegion("button.up"));
        buttonStyle.down = new TextureRegionDrawable(atlas.findRegion("button.down"));
        buttonStyle.font = font;
        button = new TextButton("Click me", buttonStyle);
        interactiveElements.put("testButton", button);
        button.pad(12);
        table.add(button).padTop(10);
    }

}
