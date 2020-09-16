package com.lynu17.notifylan;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;

public class notifylanApp extends Application
{

    @Override
    public void onCreate()
    {
        super.onCreate();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 通知渠道的id（后台推送则与后台设置的Id保持一致和创建通知时传入的ChannelId保持一致）
            String channelId = "1";
            // 用户可以看到的通知渠道的名字.
            String name = "新通知";
            // 用户可以看到的通知渠道的描述
            String description = "新通知描述";
            // 该通知的重要级别
            int importance = NotificationManager.IMPORTANCE_HIGH;
            // 创建渠道
            NotificationChannel mChannel = new NotificationChannel(channelId, name, importance);
            // 配置通知渠道的属性
            mChannel.setDescription(description);
            // 设置通知出现时的闪灯（如果 android 设备支持的话）
            mChannel.enableLights(true);
            // 设置桌面图标右上角通知提示的颜色
            mChannel.setLightColor(Color.RED);
            // 设置通知出现时的震动（如果 android 设备支持的话）
            mChannel.enableVibration(true);
            // 设置发布到此频道的通知的振动模式
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            // 是否在久按桌面图标时显示此渠道的通知
            mChannel.setShowBadge(true);
            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if (mNotificationManager != null) {
                // 创建单个渠道
                mNotificationManager.createNotificationChannel(mChannel);
                // 创建多个渠道
                // List<NotificationChannel> list = new ArrayList<>();
                // list.add(mChannel);
                // mNotificationManager.createNotificationChannels(list);
            }
        }
    }
}
