package com.mt.video.page;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import com.mt.video.R;
import com.mt.video.adapter.ImageRecommendAdapter;
import com.mt.video.adapter.MainPageClassifyAdapter;
import com.mt.video.adapter.MovieAdapter;
import com.mt.video.widgets.ClassifyScrollView;
import com.mt.video.widgets.MovieScrollView;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.HashMap;
import java.util.Map;

public class MainPage extends ViewGroup {

    private boolean mEnabled = true;
    private boolean mSelected = true;
    private boolean mPaused = false;

    private ViewPager mViewPager;
    private ImageRecommendAdapter mImageRecommendAdapter;
    private View mPageView;
    private String[] mClassifyText = {
            "经典大片", "浪漫言情", "科幻巨制", "宅男福利", "美女图库", "热辣主播", "会员专区",
    };

    private Handler mHandler = new MyHandler();

    private class MyHandler extends Handler {
        static final int LOOP_CIRCLE = 0x0;
        static final int LOOP_DURATION = 5000;

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int count = mImageRecommendAdapter.getCount();
            if (!mPaused && mSelected && mEnabled && count > 0) {
                mViewPager.setCurrentItem((mViewPager.getCurrentItem() + 1) % count);
                loopImageRecommend();
            }
        }
    }

    public MainPage(Context context) {
        super(context);
        initView(context);
    }

    private String[] mImageURL = {
            "http://a.hiphotos.baidu.com/image/w%3D2048/sign=3abb0b1d908fa0ec7fc7630d12af58ee/d52a2834349b033b03de35f414ce36d3d539bd4e.jpg",
            "http://a.hiphotos.baidu.com/image/w%3D2048/sign=07d91d2e87d6277fe91235381c001d30/4e4a20a4462309f7108a2487720e0cf3d6cad6ba.jpg",
            "http://c.hiphotos.baidu.com/image/w%3D2048/sign=2ce328218694a4c20a23e02b3acc1ad5/aa64034f78f0f73655a8852f0b55b319ebc4134e.jpg",
            "http://f.hiphotos.baidu.com/image/w%3D2048/sign=5c4e402f5d6034a829e2bf81ff2b4854/71cf3bc79f3df8dc146b46bfcc11728b4710284e.jpg",
            "http://e.hiphotos.baidu.com/image/w%3D2048/sign=7bf858dc7c1ed21b79c929e59956dcc4/79f0f736afc37931f6394f07eac4b74543a9114f.jpg"
    };

    private void initView(Context context) {
        
        mPageView = LayoutInflater.from(context).inflate(R.layout.page_mainpage, null);
        
        MainPageClassifyAdapter adapter = new MainPageClassifyAdapter(context);
        adapter.addData(mClassifyText);

        View classifyView = mPageView.findViewById(R.id.classify_menu);
        ClassifyScrollView classifyLayout = (ClassifyScrollView) classifyView
                .findViewById(R.id.classify_gallery);
        classifyLayout.setAdapter(adapter);

        final ViewPager listGallery = (ViewPager) mPageView.findViewById(R.id.pager);
        mImageRecommendAdapter = new ImageRecommendAdapter(context);
        mImageRecommendAdapter.addImageURL(mImageURL);
        listGallery.setAdapter(mImageRecommendAdapter);

        CirclePageIndicator indicator = (CirclePageIndicator) mPageView
                .findViewById(R.id.indicator);
        indicator.setViewPager(listGallery);
        listGallery.setOnTouchListener(new View.OnTouchListener() {
            float sx, sy;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if (action == MotionEvent.ACTION_DOWN) {
                    setEnabled(false);
                    mHandler.removeMessages(MyHandler.LOOP_CIRCLE);
                    sx = event.getX();
                    sy = event.getY();
                } else if (action == MotionEvent.ACTION_CANCEL
                        || action == MotionEvent.ACTION_UP) {
                    setEnabled(true);
                    loopImageRecommend();

                    ViewConfiguration configuration = ViewConfiguration.get(v.getContext());
                    int slop = configuration.getScaledTouchSlop();
                    if (Math.abs(sx - event.getX()) <= slop && Math.abs(sy - event.getY()) <= slop) {
                        mImageRecommendAdapter.onClickItem(v.getContext(),
                                listGallery.getCurrentItem());
                    }
                }
                return false;
            }
        });
        mViewPager = listGallery;

        MovieAdapter movieAdapter = new MovieAdapter(context);
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("text", "电影" + (i + 1));
            movieAdapter.addObject(map);
        }

        View view1 = mPageView.findViewById(R.id.scroll_1);
        MovieScrollView movieLayout1 = (MovieScrollView) view1.findViewById(R.id.movieLayout);
        movieLayout1.setAdapter(movieAdapter);

        View view2 = mPageView.findViewById(R.id.scroll_2);
        MovieScrollView movieLayout2 = (MovieScrollView) view2.findViewById(R.id.movieLayout);
        movieLayout2.setAdapter(movieAdapter);

        View view3 = mPageView.findViewById(R.id.scroll_3);
        MovieScrollView movieLayout3 = (MovieScrollView) view3.findViewById(R.id.movieLayout);
        movieLayout3.setAdapter(movieAdapter);

        View view4 = mPageView.findViewById(R.id.scroll_4);
        MovieScrollView movieLayout4 = (MovieScrollView) view4.findViewById(R.id.movieLayout);
        movieLayout4.setAdapter(movieAdapter);

        View view5 = mPageView.findViewById(R.id.scroll_5);
        MovieScrollView movieLayout5 = (MovieScrollView) view5.findViewById(R.id.movieLayout);
        movieLayout5.setAdapter(movieAdapter);
        
        loopImageRecommend();
    }

    private void loopImageRecommend() {
        if (!mHandler.hasMessages(MyHandler.LOOP_CIRCLE)) {
            mHandler.sendEmptyMessageDelayed(MyHandler.LOOP_CIRCLE,
                    MyHandler.LOOP_DURATION);
        }
    }

    public View getPageView() {
        return mPageView;
    }

    public void setEnabled(boolean enabled) {
        mEnabled = enabled;
        loopImageRecommend();
    }

    public void setSelected(boolean selected) {
        mSelected = selected;
        loopImageRecommend();
    }

    public void startImageRecommend() {
        mPaused = false;
        loopImageRecommend();
    }
    
    public void endImageRecommend() {
        mPaused = true;
    }
    
    @Override 
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
    }

}
