
package com.mt.video.widgets;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.mt.video.R;

public class TabStrip extends LinearLayout {
    private final Paint mFullUnderlinePaint;
    private final int mFullUnderlineHeight;
    private int mIndexForSelection;
    private Drawable mSelectedUnderlineDrawable;
    private final int mSelectedUnderlineHeight;
    private float mSelectionOffset;
    private final int mSideSeparatorHeight;
    private final Paint mSideSeparatorPaint;

    public TabStrip(Context context) {
        this(context, null);
    }

    public TabStrip(Context context, AttributeSet attr) {
        super(context, attr);
        setWillNotDraw(false);

        Resources res = context.getResources();

        mFullUnderlineHeight = res.getDimensionPixelSize(R.dimen.fullunderline_height);
        mFullUnderlinePaint = new Paint();
        mFullUnderlinePaint.setColor(res.getColor(R.color.fullunderline_color));

        mSelectedUnderlineHeight = res.getDimensionPixelSize(R.dimen.selectedunderline_height);
        mSelectedUnderlineDrawable = new ColorDrawable(res.getColor(R.color.holo_blue_light));

        mSideSeparatorPaint = new Paint();
        mSideSeparatorPaint.setColor(res.getColor(R.color.sideseparator_color));
        mSideSeparatorPaint.setStrokeWidth(res
                .getDimensionPixelSize(R.dimen.sideseparator_stroke));
        mSideSeparatorHeight = res
                .getDimensionPixelSize(R.dimen.sideseparator_height);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    protected void onDraw(Canvas canvas) {
        int stripHeight = getHeight();
        int childCount = getChildCount();

        if (childCount > 0) {
            View localView2 = getChildAt(this.mIndexForSelection);
            int i2 = localView2.getLeft();
            int i3 = localView2.getRight();
            if ((this.mSelectionOffset > 0.0F)
                    && (this.mIndexForSelection < -1 + getChildCount())) {
                View localView3 = getChildAt(1 + this.mIndexForSelection);
                int i4 = localView3.getLeft();
                int i5 = localView3.getRight();
                i2 = (int) (this.mSelectionOffset * i4 + (1.0F - this.mSelectionOffset)
                        * i2);
                i3 = (int) (this.mSelectionOffset * i5 + (1.0F - this.mSelectionOffset)
                        * i3);
            }
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD_MR1) {
                mSelectedUnderlineDrawable.setBounds(i2, stripHeight
                        - this.mSelectedUnderlineHeight, i3, stripHeight);
                mSelectedUnderlineDrawable.draw(canvas);
            } else {
                canvas.save();
                canvas.clipRect(i2, stripHeight
                        - this.mSelectedUnderlineHeight, i3, stripHeight);
                mSelectedUnderlineDrawable.setBounds(0, 0, i3 - i2, this.mSelectedUnderlineHeight);
                mSelectedUnderlineDrawable.draw(canvas);
                canvas.restore();
            }
        }

        canvas.drawRect(0.0F, stripHeight - this.mFullUnderlineHeight, getWidth(), stripHeight,
                this.mFullUnderlinePaint);

        for (int k = 1; k < childCount; k++) {
            View localView1 = getChildAt(k);
            int m = localView1.getPaddingTop();
            int n = localView1.getPaddingBottom();
            int i1 = m + (localView1.getHeight() - m - n) / 2
                    - this.mSideSeparatorHeight / 2;
            canvas.drawLine(localView1.getLeft(), i1, localView1.getLeft(), i1
                    + this.mSideSeparatorHeight, this.mSideSeparatorPaint);
        }
    }

    void onPageScrolled(int position, float positionOffset,
            int positionOffsetPixels) {
        this.mIndexForSelection = position;
        this.mSelectionOffset = positionOffset;
        invalidate();
    }

    void onPageSelected(int position) {
        this.mIndexForSelection = position;
        this.mSelectionOffset = 0.0F;
        invalidate();
    }

    public void setSelectedIndicatorColor(int color) {
        this.mSelectedUnderlineDrawable = new ColorDrawable(color);
    }
}
