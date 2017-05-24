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
import java.util.HashMap;

/**
 * Created by Zax37 on 29.03.2017.
 */

public abstract class View implements Screen {
    private Stage stage;
    private Table table;
    private HashMap<String, Actor> interactiveElements;
    private TextureAtlas atlas;
    private Skin skin;

    private static final int DEFAULT_FONT_SIZE_BIG = 27;
    private static final int DEFAULT_FONT_SIZE_SMALL = 15;

    View() {
        interactiveElements = new HashMap<String, Actor>();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        atlas = new TextureAtlas(Gdx.files.internal("ui/ui.atlas"));
        skin = new Skin(atlas);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Uni Sans Thin.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS.concat("ąęćłóśźżĄĘĆŁÓŚŹŻ");
        parameter.size = DEFAULT_FONT_SIZE_BIG;
        parameter.color = Color.WHITE;
        parameter.borderColor = Color.WHITE;
        parameter.borderWidth = 1;
        BitmapFont font = generator.generateFont(parameter);
        skin.add("heading", font);
        parameter.size = DEFAULT_FONT_SIZE_SMALL;
        font = generator.generateFont(parameter);
        skin.add("default", font);
        parameter.color = Color.BLACK;
        parameter.borderColor = Color.BLACK;
        font = generator.generateFont(parameter);
        skin.add("default", new Label.LabelStyle(font, Color.BLACK));
        skin.load(Gdx.files.internal("styles/button.json"));
        skin.load(Gdx.files.internal("styles/window.json"));
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));
        pixmap.setColor(Color.GRAY);
        pixmap.fill();
        skin.add("gray", new Texture(pixmap));

        table = new Table(skin);
        table.setFillParent(true);
        stage.addActor(table);

        addViewElements();
    }

    final Table getTable() {
        return table;
    }

    final Skin getSkin() {
        return skin;
    }

    final Stage getStage() {
        return stage;
    }

    @Override
    public final void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public final void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {

    }

    protected abstract void addViewElements();

    protected final <T extends Actor>
    void addElement(String name, T actor) {
        interactiveElements.put(name, actor);
    }

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
    public final void dispose() {
        interactiveElements.clear();
        Gdx.input.setInputProcessor(null);
        stage.dispose();
    }

    public final HashMap<String, Actor> getInteractiveElements() {
        return interactiveElements;
    }
}
