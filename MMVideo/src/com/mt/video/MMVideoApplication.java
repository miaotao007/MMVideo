
package com.mt.video;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class MMVideoApplication extends Application {
    private static MMVideoApplication sInstance;
    private Handler mHandler;

    public MMVideoApplication() {
        sInstance = this;
        mHandler = new Handler();
    }

    @Override
    public void onCreate() {

        super.onCreate();

        initImageLoader(this);
    }

    public static MMVideoApplication getInstance() {
        return sInstance;
    }
    
    /**
     * Get an ui thread handler conveniently
     * @return
     */
    public Handler getHandler() {
        return mHandler;
    }

    private void initImageLoader(Context context) {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc()
                .cacheInMemory()
                .resetViewBeforeLoading()
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .defaultDisplayImageOptions(defaultOptions).build();

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }
}
