
package com.mt.video.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mt.video.R;

/**
 * �Զ���Բ��ListView
 * 
 * @author martin
 */
public class CornerListView extends ListView {
    public CornerListView(Context context) {
        super(context);
    }

    public CornerListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public CornerListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                int x = (int) ev.getX();
                int y = (int) ev.getY();
                int itemnum = pointToPosition(x, y);

                if (itemnum == AdapterView.INVALID_POSITION)
                    break;
                else {
                    if (itemnum == 0) {
                        if (itemnum == (getAdapter().getCount() - 1)) {
                            // ֻ��һ��
                            setSelector(R.drawable.listview_corner_round);
                        } else {
                            // ��һ��
                            setSelector(R.drawable.listview_corner_round_top);
                        }
                    } else if (itemnum == (getAdapter().getCount() - 1)) {
                        // ���һ��
                        setSelector(R.drawable.listview_corner_round_bottom);
                    } else {
                        // �м���
                        setSelector(R.drawable.listview_corner_middle);
                    }
                }

                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        return super.onInterceptTouchEvent(ev);
    }

    /**
     * ���ò�����
     */
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    // /***
    // * ��̬����listview�ĸ߶�
    // *
    // * @param listView
    // */
    // public void setListViewHeightBasedOnChildren(ListView listView) {
    // ListAdapter listAdapter = listView.getAdapter();
    // if (listAdapter == null) {
    // return;
    // }
    // int totalHeight = 0;
    // for (int i = 0; i < listAdapter.getCount(); i++) {
    // View listItem = listAdapter.getView(i, null, listView);
    // listItem.measure(0, 0);
    // totalHeight += listItem.getMeasuredHeight();
    // }
    // ViewGroup.LayoutParams params = listView.getLayoutParams();
    // params.height = totalHeight
    // + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
    // // params.height += 5;// if without this statement,the listview will be
    // // a
    // // little short
    // // listView.getDividerHeight()��ȡ�����ָ���ռ�õĸ߶�
    // // params.height���õ�����ListView������ʾ��Ҫ�ĸ߶�
    // listView.setLayoutParams(params);
    // }
}
