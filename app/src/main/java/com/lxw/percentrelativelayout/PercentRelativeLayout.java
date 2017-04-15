package com.lxw.percentrelativelayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * description... //TODO
 *
 * @author lsw
 * @version 1.0, 2017/4/15
 * @see //TODO
 * @since JDK 1.8
 */

public class PercentRelativeLayout extends RelativeLayout {
    public PercentRelativeLayout(Context context) {
        this(context, null);
    }

    public PercentRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public PercentRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public RelativeLayout.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    public static class LayoutParams extends RelativeLayout.LayoutParams {
        private float mWidth;
        private float mHeight;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            TypedArray array = c.obtainStyledAttributes(attrs, R.styleable.PercentRelativeLayout);
            mWidth = array.getFloat(R.styleable.PercentRelativeLayout_layout_width_percent, 0);
            mHeight = array.getFloat(R.styleable.PercentRelativeLayout_layout_height_percent, 0);
            array.recycle();
        }

        public float getWidth() {
            return mWidth;
        }

        public float getHeight() {
            return mHeight;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof LayoutParams) {
                LayoutParams lp = (LayoutParams) layoutParams;
                float widthPercent = lp.getWidth();
                float heightPercent = lp.getHeight();
                if (widthPercent != 0) {
                    lp.width = (int) (width * widthPercent);
                }
                if (heightPercent != 0) {
                    lp.height = (int) (height * heightPercent);
                }
            }
        }


        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
