
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
            case MotionEvent.ACTION_DOWN:// ����
                mStartX = event.getX();
                getParent().requestDisallowInterceptTouchEvent(true);
                break;

            // �������ڴ˶����viewpager�ĵ�һҳ�����һҳ����������
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
                // ���viewpager�Ѿ������һҳ����ʱ�������һ�����ָ�������󻬣�
                else if (mStartX > event.getX()) {
                    if (currentItem == count - 1)
                    {
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }
                }
                // ���viewpager�Ѿ��ǵ�һҳ����ʱ�������󻬣���ָ�������һ���
                else if (mStartX < event.getX()) {
                    if (currentItem == 0)
                    {
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:// ̧��
            case MotionEvent.ACTION_CANCEL:
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        return super.onInterceptTouchEvent(event);
    }
}
