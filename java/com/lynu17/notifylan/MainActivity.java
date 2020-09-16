package com.lynu17.notifylan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button notifyBn = findViewById(R.id.notify);
    }

    public void notify(View view){
        notification();
       // finish();
    }

    private void notification() {

        Notification notification;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder builder = new Notification.Builder(this, "1");
            builder.setSmallIcon(R.mipmap.ic_launcher_round)//设置通知小图标在状态栏显示（必须设置）
                    //设置通知大图标，在通知栏显示
                    .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher))
                    //通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
                    .setWhen(System.currentTimeMillis())
                    //设置该通知优先级
                    .setPriority(Notification.PRIORITY_HIGH)
                    //通知首次出现在通知栏，带上升动画效果的
                    .setTicker("新通知")
                    //设置通知栏标题
                    .setContentTitle("新通知标题")
                    //设置通知栏内容
                    .setContentText("新通知内容")
                    //设置通知出现时的震动具体值
                    .setVibrate(new long[0])
                    //ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
                    .setOngoing(false)
                    //设置这个标志当用户单击面板就可以让通知将自动取消
                    .setAutoCancel(true);
            //创建PendingIntent，处理点击通知之后的逻辑
            Intent intent = new Intent(this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this,
                    0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            builder.setContentIntent(pendingIntent);
            notification = builder.build();
        } else {
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
            mBuilder.setContentTitle("新通知标题1")//设置通知栏标题
                    //通知首次出现在通知栏，带上升动画效果的
                    .setTicker("通知来啦")
                    //通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
                    .setWhen(System.currentTimeMillis())
                    //设置该通知优先级
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    //设置通知栏内容
                    .setContentText("新通知内容1")
                    //设置通知大图标，在通知栏显示
                    .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher))
                    //设置这个标志当用户单击面板就可以让通知将自动取消
                    .setAutoCancel(true)
                    //ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
                    .setOngoing(false)
                    //设置通知小图标在状态栏显示（必须设置）
                    .setSmallIcon(R.mipmap.ic_launcher_round);//设置通知小ICON
            Intent intent = new Intent(this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            mBuilder.setContentIntent(pendingIntent);
            notification = mBuilder.build();
        }
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.notify(1, notification);
        }
    }

}
