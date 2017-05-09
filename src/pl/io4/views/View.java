package pl.io4.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import java.util.HashMap;

/**
 * Created by Zax37 on 29.03.2017.
 */

public abstract class View implements Screen {
    Stage stage;
    Table table;
    HashMap<String, Actor> interactiveElements;
    TextureAtlas atlas;
    Skin skin;

    public View(){
        interactiveElements = new HashMap<String, Actor>();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        atlas = new TextureAtlas(Gdx.files.internal("assets/ui/ui.atlas"));
        skin = new Skin(atlas);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator( Gdx.files.internal("assets/fonts/Uni Sans Thin.otf") );
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS.concat("ąęćłóśźżĄĘĆŁÓŚŹŻ");
        parameter.size = 14;
        parameter.color = Color.BLACK;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 1;
        BitmapFont font = generator.generateFont(parameter);

        skin.add("default", font);
        skin.add("default", new Label.LabelStyle(font, Color.BLACK));

        skin.load(Gdx.files.internal("assets/styles/button.json"));
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));
        pixmap.setColor(Color.GRAY);
        pixmap.fill();
        skin.add("gray", new Texture(pixmap));

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        addViewElements();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {

    }

    protected abstract void addViewElements();

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        interactiveElements.clear();
        Gdx.input.setInputProcessor(null);
        stage.dispose();
    }

    public HashMap<String, Actor> getInteractiveElements(){
        return interactiveElements;
    }
}
