package com.kimi.md5signviewer.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * 2016-08-02 zjm 添加了在show方法中使用反射来获取当前运行环境的上下文，可以不进行初始化
 */
public class ToastUtils {
    static Context context;

    @Deprecated
    public static void init(Context context) {
        ToastUtils.context = context;
    }

    private static int GRAVITY = Gravity.CENTER;

    public static void showLong(String message) {
        show(message, Toast.LENGTH_LONG);
    }

    public static Toast showShort(String message) {
        return show(message, Toast.LENGTH_SHORT);
    }

    public static void showLong(int textId) {
        show(textId, Toast.LENGTH_LONG);
    }

    public static void showShort(int textId) {
        show(textId, Toast.LENGTH_SHORT);
    }

    public static Toast show(String text, int duration) {
        if (context == null) {
            context = SystemUtil.getApplicationUsingReflection();
        }
        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(GRAVITY, 0, 0);
        toast.show();
        return toast;
    }

    public static void show(int textId, int duration) {
        if (context == null) {
            context = SystemUtil.getApplicationUsingReflection();
        }
        Toast toast = Toast.makeText(context, textId, duration);
        toast.setGravity(GRAVITY, 0, 0);
        toast.show();
    }

}