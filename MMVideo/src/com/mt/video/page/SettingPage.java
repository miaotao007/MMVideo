package com.mt.video.page;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.mt.video.R;
import com.mt.video.adapter.CornerListViewAdapter;
import com.mt.video.widgets.CornerListView;

public class SettingPage extends View {

    private View mContentView;
    private String[] mItems = {"���ع���", "�������", "������", "���ѷ���", "��Ա����", "����"};
    
    public SettingPage(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        mContentView = LayoutInflater.from(context).inflate(R.layout.page_setting, null);
        
        CornerListView listView = (CornerListView) mContentView.findViewById(R.id.setting_list);
        CornerListViewAdapter listAdapter = new CornerListViewAdapter(context);
        listAdapter.setData(mItems);
        listView.setAdapter(listAdapter);
    }

    public View getPageView() {
        return mContentView;
    }
}
