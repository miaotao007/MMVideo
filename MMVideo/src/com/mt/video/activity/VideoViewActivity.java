/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mt.video.activity;

import android.app.Activity;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

import com.mt.video.R;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.MediaPlayer.OnPreparedListener;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class VideoViewActivity extends Activity {

	private String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Video/New Divide 480.flv";
	private VideoView mVideoView;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		if (!io.vov.vitamio.LibsChecker.checkVitamioLibs(this, R.string.init_decoders, R.raw.libarm))
			return;

		setContentView(R.layout.videoview);
		mVideoView = (VideoView) findViewById(R.id.surface_view);
//		Uri pathUri = this.getIntent().getData();
		Uri pathUri = Uri.parse("http://115.238.175.78/vhot2.qqvideo.tc.qq.com/e0116o5i5bi.mp4?vkey=FFF2DBF9B516C29A4A7518832FCB837AFA731D2A849ED42CF9BC85E74AAF7C1D0593D3E0EB7B826C&br=68&platform=0&fmt=mp4&level=1");
		if (pathUri != null)
			mVideoView.setVideoURI(pathUri);
		else
			mVideoView.setVideoPath(path);
		mVideoView.setVideoQuality(MediaPlayer.VIDEOQUALITY_HIGH);
		mVideoView.setOnPreparedListener(new OnPreparedListener() {
			@Override
			public void onPrepared(MediaPlayer mp) {
				mVideoView.setSubShown(true);
			}
		});
		
		MediaController controller = new MediaController(this);
		controller.setFileName("备胎的自我修养");
		mVideoView.setMediaController(controller);
		mVideoView.requestFocus();
	}

	private int mLayout = VideoView.VIDEO_LAYOUT_ORIGIN;

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		if (mVideoView != null)
			mVideoView.setVideoLayout(mLayout, 0);
		super.onConfigurationChanged(newConfig);
	}
}
