package com.example.charles.royaltips.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.charles.royaltips.R;
import com.pnikosis.materialishprogress.ProgressWheel;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;


/**
 * Created by nyk0806 on 2017. 9. 30..
 * Web Image View
 */

public class WebImageView extends FrameLayout {

    String url = "";
    Context context;
    ImageView image;
    ProgressWheel wheel;

    float perX = 0f;
    float perY = 0f;
    float aspectRatio = -1.0f;

    final boolean useAutoAspect = false;
    final int WHEEL_RADIOUS_DP = 20;

    boolean loading = false;
    boolean isProduct = false;
    boolean animatable = true;
    boolean onParallax = false;
    boolean aspectFitSize = false;
    boolean useCenterCrop = true;
    boolean useTopAlignment = false;

    public WebImageView(@NonNull Context context) {
        super(context);
        this.context = context;
        this.animatable = true;
        config();
    }

    public WebImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        if (attrs != null) {
            this.animatable = attrs.getAttributeBooleanValue(null, "animatable", true);
            this.aspectFitSize = attrs.getAttributeBooleanValue(null, "aspectFitSize", false);
            this.aspectRatio = attrs.getAttributeFloatValue(null, "aspectRatio", -1.0f);
        }
        config();
    }

    public WebImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (attrs != null) {
            this.animatable = attrs.getAttributeBooleanValue(null, "animatable", true);
            this.aspectFitSize = attrs.getAttributeBooleanValue(null, "aspectFitSize", false);
            this.aspectRatio = attrs.getAttributeFloatValue(null, "aspectRatio", -1.0f);
        }
        config();
    }

    public void setUseCenterCrop(boolean val) {
        useCenterCrop = val;
    }

    public void setProduct(boolean val) {
        isProduct = val;
    }

    public void setAspectRatio(float val) {
        if (val > 0) {
            aspectRatio = val;
            invalidate();
        }
    }

    public void setPerX(float perX) {
        this.perX = perX;
        applyParallax();
    }

    public void setPerY(float perY) {
        this.perY = perY;
        applyParallax();
    }

    void clearParallax() {
        if (onParallax) {
            perX = perY = 0f;
        }
    }

    void applyParallax() {
        if (onParallax && image.getDrawable() != null) {
            Rect rect = image.getDrawable().getBounds();

            float w = (float)getWidth() / rect.width();
            float h = (float)getHeight() / rect.height();

            if (h > w) {
                image.getLayoutParams().width = (int)((float)getHeight() / (float)rect.height() * (float)rect.width());
            } else {
                image.getLayoutParams().height = (int)((float)getWidth() / (float)rect.width() * (float)rect.height());
            }

            image.setX((float)getWidth() * 0.5f - (float)image.getWidth() * 0.5f + perX * (float)image.getWidth());
            image.setY((float)getHeight() * 0.5f - (float)image.getHeight() * 0.5f + perX * (float)image.getHeight());
            image.requestLayout();
        }
    }

    public boolean isLoading() {
        return loading;
    }

    private void config() {
        image = new ImageView(context);
        image.setAdjustViewBounds(true);

        wheel = new ProgressWheel(context);
        wheel.setBarColor(Color.WHITE);
        wheel.setCircleRadius((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, WHEEL_RADIOUS_DP, getResources().getDisplayMetrics()));

        addView(wheel);
        addView(image, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        requestLayout();
    }

    private void onLoadingDone() {
        applyParallax();
    }

    private void startSpin() {
        loading = true;
        if (!wheel.isSpinning()) {
            wheel.spin();
        }
    }

    private void stopSpin() {
        loading = false;
        if (wheel.isSpinning()) {
            wheel.stopSpinning();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        applyParallax();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (aspectRatio > 0) {
            int widthPixel = MeasureSpec.getSize(widthMeasureSpec);
            int heightPixel = (int)(widthPixel / aspectRatio);
            super.onMeasure(MeasureSpec.makeMeasureSpec(widthPixel, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(heightPixel, MeasureSpec.EXACTLY));
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    RequestOptions baseRequestOption() {
        return RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.DATA).format(DecodeFormat.PREFER_ARGB_8888);
    }

    RequestOptions imageRequestOption() {
        return useCenterCrop ? RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.DATA).format(DecodeFormat.PREFER_ARGB_8888).centerCrop() :
                RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.DATA).format(DecodeFormat.PREFER_ARGB_8888).fitCenter();
    }

    public void loadImage(String url) {
        if (url == null || url.isEmpty()) return;
        this.url = url;
        this.loadImage(url, null);
    }

    public void loadImage(String url, RequestListener<Drawable> listener) {
        this.url = url;
        clear();

        RequestOptions requestOptions = isProduct ? baseRequestOption() : imageRequestOption();
        RequestBuilder<Drawable> builder = Glide.with(context)
                .load(url)
                .apply(requestOptions)
                .transition(withCrossFade());

        if (listener != null) builder.listener(listener);
        builder.into(new ImageViewTarget<Drawable>(image) {
            @Override
            protected void setResource(@Nullable Drawable resource) {
                if (resource != null) {
                    setImageDrawale(resource);
                    onLoadingDone();
                }
            }

            @Override
            public void onLoadStarted(@Nullable Drawable placeholder) {
                super.onLoadStarted(placeholder);
                startSpin();
            }

            @Override
            public void onResourceReady(Drawable resource, @Nullable Transition<? super Drawable> transition) {
                super.onResourceReady(resource, transition);
                if (resource instanceof GifDrawable) {
                    ((GifDrawable)resource).setLoopCount(animatable ? GifDrawable.LOOP_FOREVER : GifDrawable.LOOP_INTRINSIC);
                }
                stopSpin();
            }

            @Override
            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                super.onLoadFailed(errorDrawable);
                stopSpin();
            }

            @Override
            public void onStop() {
                super.onStop();
                stopSpin();
            }
        });
    }

    public void loadCircularImage(String url) {
        if (url == null || url.isEmpty()) return;

        this.url = url;
        clear();

        Glide.with(context)
                .load(url)
                .apply(imageRequestOption())
                .transition(withCrossFade())
                .into(new ImageViewTarget<Drawable>(image) {
                    @Override
                    protected void setResource(@Nullable Drawable drawable) {
                        if (drawable != null) {
                            Bitmap bitmap = drawableToBitmap(drawable);
                            // set circular image stroke
                            Paint paint = new Paint();
                            paint.setColor(ContextCompat.getColor(getContext(), R.color.imageBorder));
                            paint.setStrokeWidth(2);
                            paint.setStyle(Paint.Style.STROKE);
                            paint.setAntiAlias(true);

                            Canvas canvas = new Canvas(bitmap);
                            float radius = Math.max(canvas.getWidth() / 2.0f, canvas.getHeight() / 2.0f);
                            canvas.drawCircle(canvas.getWidth() / 2.0f, canvas.getHeight() / 2.0f, canvas.getWidth() / 2.0f, paint);

                            RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), bitmap);
                            roundedBitmapDrawable.setCircular(true);
                            setImageDrawale(roundedBitmapDrawable);
                        } else {
                            setImageDrawale(drawable);
                        }
                        applyParallax();
                    }

                    @Override
                    public void onLoadStarted(@Nullable Drawable placeholder) {
                        super.onLoadStarted(placeholder);
                        loading = true;
                        if (!wheel.isSpinning())
                            wheel.spin();
                    }

                    @Override
                    public void onResourceReady(Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        super.onResourceReady(resource, transition);
                        loading = false;
                        if (wheel.isSpinning())
                            wheel.stopSpinning();
                        if (resource instanceof GifDrawable) {
                            ((GifDrawable) resource).setLoopCount(animatable ? GifDrawable.LOOP_FOREVER : GifDrawable.LOOP_INTRINSIC);
                        }
                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                    }
                });
    }

    void clear() {
        setImageDrawale(null);
        clearParallax();
    }

    void setImageDrawale(Drawable d) {
        if (useTopAlignment) {
            image.setScaleType(ImageView.ScaleType.FIT_START);
        } else {
            image.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }
        image.setImageDrawable(d);
    }

    public Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}
