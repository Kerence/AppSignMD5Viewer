package com.kimi.md5signviewer.util;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;

import java.util.List;

/**
 * 获取安卓设备ID
 *
 * @author zhangjinming
 * @date 2013-11-10
 */
public class SystemUtil {
    public static boolean isVMDalvik() {
        return "Dalvik".equals(java.lang.System.getProperty("java.vm.name"));
    }

    public static String getDeviceId(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

    public static String getAndroidId(Context context) {
        if (context == null) {
            return null;
        }
        return Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
    }

    public static String getStackTraceString() {
        StringBuilder sb = new StringBuilder("");
        Exception e = new Exception();
        StackTraceElement[] trace = e.getStackTrace();
        for (int i = 0; i < trace.length; i++)
            sb.append(trace[i] + "\n");
        return sb.toString();
    }

    public static Application getApplicationUsingReflection() {
        try {
            return (Application) Class.forName("android.app.ActivityThread")
                    .getMethod("currentApplication").invoke(null, (Object[]) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @return null may be returned if the specified process not found
     */
    public static String getProcessName(Context cxt, int pid) {
        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return null;
    }
}
