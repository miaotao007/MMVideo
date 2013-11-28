
package com.mt.video.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mt.video.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MovieAdapter extends BaseAdapter {

    private List<Map<String, Object>> mList;
    private Context mContext;
    private DisplayImageOptions mOptions;

    private String[] mImageUrl = new String[] {
            "http://pic9.nipic.com/20100911/2167235_012514749906_2.jpg",
            "http://pic5.nipic.com/20091226/2167235_025734105896_2.jpg",
            "http://image11.m1905.cn/uploadfile/2008/1006/090457929.jpg",
            "http://pic3.nipic.com/20090625/18779_091004007_2.jpg",
            "http://pic12.nipic.com/20110116/479029_230611219170_2.jpg",
            "http://pic11.nipic.com/20101108/479029_025037750000_2.jpg",
            "http://pic3.nipic.com/20090612/2817820_190745044_2.jpg",
            "http://img4.mypsd.com.cn/20110407/Mypsd_22896_201104071811460010B.jpg",
            "http://img.sucai.redocn.com/attachments/images/200910/20091008/20091008_5005ddd9beb6b96d3461G4oTxkuHKXqI.jpg",
            "http://pic4.nipic.com/20091218/2167235_014638696737_2.jpg"
    };

    public MovieAdapter(Context context) {
        mContext = context;
        mList = new ArrayList<Map<String, Object>>();

        mOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc()
                .cacheInMemory()
                .resetViewBeforeLoading()
                .displayer(new RoundedBitmapDisplayer(12))
                .build();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Map<String, Object> getItem(int location) {
        return mList.get(location);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    public void addObject(Map<String, Object> map) {
        mList.add(map);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int location, View arg1, ViewGroup arg2) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.my_movie, null);
        ImageView image = (ImageView) view.findViewById(R.id.movie_image);
        TextView text = (TextView) view.findViewById(R.id.movie_text);
        Map<String, Object> map = getItem(location);
        ImageLoader.getInstance().displayImage(mImageUrl[location], image, mOptions);
        text.setText(map.get("text").toString());
        return view;
    }

}
