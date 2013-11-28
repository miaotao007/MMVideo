package com.mt.video.widgets;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.mt.video.activity.VideoViewActivity;
import com.mt.video.adapter.MovieAdapter;

import java.util.Map;

public class MovieScrollView extends LinearLayout {

    private Context mContext;

    public MovieScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public void setAdapter(MovieAdapter adapter) {
        for (int i = 0; i < adapter.getCount(); i++) {
            final Map<String, Object> map = adapter.getItem(i);
            View view = adapter.getView(i, null, null);
            view.setPadding(10, 10, 10, 10);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(mContext, "Äúµã»÷ÁË" + map.get("text"), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, VideoViewActivity.class);
                    mContext.startActivity(intent);
                }
            });
            this.setOrientation(HORIZONTAL);
            this.addView(view, new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT));
        }
    }
}
