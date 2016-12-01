package com.example.mylibrary.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.example.mylibrary.LogUtils;
import com.example.mylibrary.R;

/**
 * Created by JessYuan on 30/11/2016.
 */

public class CircleImageView extends ImageView {

    private static final String TAG = "圆形ImageView";

    private Context mContext;

    private Paint mBitmapPaint = new Paint();
    private Paint mBorderPaint = new Paint();
    private Paint mFillPaint = new Paint();
    private Matrix mMatrix = new Matrix();
    private Shader mShader = new Shader();

    private Bitmap mBitmap;
    private int mBitmapWidth;
    private int mBitmapHeight;
    private float mDrawableRadius;

    private float mBorderRadius;
    private int mBorderWidth;
    private int mBorderColor;

    private int mFillColor = Color.TRANSPARENT;

    private RectF mBorderRect = new RectF();
    private RectF mDrawableRect = new RectF();

    public CircleImageView(Context context) {
        super(context);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a =  context.obtainStyledAttributes(attrs, R.styleable.CircleImageView, defStyleAttr, 0);

        mBorderWidth = a.getDimensionPixelSize(R.styleable.CircleImageView_civ_border_width, 0);
        mBorderColor = a.getColor(R.styleable.CircleImageView_civ_border_color, Color.WHITE);

        a.recycle();

        setup();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY || heightMode == MeasureSpec.EXACTLY) {
            int size = Math.min(widthSize, heightSize);
            setMeasuredDimension(size, size);
            return;
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        Log.i(TAG, "setPadding: ");
        super.setPadding(left, top, right, bottom);
        setup();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.i(TAG, "onSizeChanged: ");
        super.onSizeChanged(w, h, oldw, oldh);
        setup();
    }

    @Override
    public void setPaddingRelative(int start, int top, int end, int bottom) {
        Log.i(TAG, "setPaddingRelative: ");
        super.setPaddingRelative(start, top, end, bottom);
        setup();
    }

    @Override
    public void setImageURI(Uri uri) {
        Log.i(TAG, "setImageURI: ");
        super.setImageURI(uri);
        setupBitmap();
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        Log.i(TAG, "setImageBitmap: ");
        super.setImageBitmap(bm);
        setupBitmap();
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        Log.i(TAG, "setImageDrawable: ");
        super.setImageDrawable(drawable);
        setupBitmap();
    }

    @Override
    public void setImageResource(int resId) {
        Log.i(TAG, "setImageResource: ");
        super.setImageResource(resId);
        setupBitmap();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mBitmap == null) {
            return;
        }

        canvas.drawCircle(mDrawableRect.centerX(), mDrawableRect.centerY(), mDrawableRadius, mFillPaint);
        canvas.drawCircle(mDrawableRect.centerX(), mDrawableRect.centerY(), mDrawableRadius, mBitmapPaint);
        canvas.drawCircle(mBorderRect.centerX(), mBorderRect.centerY(), mBorderRadius, mBorderPaint);
    }

    private void setupBitmap() {
        mBitmap = getBitmapFromDrawable(getDrawable());
        setup();
    }

    private Bitmap getBitmapFromDrawable(Drawable drawable) {
        if (drawable == null) {
            return null;
        }

        Bitmap bitmap;
        if (drawable instanceof BitmapDrawable) {
            bitmap = ((BitmapDrawable)drawable).getBitmap();
            return bitmap;
        }

        if (drawable instanceof ColorDrawable) {
            bitmap = Bitmap.createBitmap(2,2, Bitmap.Config.ARGB_8888);
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    private void setup() {
        super.setScaleType(ScaleType.CENTER_CROP);

        if (getWidth() == 0 || getHeight() == 0) {
            return ;
        }

        if (mBitmap == null) {
            return;
        }

        mShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        mBitmapPaint.setAntiAlias(true);
        mBitmapPaint.setShader(mShader);

        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setColor(mBorderColor);
        mBorderPaint.setStrokeWidth(mBorderWidth);

        mFillPaint.setStyle(Paint.Style.FILL);
        mFillPaint.setColor(mFillColor);
        mFillPaint.setAntiAlias(true);

        mBitmapHeight = mBitmap.getHeight();
        mBitmapWidth = mBitmap.getWidth();

        mBorderRect.set(calculateBorder());
        mBorderRadius = Math.min((mBorderRect.width() - mBorderWidth)/2.0f, (mBorderRect.height() - mBorderWidth)/2.0f);

        mDrawableRect.set(mBorderRect);
        mDrawableRadius = Math.min(mDrawableRect.width()/2.f, mDrawableRect.height()/2.f);

        setShaderMatrix();
        invalidate();
    }

    private RectF calculateBorder() {
        int availableWidth = getWidth() - getPaddingLeft() - getPaddingRight();

        float left = getPaddingLeft();
        float top = getPaddingTop();

        return new RectF(left, top, left + availableWidth, top + availableWidth);
    }

    private void setShaderMatrix() {
        float scale;
        mMatrix.set(null);

        if (mDrawableRect.height() / mBitmapHeight > mDrawableRect.width() / mBitmapWidth) {
            scale = mDrawableRect.height() / mBitmapHeight;
        } else {
            scale = mDrawableRect.width() / mBitmapWidth;
        }

        mMatrix.setScale(scale, scale);
        mShader.setLocalMatrix(mMatrix);
        mBitmapPaint.setShader(mShader);
    }
}
