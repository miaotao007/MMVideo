
package com.mt.video.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.mt.video.R;
import com.mt.video.page.MMImagePage;
import com.mt.video.page.MainPage;
import com.mt.video.page.SettingPage;

public class MainActivity extends BaseActivity {

    private MainPage mMainPage;
    private SettingPage mSettingPage;
    private MMImagePage mMMImagePage;
    
    private View mClickedView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        mMainPage = new MainPage(this);
        setContentLayout(mMainPage.getPageView());

        ImageView setting_Img = (ImageView) findViewById(R.id.bottom_menu_right);
        setting_Img.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if(mClickedView != null && mClickedView.equals(v)) {
                    mClickedView = v;
                    return;
                }
                
                mClickedView = v;
                
                if(mSettingPage == null) {
                    mSettingPage = new SettingPage(MainActivity.this);
                }
                setContentLayout(mSettingPage.getPageView());
            }
        });
        
        ImageView main_page = (ImageView) findViewById(R.id.bottom_menu_middle);
        main_page.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if(mClickedView != null && mClickedView.equals(v)) {
                    return;
                }
                
                mClickedView = v;
                
                setContentLayout(mMainPage.getPageView());
            }
        });

        ImageView mmImage_page = (ImageView) findViewById(R.id.bottom_menu_left);
        mmImage_page.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if(mClickedView != null && mClickedView.equals(v)) {
                    return;
                }
                
                mClickedView = v;
                
                if(mMMImagePage == null) {
                    mMMImagePage = new MMImagePage(MainActivity.this);
                }
                setContentLayout(mMMImagePage.getPageView());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
