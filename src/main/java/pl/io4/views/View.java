package pl.io4.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import pl.io4.model.Model;

import java.util.HashMap;

/**
 * Created by Zax37 on 29.03.2017.
 */

public abstract class View implements Screen {
    private Stage stage;
    private Table table;
    private HashMap<String, Actor> interactiveElements;
    private static final TextureAtlas TEXTURE_ATLAS
            = new TextureAtlas(Gdx.files.internal("ui/ui.atlas"));
    private static final Skin SKIN = new Skin(TEXTURE_ATLAS);

    private static final int DEFAULT_FONT_SIZE_BIG = 28;
    private static final int DEFAULT_FONT_SIZE_SMALL = 15;

    static final int PAD_SMALL = 10;
    static final int PAD_BIG = 20;

    private static void padDrawable(Drawable drawable, int padding) {
        drawable.setLeftWidth(padding);
        drawable.setTopHeight(padding);
        drawable.setBottomHeight(padding);
        drawable.setRightWidth(padding);
    }

    static { // Przygotuj czcionki i style dla wszystkich widoków
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Uni Sans Heavy.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS.concat("ąęćłóśźżĄĘĆŁÓŚŹŻ");
        parameter.size = DEFAULT_FONT_SIZE_BIG;
        parameter.color = Color.WHITE;
        parameter.spaceX += 2;
        BitmapFont font = generator.generateFont(parameter);
        SKIN.add("heading", font);
        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Uni Sans Thin.otf"));
        parameter.spaceX -= 2;
        parameter.borderColor = Color.WHITE;
        parameter.borderWidth = 1;
        parameter.size = DEFAULT_FONT_SIZE_SMALL;
        font = generator.generateFont(parameter);
        SKIN.add("default", font);
        parameter.color = Color.BLACK;
        parameter.borderColor = Color.BLACK;
        font = generator.generateFont(parameter);
        SKIN.add("default", new Label.LabelStyle(font, Color.BLACK));
        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Arial.ttf"));
        parameter.borderWidth = 0;
        font = generator.generateFont(parameter);
        SKIN.add("input", font);
        parameter.color = Color.WHITE;
        font = generator.generateFont(parameter);
        SKIN.add("input", new Label.LabelStyle(font, Color.WHITE));
        parameter.color = Color.GRAY;
        font = generator.generateFont(parameter);
        SKIN.add("input", new Label.LabelStyle(font, Color.GRAY));
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        SKIN.add("white", new Texture(pixmap));
        SKIN.add("input", new Texture(pixmap));
        pixmap.setColor(Color.GRAY);
        pixmap.fill();
        SKIN.add("gray", new Texture(pixmap));
        pixmap.setColor(Color.BLACK);
        pixmap.fill();
        SKIN.add("black", new Texture(pixmap));
        FileHandle[] files = Gdx.files.internal("styles/").list();
        SKIN.load(Gdx.files.internal("styles/colors.json"));
        for (FileHandle file: files) {
            SKIN.load(file);
        }
        padDrawable(SKIN.getDrawable("input"), PAD_SMALL);
        padDrawable(SKIN.getDrawable("button.up"), PAD_SMALL);
        padDrawable(SKIN.getDrawable("button.down"), PAD_SMALL);
    }

    View() {
        interactiveElements = new HashMap<String, Actor>();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);


        table = new Table(SKIN);
        table.setFillParent(true);
        stage.addActor(table);

        addViewElements();
    }

    public final void addErrorMessage(String message) {
        Dialog popup = new Dialog(Model.getString("ERROR"), getSkin());
        popup.text(message).pad(PAD_BIG, PAD_BIG, PAD_BIG, PAD_BIG);
        popup.button("OK");
        popup.background("gray");
        popup.show(stage);
    }

    final Table getTable() {
        return table;
    }

    final Skin getSkin() {
        return SKIN;
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
