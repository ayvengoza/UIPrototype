package com.ayvengoza.pet.uiprototype;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.DrawableRes;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.view.View;
import android.widget.TextView;

/**
 * Created by ang on 10.01.18.
 */

public class CaptionedImageView extends FrameLayout implements View.OnLayoutChangeListener {
    private Drawable mDrawable;
    private TextView mTextView;
    private SquareImageView mImageView;
    private int mScrimColor;
    public CaptionedImageView(Context context){
        super(context);
        init(context);
    }
    public CaptionedImageView(Context context, AttributeSet attrs){
        super(context, attrs);
        init(context);
    }
    public CaptionedImageView(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        init(context);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CaptionedImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes){
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public TextView getTextView() {
        return mTextView;
    }

    public SquareImageView getImageView() {
        return mImageView;
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        if(v.getVisibility() != VISIBLE){
            return;
        }

        final int height = bottom - top;
        final int width = right - left;
        if(height == 0 || width == 0){
            return;
        }
        updateBlur();
    }

    public void setImageResource(@DrawableRes int drawableResourceId){
        mDrawable = getResources().getDrawable(drawableResourceId);
        mImageView.setImageResource(drawableResourceId);
        updateBlur();
    }

    private void updateBlur(){
        if(!(mDrawable instanceof BitmapDrawable)){
            return;
        }
        final int textViewHeight = mTextView.getHeight();
        if(textViewHeight == 0){
            return;
        }

        // ratio TextView to ImageView
        final float ratio = (float) textViewHeight/mImageView.getHeight();
        // get Bitmap
        final BitmapDrawable bitmapDrawable = (BitmapDrawable) mDrawable;
        final Bitmap originalBitmap = bitmapDrawable.getBitmap();

        // calculate height
        int height = (int) (ratio * originalBitmap.getHeight());

        // position y
        final int y = originalBitmap.getHeight() - height;

        final Bitmap portionToBlur = Bitmap.createBitmap(originalBitmap,
                0, y, originalBitmap.getWidth(), height);
        final Bitmap blurredBitmap = portionToBlur.copy(Bitmap.Config.ARGB_8888, true);

        // Use RenderScript
        RenderScript rs = RenderScript.create(getContext());
        ScriptIntrinsicBlur theIntrinsic = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        Allocation tmpIn = Allocation.createFromBitmap(rs, portionToBlur);
        Allocation tmpOut = Allocation.createFromBitmap(rs, blurredBitmap);
        theIntrinsic.setRadius(25f);
        theIntrinsic.setInput(tmpIn);
        theIntrinsic.forEach(tmpOut);
        tmpOut.copyTo(blurredBitmap);
        new Canvas(blurredBitmap).drawColor(mScrimColor);

        // Create new object Bitmap from source and blured area
        // and show it
        final Bitmap newBitmap = originalBitmap.copy(Bitmap.Config.ARGB_8888, true);
        final Canvas canvas = new Canvas(newBitmap);
        canvas.drawBitmap(blurredBitmap, 0, y, new Paint());
        mImageView.setImageBitmap(newBitmap);
    }

    private void init(Context context){
        inflate(context, R.layout.captioned_image_view, this);
        mTextView = (TextView) findViewById(R.id.text);
        mImageView = (SquareImageView) findViewById(R.id.image);
        mScrimColor = getResources().getColor(R.color.grid_item_scrim);
        mTextView.addOnLayoutChangeListener(this);
    }
}
