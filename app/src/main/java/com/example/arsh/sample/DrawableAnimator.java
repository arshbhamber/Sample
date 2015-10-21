package com.example.arsh.sample;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

/**
 * Created by arsh on 21/10/15.
 */
public class DrawableAnimator extends ValueAnimator {

    Context mContext;
    Drawable mSelectedDrawable;
    Drawable mUnselectedDrawable;
    int mDuration;
    ImageView mImageView;

    public CustomDrawable getmDrawable() {
        return mDrawable;
    }

    CustomDrawable mDrawable;

    public DrawableAnimator(Context context,int res_selected,int res_unselected,ImageView imageView,int duration){

        mContext = context;
        mSelectedDrawable = context.getResources().getDrawable(res_selected);
        mUnselectedDrawable = context.getResources().getDrawable(res_unselected);
        mDuration = duration;
        mImageView = imageView;
        super.setDuration(duration);
        super.setFloatValues(0.0f, 1.0f);
        super.setInterpolator(new AccelerateDecelerateInterpolator());
        mDrawable = new CustomDrawable(mSelectedDrawable, mUnselectedDrawable, 2);
        super.addUpdateListener(new AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mDrawable = new CustomDrawable(mSelectedDrawable, mUnselectedDrawable, 2);
                mImageView.setImageDrawable(mDrawable);
                mDrawable.setLevel((int) (animation.getAnimatedFraction() * 5000.0f));
            }
        });

    }

}
