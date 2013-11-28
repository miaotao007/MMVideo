package com.mt.video.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.mt.video.adapter.MainPageClassifyAdapter;

public class ClassifyScrollView extends LinearLayout {

    public ClassifyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setAdapter(MainPageClassifyAdapter adapter) {
        for (int i = 0; i < adapter.getCount(); i++) {
            final View view = adapter.getView(i, null, null);
            view.setPadding(5, 5, 5, 5);
            this.setOrientation(HORIZONTAL);
            this.addView(view, new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT));
        }
    }
}
