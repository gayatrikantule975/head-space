package com.example.headspace;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.TimeUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
public class instrumentalMusicActivity extends AppCompatActivity {
TextView tvSongname;
ImageView ivSongimage;
SeekBar sbProgress;
TextView tvStartTime,tvTotalTime;
ImageView ivPrevious,ivBackword,ivplay,ivForward,ivNext;
int currentIndex=0;
MediaPlayer mMediaPlayer;
private static  int sTime=0,tTime=0,oTime=0, fTime=5000,bTime=5000;
Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_instrumental_music);
        setTitle("My Player");
        tvSongname = findViewById(R.id.tvSongName);
        ivSongimage = findViewById(R.id.ivSongimage);
        tvStartTime = findViewById(R.id.tvStartTime);
        sbProgress = findViewById(R.id.sbProgress);
        tvTotalTime = findViewById(R.id.tvTotaltime);
        ivPrevious = findViewById(R.id.ivPrevious);
        ivBackword = findViewById(R.id.ivBackword);
        ivplay = findViewById(R.id.ivPlay);
        ivForward = findViewById(R.id.ivForward);
        ivNext = findViewById(R.id.ivNext);
        ArrayList<Integer> songArrayList = new ArrayList<>();
        songArrayList.add(0, R.raw.hamnava);
        songArrayList.add(1, R.raw.thodi_jagah);
        songArrayList.add(2, R.raw.pal_ek_pal);
        songArrayList.add(3, R.raw.ye_rate_ye_mausam);
        songArrayList.add(4, R.raw.kalhoyanaho);
        mMediaPlayer = MediaPlayer.create(instrumentalMusicActivity.this, songArrayList.get(currentIndex));

        ivplay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
                    mMediaPlayer.pause();
                    ivplay.setImageResource(R.drawable.baseline_play_circle_filled_24);
                } else {
                    mMediaPlayer.start();
                    ivplay.setImageResource(R.drawable.baseline_pause_circle_24);
                }
                tTime = mMediaPlayer.getDuration();
                sTime = mMediaPlayer.getCurrentPosition();
                if (oTime == 0) {
                    sbProgress.setMax(tTime);
                    oTime = 1;
                }
                tvTotalTime.setText(String.format("%d:%d ",
                        TimeUnit.MILLISECONDS.toMinutes(tTime),
                        TimeUnit.MILLISECONDS.toSeconds(tTime)
                                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(tTime))));
                tvStartTime.setText(String.format("%d:%d ",
                        TimeUnit.MILLISECONDS.toMinutes(sTime),
                        TimeUnit.MILLISECONDS.toSeconds(sTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(sTime))));
                handler.postDelayed(UpdateSongProgress, 1000);
                songDetails();
            }
        });

        sbProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mMediaPlayer.seekTo(progress);
                    sbProgress.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        ivPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex > 0) {
                    currentIndex--;
                } else {
                    currentIndex = songArrayList.size() - 1;
                }
                if (mMediaPlayer.isPlaying()) {
                    mMediaPlayer.stop();
                }
                if (mMediaPlayer != null) {
                    ivplay.setImageResource(R.drawable.baseline_pause_circle_24);
                }
                mMediaPlayer=MediaPlayer.create(instrumentalMusicActivity.this,songArrayList.get(currentIndex));
                tTime = mMediaPlayer.getDuration();
                sTime = mMediaPlayer.getCurrentPosition();
                oTime = 0;
                if (oTime == 0) {
                    sbProgress.setMax(tTime);
                    oTime = 1;
                }
                tvTotalTime.setText(String.format("%d :%d  ",
                        TimeUnit.MILLISECONDS.toMinutes(tTime),
                        TimeUnit.MILLISECONDS.toSeconds(tTime)
                                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(tTime))));
                tvStartTime.setText(String.format("%d :%d  ",
                        TimeUnit.MILLISECONDS.toMinutes(sTime),
                        TimeUnit.MILLISECONDS.toSeconds(sTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(sTime))));
                handler.postDelayed(UpdateSongProgress, 1000);
                mMediaPlayer.start();
                songDetails();
            }
        });
        ivNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentIndex<songArrayList.size()-1)
                {
                    currentIndex++;
                }
                else {
                    currentIndex=0;
                }
                if(mMediaPlayer.isPlaying())
                {
                    mMediaPlayer.stop();
                }
                if (mMediaPlayer!=null)
                {
                    ivplay.setImageResource(R.drawable.baseline_pause_circle_24);
                }
                mMediaPlayer=MediaPlayer.create(instrumentalMusicActivity.this,songArrayList.get(currentIndex));
                tTime = mMediaPlayer.getDuration();
                sTime = mMediaPlayer.getCurrentPosition();
                oTime = 0;
                if (oTime == 0) {
                    sbProgress.setMax(tTime);
                    oTime = 1;
                }
                tvTotalTime.setText(String.format("%d :%d  ",
                        TimeUnit.MILLISECONDS.toMinutes(tTime),
                        TimeUnit.MILLISECONDS.toSeconds(tTime)
                                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(tTime))));
                tvStartTime.setText(String.format("%d :%d  ",
                        TimeUnit.MILLISECONDS.toMinutes(sTime),
                        TimeUnit.MILLISECONDS.toSeconds(sTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(sTime))));
                handler.postDelayed(UpdateSongProgress, 1000);
                mMediaPlayer.start();
                songDetails();
            }
        });
        ivBackword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((sTime-bTime)>0)
                {
                    sTime=sTime-bTime;
                    mMediaPlayer.seekTo(sTime);
                }
                else
                {
                    Toast.makeText(instrumentalMusicActivity.this,"Can not jump backword",Toast.LENGTH_SHORT).show();
                }
            }
        });
        ivForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((sTime+fTime)<tTime)
                {
                    sTime=sTime+fTime;
                    mMediaPlayer.seekTo(sTime);
                }
                else {
                    Toast.makeText(instrumentalMusicActivity.this,"Can not jump forward",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
            private void songDetails() {
                if (currentIndex == 0) {
                    tvSongname.setText("hamnava");
                    ivSongimage.setImageResource(R.drawable.hamnava);
                } else if (currentIndex == 1) {
                    tvSongname.setText("thodi_jagah");
                    ivSongimage.setImageResource(R.drawable.thodi_jagah);
                } else if (currentIndex == 2) {
                    tvSongname.setText("pal_ek_pal");
                    ivSongimage.setImageResource(R.drawable.pal_ek_pal);
                } else if (currentIndex == 3) {
                    tvSongname.setText("ye_rate_ye_mausam");
                    ivSongimage.setImageResource(R.drawable.ye_rate_ye_mausam);
                } else if (currentIndex == 4) {
                    tvSongname.setText("kalhoyanaho");
                    ivSongimage.setImageResource(R.drawable.kal_ho_na_ho);
                }
            }
            private Runnable UpdateSongProgress = new Runnable() {
                @Override
                public void run() {
                    sTime = mMediaPlayer.getCurrentPosition();
                    tvStartTime.setText(String.format("%d:%d",
                            TimeUnit.MILLISECONDS.toMinutes(sTime),
                            TimeUnit.MILLISECONDS.toSeconds(sTime) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(sTime))
                    ));
                    sbProgress.setProgress(sTime);
                    handler.postDelayed(this, 1000);
                }
            };
        }