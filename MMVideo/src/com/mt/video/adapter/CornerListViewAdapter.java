
package com.mt.video.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mt.video.R;

public class CornerListViewAdapter extends BaseAdapter {

    private String[] mItems;
    private Context mContext;

    public CornerListViewAdapter(Context context) {
        mContext = context;
    }
    
    public void setData(String[] items) {
        mItems = items;
    }
    
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mItems.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.listitem_corner, null);
        }

        TextView text = (TextView) convertView.findViewById(R.id.text);
        text.setText(mItems[position]);
        return convertView;
    }

}
