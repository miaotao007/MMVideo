
package com.mt.video.activity;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.mt.video.R;

public class BaseActivity extends Activity {

    private LinearLayout mBaseContent;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_layout);
    }
    
    public void setContentLayout(int layoutId) {
        if(mBaseContent == null) {
            mBaseContent = (LinearLayout) findViewById(R.id.content_layout);
        } else {
            mBaseContent.removeAllViews(); 
        }
        
        View view = getLayoutInflater().inflate(layoutId, null);
        mBaseContent.addView(view);
    }
    
    public void setContentLayout(View view) {
        if(mBaseContent == null) {
            mBaseContent = (LinearLayout) findViewById(R.id.content_layout);
        } else {
            mBaseContent.removeAllViews(); 
        }
        
        mBaseContent.addView(view);
    }
    
    @Override
    public void onResume() {
        super.onResume();
    }

}
