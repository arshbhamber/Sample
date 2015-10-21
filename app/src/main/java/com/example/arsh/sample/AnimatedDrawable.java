package com.example.arsh.sample;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/**
 * Created by arsh on 21/10/15.
 */
public class AnimatedDrawable {

    private DrawableAnimator animator;
    private boolean isStarted = false;

    public AnimatedDrawable(Context context,int res_selected,int res_unselected,ImageView imageView,int duration){

        animator = new DrawableAnimator(context,res_selected,res_unselected,imageView,duration);
    }

    public void start(){

        if(animator.getmDrawable().getLevel()!=5000 && !isStarted)
            animator.start();
        isStarted = true;
    }
    public void reverse(){

        if(animator.getmDrawable().getLevel()!=0 && isStarted)
        animator.reverse();
        isStarted = false;
    }

    public Drawable getDrawable(){

        return animator.getmDrawable();

    }



}
