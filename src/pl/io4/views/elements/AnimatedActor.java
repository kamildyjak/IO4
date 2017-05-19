package pl.io4.views.elements;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by Zax37 on 19.05.2017.
 */
public class AnimatedActor extends Image
{
    private final AnimationDrawable drawable;

    public AnimatedActor(AnimationDrawable drawable)
    {
        super(drawable);
        this.drawable = drawable;
    }

    @Override
    public void act(float delta)
    {
        drawable.act(delta);
        super.act(delta);
    }
}