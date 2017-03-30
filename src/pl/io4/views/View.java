package pl.io4.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import java.util.HashMap;

/**
 * Created by Zax37 on 29.03.2017.
 */

public abstract class View implements Screen {
    private Stage stage;
    protected Table table;
    protected HashMap<String, Actor> interactiveElements;

    public View(){
        interactiveElements = new HashMap<String, Actor>();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

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
