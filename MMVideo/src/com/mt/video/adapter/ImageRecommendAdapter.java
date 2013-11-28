
package com.mt.video.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class ImageRecommendAdapter extends PagerAdapter {

    private List<View> mViews;
    private String[] mStrs;
    
    private DisplayImageOptions mOptions;

    public ImageRecommendAdapter(Context context) {
        this.mViews = new ArrayList<View>();
        mOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc()
                .cacheInMemory()
                .resetViewBeforeLoading()
                .build();

        for (int i = 0; i < 5; i++) {
            ImageView view = new ImageView(context);
            view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT));
            view.setScaleType(ScaleType.FIT_XY);
            mViews.add(view);
        }
    }

    public void addImageURL(String[] strs) {
        mStrs = strs;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mStrs != null) {
            return mStrs.length;
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

        ImageView imageView = (ImageView) mViews.get(position);
        ((ViewPager) container).addView(imageView, 0);

        if (mStrs != null && imageView.getContentDescription() == null) {
            ImageLoader.getInstance().displayImage(mStrs[position], imageView, mOptions);
            // imageView.setContentDescription(topic.cover);
            // imageView.setImageBitmap(bitmap);
        }

        return imageView;
    }

    public void onClickItem(Context context, int position) {
        if (mStrs == null) {
            return;
        }

        // SoftReference<Bitmap> bitmap = mBitmapRefs.get(position);
        //
        // Intent intent = new Intent();
        // Bundle bundle = new Bundle();
        // bundle.putString("topicId", topic.id);
        // bundle.putString("topicName", topic.name);
        // bundle.putString("topicBrief", topic.brief);
        // bundle.putString("topicCover", topic.cover);
        //
        // intent.setClass(context, TopicDetailsActivity.class);
        // intent.putExtras(bundle);
        // context.startActivity(intent);
    }
}
