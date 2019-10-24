package com.mwalagho.ferdinand.cosocial;



import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;


import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;




public class YoutubeActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

//

    static final String GOOGLE_API_KEY = "AIzaSyAMJQnmOh5zCgRLPCf5O4sQy-JgDvTtPzA";
    static final String YOUTUBE_VIDEO_ID = "B9H3iinXZv0";
    static final String YOUTUBE_PLAYLIST = "PLv3hscIxgEp_7GARv6wxpLIV-khzrFg9O";
    public static final String TAG = YoutubeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);


//        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.activity_youtube);
        ConstraintLayout layout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.activity_youtube, null);
        setContentView(layout);

//        Button button = new Button(this);
//        button.setLayoutParams(new ConstraintLayout.LayoutParams(300,80));
//        button.setText("Button added");
//        layout.addView(button);

        YouTubePlayerView playerView = new YouTubePlayerView(this);
        playerView.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout.addView(playerView);
        playerView.initialize(GOOGLE_API_KEY, this);


    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        Log.d(TAG, "onInitializationSuccess: provider is" + provider.getClass().toString());
        Toast.makeText(this, "Initialized Succesfully", Toast.LENGTH_LONG).show();

        if (!b) {
            youTubePlayer.cueVideo(YOUTUBE_VIDEO_ID);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        final int REQUEST_CODE = 1;

        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this, REQUEST_CODE).show();
        } else {
            String errorMessage = String.format("There was an error initializing the YoutubePlayer (%1$s)", youTubeInitializationResult.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();


        }
         YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
            @Override
            public void onPlaying() {

            }

            @Override
            public void onPaused() {

            }

            @Override
            public void onStopped() {

            }

            @Override
            public void onBuffering(boolean b) {

            }

            @Override
            public void onSeekTo(int i) {

            }
        };
    }
}
