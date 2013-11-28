
package com.mt.video.widgets;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.res.ColorStateList;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.mt.video.R;

import java.util.ArrayList;

public class TabContainer extends HorizontalScrollView implements
        ViewPager.OnPageChangeListener {
    private int mLastScrollTo;
    private int mScrollState;
    private TabStrip mTabStrip;
    private final int mTitleOffset;
    private ViewPager mViewPager;
    private ViewPager.OnPageChangeListener mOnPageChangeListener;
    private ArrayList<Integer> mIcons;
    private BroadcastReceiver mThemeChangeReceiver;
    
    public TabContainer(Context context) {
        this(context, null);
    }

    public TabContainer(Context context, AttributeSet paramAttributeSet) {
        super(context, paramAttributeSet);
        setHorizontalScrollBarEnabled(false);
        
        mTitleOffset = context.getResources().getDimensionPixelSize(
                R.dimen.tabtitleoffset);
    }

    private void scrollToChild(int childIndex, int extraOffset) {
        if (this.mTabStrip.getChildCount() == 0)
            return;
        if ((childIndex <= 0) && (extraOffset <= 0))
            return;
        this.mLastScrollTo = extraOffset
                + this.mTabStrip.getChildAt(childIndex).getLeft()
                - this.mTitleOffset;
        scrollTo(this.mLastScrollTo, 0);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
//        this.mTabStrip = ((TabStrip) findViewById(R.id.pager_tab_strip));
        
        if (mThemeChangeReceiver != null) {
            return;
        }
        applyTheme();
    }

    private void applyTheme() {
        ColorStateList colorStateList = getResources().getColorStateList(R.color.blue_pressed);
        mTabStrip.setSelectedIndicatorColor(colorStateList.getDefaultColor());
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mThemeChangeReceiver = null;
    }
    
    public void setOnPageChangeListener(OnPageChangeListener listener) {
        mOnPageChangeListener = listener;
    }

    public void onPageScrollStateChanged(int state) {
        if (mOnPageChangeListener != null) {
            mOnPageChangeListener.onPageScrollStateChanged(state);
        }

        this.mScrollState = state;
    }

    public void onPageScrolled(int position, float positionOffset,
            int positionOffsetPixels) {
        if (mOnPageChangeListener != null) {
            mOnPageChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }

        if (this.mTabStrip.getChildCount() == 0)
            return;
        this.mTabStrip.onPageScrolled(position, positionOffset,
                positionOffsetPixels);
        scrollToChild(position, (int) (positionOffset * this.mTabStrip
                .getChildAt(position).getWidth()));
    }

    public void onPageSelected(int position) {
        if (mOnPageChangeListener != null) {
            mOnPageChangeListener.onPageSelected(position);
        }

        if (this.mScrollState == 0) {
            this.mTabStrip.onPageSelected(position);
            scrollToChild(position, 0);
        }
    }

    public void setSelectedIndicatorColor(int color) {
        this.mTabStrip.setSelectedIndicatorColor(color);
    }

    public void setTitleIcons(ArrayList<Integer> icons){
        this.mIcons = icons;
    }
    
    public void setViewPager(ViewPager viewPager) {
        this.mViewPager = viewPager;
        PagerAdapter adapter = this.mViewPager.getAdapter();
        LayoutInflater localLayoutInflater = LayoutInflater.from(getContext());
        for (int i = 0; i < adapter.getCount(); i++) {
            TextView v = (TextView) localLayoutInflater.inflate(
                    R.layout.tab_text, this.mTabStrip, false);
            v.setText(adapter.getPageTitle(i));
            if(mIcons != null && mIcons.get(i) > 0){
                v.setCompoundDrawablesWithIntrinsicBounds(mIcons.get(i), 0, 0, 0);
            }
            v.setOnClickListener(new TextClicker(i));
            this.mTabStrip.addView(v);
        }
        this.mTabStrip.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @SuppressWarnings("deprecation")
                    public void onGlobalLayout() {
                        TabContainer.this.scrollToChild(
                                TabContainer.this.mViewPager
                                        .getCurrentItem(), 0);
                        TabContainer.this.mTabStrip.getViewTreeObserver()
                                .removeGlobalOnLayoutListener(this);
                    }
                });
    }

    private class TextClicker implements View.OnClickListener {
        private int indexToSelect;

        public TextClicker(int index) {
            this.indexToSelect = index;
        }

        @Override
        public void onClick(View v) {
            TabContainer.this.mViewPager.setCurrentItem(indexToSelect);
        }

    }
}
