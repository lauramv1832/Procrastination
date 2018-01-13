package com.example.ikhan.procrastinationapp;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.Handler;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.lang.reflect.Field;

import static android.content.Context.ACTIVITY_SERVICE;

/**
 * Created by Laura on 1/11/18.
 */

public class DetectLaunchRunnable {

    private static Context context;
    //Context context = MyApplication.getInstance()

    private static ActivityManager actvityManager;

    {
        actvityManager = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
    }

    final Handler handler = new Handler();
    final Runnable r = new Runnable(){
        @Override
        public void run() {
            String[] activePackages;
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
                activePackages = getActivePackages();
            } else {
                activePackages = getActivePackagesCompat();
            }
            if (activePackages != null) {
                for (String activePackage : activePackages) {
                    if (activePackage.equals("com.google.android.chrome")) {
                        System.out.println("chrome");
                    }
                }
            }
            handler.postDelayed(r, 1000);
        }
    };

    public ActivityManager.RunningAppProcessInfo getApp() {
        final int PROCESS_STATE_TOP = 2;
        ActivityManager.RunningAppProcessInfo currentInfo = null;
        Field field = null;
        try {
            field = ActivityManager.RunningAppProcessInfo.class.getDeclaredField("processState");
        } catch (Exception ignored) {
        }
        ActivityManager am = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appList = am.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo app : appList) {
            if (app.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
                    && app.importanceReasonCode == ActivityManager.RunningAppProcessInfo.REASON_UNKNOWN) { //==0
                Integer state = null;
                try {
                    state = field.getInt(app);
                } catch (Exception e) {
                }
                if (state != null && state == PROCESS_STATE_TOP) {
                    currentInfo = app;
                    break;
                }
            }
        }
        return currentInfo;
    }

    public String[] getActivePackagesCompat() {
        final List<ActivityManager.RunningTaskInfo> taskInfo = actvityManager.getRunningTasks(1);
        final ComponentName componentName = taskInfo.get(0).topActivity;
        final String[] activePackages = new String[1];
        activePackages[0] = componentName.getPackageName();
        return activePackages;
    }

    public String[] getActivePackages() {
        final Set<String> activePackages = new HashSet<String>();
        final List<ActivityManager.RunningAppProcessInfo> processInfos = actvityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo processInfo : processInfos) {
            if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                activePackages.addAll(Arrays.asList(processInfo.pkgList));
            }
        }
        return activePackages.toArray(new String[activePackages.size()]);
    }
}
