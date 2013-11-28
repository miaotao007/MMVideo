
package com.mt.video.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter{
    private List<View> mViews;
    private List<String> mTitles;
    
    public ViewPagerAdapter(List<View> views) {
        this(views, null); 
    }
    
    public ViewPagerAdapter(List<View> views, List<String> titles) {
        this.mViews = views;
        this.mTitles = titles;
    }
    
    @Override
    public CharSequence getPageTitle(int position) {
        if(mTitles != null){
            return mTitles.get(position);
        }
        return super.getPageTitle(position);
    }

    @Override
    public int getCount() {
        if (mViews != null) {
            return mViews.size();
        }

        return 0;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView(mViews.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ((ViewPager) container).addView(mViews.get(position), 0);
        return mViews.get(position);
    }
    
    public View getViewAtPosition(int position) {
        if (mViews == null || mViews.size() <= 0 || position < 0 || position > getCount() -1) {
            return null;
        }
        return mViews.get(position);
    }
}
