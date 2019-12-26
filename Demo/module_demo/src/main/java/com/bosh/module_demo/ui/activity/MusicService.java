package com.bosh.module_demo.ui.activity;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import com.bosh.module_demo.R;
import com.china.bosh.mylibrary.utils.NotificationChannels;

/**
 * @author bosh
 */
public class MusicService extends Service {
    private final String TAG = "MusicService";

    private MediaPlayer mMediaPlayer;

    private AudioManager mAudioManager;

    private AudioManager.OnAudioFocusChangeListener mAudioFocusChange = new
            AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    switch (focusChange) {
                        case AudioManager.AUDIOFOCUS_GAIN:
                            Log.e(TAG, "AUDIO_FOCUS_GAIN");
                            try {
                                startPlayMusic();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case AudioManager.AUDIOFOCUS_LOSS:
                            Log.e(TAG, "AUDIO_FOCUS_LOSS");

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                AudioFocusRequest.Builder builder = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN);
                                builder.setOnAudioFocusChangeListener(mAudioFocusChange);
                                mAudioManager.abandonAudioFocusRequest(builder.build());
                            } else {
                                mAudioManager.abandonAudioFocus(mAudioFocusChange);
                            }
                            break;
                        case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                            Log.e(TAG, "AUDIO_FOCUS_LOSS_TRANSIENT");
                            break;
                        case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                            Log.e(TAG, "AUDIO_FOCUS_LOSS_TRANSIENT_CAN_DUCK");
                            break;
                        default:
                    }
                }
            };

    public MusicService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //音标处理
        mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        if (mAudioManager != null) {
            mAudioManager.requestAudioFocus(mAudioFocusChange, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
        }
        mMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.demo_wusheng);
        mMediaPlayer.setLooping(true);

        startPlayMusic();
        int icon = R.drawable.demo_huaji;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder builder = new Notification.Builder(this, NotificationChannels.MEDIA);
            builder.setContentTitle("前台服务")
                    .setContentText("保活？")
                    //设置点击通知后自动删除通知
                    .setAutoCancel(true)
                    //设置显示通知时间
                    .setShowWhen(true)
                    //设置通知右侧大图标
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.demo_huaji))
                    .setSmallIcon(icon);
            PendingIntent pendingintent = PendingIntent.getActivity(this, 0, new Intent(), PendingIntent.FLAG_CANCEL_CURRENT);
            //设置点击通知相应事件
            builder.setContentIntent(pendingintent);
            startForeground(20, builder.build());
        }

        return START_STICKY;
    }


    private void startPlayMusic() {
        if (mMediaPlayer != null && !mMediaPlayer.isPlaying()) {
            Log.e(TAG, "启动后台播放音乐");
            mMediaPlayer.start();
        }
    }

    private void stopPlayMusic() {
        if (mMediaPlayer != null) {
            Log.e(TAG, "关闭后台播放音乐");
            mMediaPlayer.stop();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopPlayMusic();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            stopForeground(Service.STOP_FOREGROUND_REMOVE);
        } else {
            stopForeground(true);
        }
        Log.e(TAG, TAG + "---->onDestroy,停止服务");
    }
}
