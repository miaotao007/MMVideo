
package com.mt.video.page;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mt.video.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class MMImagePage extends View {

    private View mContentView;
    
    private LinearLayout mLayoutLeft, mLayoutRight;
    private DisplayImageOptions mOptions;
    private int mNum;
    
    public static final String[] sImageUrl = {
            "http://f.hiphotos.baidu.com/pic/w%3D310/sign=9fc23a46d53f8794d3ff4e2fe21a0ead/f636afc379310a55be9e070ab64543a983261091.jpg",
            "http://a.hiphotos.baidu.com/pic/w%3D310/sign=b7a838c934fae6cd0cb4ad603fb20f9e/b151f8198618367afa37a6232f738bd4b21ce596.jpg",
            "http://d.hiphotos.baidu.com/pic/w%3D310/sign=896b1ed1bf096b63811958513c328733/ac345982b2b7d0a28fd9dd63caef76094b369a38.jpg",
            "http://c.hiphotos.baidu.com/pic/w%3D310/sign=d97658d28601a18bf0eb144eae2e0761/472309f79052982220ace73dd6ca7bcb0b46d4b5.jpg",
            "http://e.hiphotos.baidu.com/pic/w%3D310/sign=ad11561554fbb2fb342b5e137f4b2043/3b87e950352ac65ce4c7383bfaf2b21192138abc.jpg",
            "http://a.hiphotos.baidu.com/pic/w%3D310/sign=0997b797b151f819f125054beab54a76/279759ee3d6d55fb827e479a6c224f4a21a4ddd5.jpg",
            "http://h.hiphotos.baidu.com/pic/w%3D310/sign=1fa2c18c30adcbef013478079caf2e0e/bd315c6034a85edf42b1066b48540923dd5475bd.jpg",
            "http://b.hiphotos.baidu.com/pic/w%3D310/sign=78d5c76537d3d539c13d09c20a86e927/37d12f2eb9389b50fefd07c98435e5dde7116e2f.jpg"
    };
    
    public MMImagePage(Context context) {
        super(context);
        initView(context);
    }
    
    private void initView(Context context) {
        mContentView = LayoutInflater.from(context).inflate(R.layout.page_mmimage, null);
        
        mOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc()
                .cacheInMemory()
                .resetViewBeforeLoading()
                .displayer(new FadeInBitmapDisplayer(500))
                .build();

        mLayoutLeft = (LinearLayout) mContentView.findViewById(R.id.layout_left);
        mLayoutRight = (LinearLayout) mContentView.findViewById(R.id.layout_right);

        getMMImage(context, sImageUrl[0]);
    }
    
    private void getMMImage(final Context context, String imgUrl) {
        try {
            ImageView img = new ImageView(context);

            if (mNum % 2 == 0) {
                mLayoutLeft.addView(img);
            } else {
                mLayoutRight.addView(img);
            }

            ImageLoader.getInstance().displayImage(imgUrl, img, mOptions,
                    new ImageLoadingListener() {

                        @Override
                        public void onLoadingStarted(String imageUri, View view) {
                        }

                        @Override
                        public void onLoadingFailed(String imageUri, View view,
                                FailReason failReason) {
                        }

                        @Override
                        public void onLoadingComplete(String imageUri, View view,
                                Bitmap loadedImage) {
                            ImageView mmImageView = (ImageView) view;

                            int oldwidth = loadedImage.getWidth();
                            int oldheight = loadedImage.getHeight();

                            int itemWidth = ((Activity) context).getWindowManager().getDefaultDisplay().getWidth() / 2;
                            LayoutParams lp = mmImageView.getLayoutParams();

                            lp.width = LayoutParams.MATCH_PARENT;
                            lp.height = ((oldheight * itemWidth) / oldwidth);
                            mmImageView.setLayoutParams(lp);
                            
                            mmImageView.setOnClickListener(new OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    // 
                                }
                            });

                            mNum++;
                            
                            if(mNum < sImageUrl.length) {
                                getMMImage(context, sImageUrl[mNum]);
                            }
                        }

                        @Override
                        public void onLoadingCancelled(String imageUri, View view) {
                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public View getPageView() {
        return mContentView;
    }
}
