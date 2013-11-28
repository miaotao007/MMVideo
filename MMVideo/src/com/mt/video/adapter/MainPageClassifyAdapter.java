
package com.mt.video.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.mt.video.R;

public class MainPageClassifyAdapter extends BaseAdapter {

    private String[] mClassifyText;
    private Context mContext;
    private TextView[] mTextViews;

    public MainPageClassifyAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return mClassifyText.length;
    }

    @Override
    public String getItem(int location) {
        return mClassifyText[location];
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    public void addData(String[] classifyName) {
        mClassifyText = classifyName;
        mTextViews = new TextView[mClassifyText.length];
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int location, View arg1, ViewGroup arg2) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.mainpage_classify, null);

        if (mTextViews[location] == null) {
            TextView text = (TextView) view.findViewById(R.id.classify_text);
            mTextViews[location] = text;
        }

        final String classifyName = mClassifyText[location];
        mTextViews[location].setText(classifyName);

        mTextViews[location].setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Äúµã»÷ÁË" + classifyName, Toast.LENGTH_SHORT).show();
                for (TextView tv : mTextViews) {
                    tv.setTextColor(mContext.getResources().getColor(R.color.white));
                }

                mTextViews[location].setTextColor(mContext.getResources().getColor(
                        R.color.blue_pressed));
            }
        });
        
        return view;
    }

}
