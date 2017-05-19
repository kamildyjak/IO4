package pl.io4.views.elements;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;

/**
 * Created by Zax37 on 19.05.2017.
 */
public class AnimationDrawable extends BaseDrawable
{
    public final Animation<TextureRegion> anim;
    private float stateTime = 0;
    private float speed = 1;

    public AnimationDrawable(Animation anim, float speed)
    {
        this.speed= speed;
        this.anim = anim;
        setMinWidth(((TextureRegion) anim.getKeyFrame(0)).getRegionWidth());
        setMinHeight(((TextureRegion) anim.getKeyFrame(0)).getRegionHeight());
    }

    public void act(float delta)
    {
        stateTime += speed*delta;
    }

    public void reset()
    {
        stateTime = 0;
    }

    @Override
    public void draw (Batch batch, float x, float y, float width, float height) {
        batch.draw(anim.getKeyFrame(stateTime), x, y, width, height);
    }
}