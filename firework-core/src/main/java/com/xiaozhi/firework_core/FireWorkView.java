package com.xiaozhi.firework_core;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;
import com.plattysoft.leonids.ParticleSystem;

import java.util.Random;

public class FireWorkView extends RelativeLayout {
    private Context context;
    private int display_Width;
    private int display_Height;
    private Bitmap fireBitmap;
    public ImageView fireImageView;
    private LayoutParams fireLayoutParams;
    private ParticleSystem particleSystem;
    private Random random = new Random();
    boolean fireWorkFlag = true;

    public FireWorkView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    public FireWorkView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public FireWorkView(Context context, int resId) {
        super(context);
        this.context = context;
        WindowManager windowManager = ((Activity) context).getWindowManager();
        Display defaultDisplay = windowManager.getDefaultDisplay();
        fireBitmap = BitmapFactory.decodeResource(getResources(), resId);
        display_Width = defaultDisplay.getWidth();
        display_Height = defaultDisplay.getHeight();
        initView(context);
    }

    public void initView(Context context) {
        fireImageView = new ImageView(context);
        fireImageView.setImageBitmap(fireBitmap);
        fireLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        fireLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        fireImageView.setLayoutParams(fireLayoutParams);
        this.addView(fireImageView);
    }

    public void initParticleSystem(){
        particleSystem = new ParticleSystem((Activity) context, 100, fireBitmap, 2000);
        particleSystem.setScaleRange(0.5f, 1f);
        particleSystem.setSpeedRange(0.1f, 0.5f);
        particleSystem.setRotationSpeedRange(90, 180);
        particleSystem.setFadeOut(200, new AccelerateInterpolator());
    }

    public void playAnim() {
        fireImageView.setVisibility(View.VISIBLE);
        fireLayoutParams.leftMargin = random.nextInt(display_Width / 3) + display_Width / 3;
        fireImageView.setLayoutParams(fireLayoutParams);
        ValueAnimator animator = ValueAnimator.ofInt(0, random.nextInt(display_Height / 3) + display_Height / 3);
        animator.addUpdateListener(new AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                int value = (Integer) animator.getAnimatedValue();
                fireLayoutParams.bottomMargin = value;
                fireImageView.setLayoutParams(fireLayoutParams);
            }
        });

        animator.addListener(new AnimatorListener() {
            @Override
            public void onAnimationStart(Animator arg0) {
            }

            @Override
            public void onAnimationRepeat(Animator arg0) {
            }

            @Override
            public void onAnimationEnd(Animator arg0) {
                if (!fireWorkFlag) return;
                initParticleSystem();
                particleSystem.oneShot(fireImageView, 50);

                new Thread() {
                    public void run() {
                        SystemClock.sleep(100);
                        ((Activity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                fireImageView.setVisibility(View.GONE);
                                playAnim();
                            }
                        });
                    }
                }.start();
            }

            @Override
            public void onAnimationCancel(Animator arg0) {
            }
        });
        animator.setDuration(1000);
        animator.start();
    }

    public void stopAnim() {
        if (particleSystem != null) {
            particleSystem.cancel();
            fireImageView.clearAnimation();
            fireImageView.setVisibility(View.GONE);
            fireWorkFlag = false;
        }
    }
}
