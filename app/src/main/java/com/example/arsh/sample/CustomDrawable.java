package com.example.arsh.sample;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.Gravity;

/**
 * Created by arsh on 21/10/15.
 */
public class CustomDrawable extends Drawable implements Drawable.Callback {

    Drawable mSelectedDrawable;
    Drawable mUnselectedDrawable;
    int mAnimId;

    public CustomDrawable(Drawable selected, Drawable unSelected, int animId){

        mSelectedDrawable = selected;
        mUnselectedDrawable = unSelected;
        mAnimId = animId;

    }

    @Override
    public void invalidateDrawable(Drawable who) {
        final Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    @Override
    public void scheduleDrawable(Drawable who, Runnable what, long when) {
        final Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, what, when);
        }
    }

    @Override
    public void unscheduleDrawable(Drawable who, Runnable what) {
        final Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, what);
        }
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        mUnselectedDrawable.setBounds(bounds);
        mSelectedDrawable.setBounds(bounds);
    }


    @Override
    public void draw(Canvas canvas) {

        switch (mAnimId){

            case 1 : disperseIcons(canvas);
                break;

            case 2 : bottomToTop(canvas);
                break;




        }




    }

    private void bottomToTop(Canvas canvas) {

        int prop = (int)(((float)getLevel()/5000.0f)*(100.0f));

        int level = getLevel();
        final Rect r = new Rect();
        final Rect bounds = getBounds();

        { // Draw the unselected portion
//            float value = (level / 5000f) - 1f;
//            int w = bounds.width();
//
//            int h = bounds.height();
//                h = (int) (h * Math.abs(value));
//
//            int gravity = value < 0 ? Gravity.BOTTOM : Gravity.TOP;
//            Gravity.apply(gravity, w, h, bounds, r);
//
//            if (w > 0 && h > 0) {
//                canvas.save();
//                canvas.clipRect(r);
                mUnselectedDrawable.draw(canvas);
//                canvas.restore();
//            }
        }

        { // Draw the selected portion
            float value = (level / 5000f) - 1f;
            int w = bounds.width();

            int h = bounds.height();
                h -= (int) (h * Math.abs(value));

            int gravity = value < 0 ? Gravity.BOTTOM : Gravity.TOP;
            Gravity.apply(gravity, w, h, bounds, r);

            if (w > 0 && h > 0) {
                canvas.save();
                canvas.clipRect(r);
                mSelectedDrawable.draw(canvas);
                canvas.restore();
            }
        }

    }

    @Override
    protected boolean onLevelChange(int level) {
        invalidateSelf();
        return true;
    }

    private void disperseIcons(Canvas canvas){


        int prop = (int)(((float)getLevel()/5000.0f)*(255.0f));

        mUnselectedDrawable.setAlpha(255-prop);
        mSelectedDrawable.setAlpha(prop);
        mSelectedDrawable.draw(canvas);
        mUnselectedDrawable.draw(canvas);



    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return 0;
    }
}


