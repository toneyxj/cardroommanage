package com.xj.mainframe.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

/**
 * 闹钟单列模式实现
 */
public class AlarmUtils {
    public static final String ALARM_RECEIVER = "com.xj.alarm.receiver";
    private static final long TIME_INTERVAL = 5 * 1000;//闹钟执行任务的时间间隔
    private static AlarmUtils alarmUtils;
    private Context context;

    public static AlarmUtils getInstance(Context context) {
        if (alarmUtils == null) {
            synchronized (AlarmUtils.class) {
                if (alarmUtils == null)
                    alarmUtils = new AlarmUtils(context);

            }
        }
        return alarmUtils;
    }

    public AlarmUtils(Context context) {
        this.context = context.getApplicationContext();
    }

    private AlarmManager am;
    private PendingIntent pendingIntent;

    public void createAlarmManager() {
        am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(ALARM_RECEIVER);
        intent.putExtra("msg", "赶紧起床111");
        pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);//每隔5秒发送一次广播
    }

    /**
     * 开始闹钟注册
     */
    public void startAlarmManagerWork() {
        if (am == null) {
            createAlarmManager();
        }
        //版本适配
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {// 6.0及以上
            am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,
                    System.currentTimeMillis()+TIME_INTERVAL, pendingIntent);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {// 4.4及以上
            am.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+TIME_INTERVAL,
                    pendingIntent);
        } else {//重复提示
            am.setRepeating(AlarmManager.RTC_WAKEUP,
                    System.currentTimeMillis(), TIME_INTERVAL, pendingIntent);
        }
    }

    /**
     * 重复执行闹钟注册
     */
    public void restartAlarmManagerWork() {
        if (am == null) {
            createAlarmManager();
        }
        //高版本重复设置闹钟达到低版本中setRepeating相同效果
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {// 6.0及以上
            am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,
                    System.currentTimeMillis() + TIME_INTERVAL, pendingIntent);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {// 4.4及以上
            am.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                     + TIME_INTERVAL, pendingIntent);
        }
    }
}
