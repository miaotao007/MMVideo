
package com.mt.video.widgets;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.mt.video.R;

public class InnerViewPager extends RelativeLayout {
    private ViewPager mChildViewpager;
    private float mStartX;

    public InnerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mChildViewpager = (ViewPager) findViewById(R.id.pager);
    }

    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (mChildViewpager == null || mChildViewpager.getAdapter() == null) {
            return super.onInterceptTouchEvent(event);
        }
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:// 按下
                mStartX = event.getX();
                getParent().requestDisallowInterceptTouchEvent(true);
                break;

            // 滑动，在此对里层viewpager的第一页和最后一页滑动做处理
            case MotionEvent.ACTION_MOVE:
                int currentItem = mChildViewpager.getCurrentItem();
                int count = mChildViewpager.getAdapter().getCount();

                if (mStartX == event.getX()) {
                    if (0 == currentItem
                            || currentItem == count - 1)
                    {
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }
                }
                // 里层viewpager已经是最后一页，此时继续向右滑（手指从右往左滑）
                else if (mStartX > event.getX()) {
                    if (currentItem == count - 1)
                    {
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }
                }
                // 里层viewpager已经是第一页，此时继续向左滑（手指从左往右滑）
                else if (mStartX < event.getX()) {
                    if (currentItem == 0)
                    {
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:// 抬起
            case MotionEvent.ACTION_CANCEL:
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        return super.onInterceptTouchEvent(event);
    }
}
