package com.ayvengoza.pet.uiprototype;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;


/**
 * Created by ang on 10.01.18.
 */

public class SquareImageView extends android.support.v7.widget.AppCompatImageView {
    public SquareImageView(Context context){
        super(context);
    }
    public SquareImageView(Context context, AttributeSet attrs){
        super(context, attrs);
    }
    public SquareImageView(Context context, AttributeSet attrs, int defStyleAttrs){
        super(context, attrs, defStyleAttrs);
    }
//    public SquareImageView(Context context, AttributeSet attrs, int defStyleAttrs, int defStyleRes){
//        super(context, attrs, defStyleAttrs, defStyleRes);
//    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
    }
}
