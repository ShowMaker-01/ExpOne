package com.example.myapplication2.util;

import android.content.Context;
import android.widget.Toast;

//显示提示信息，以弹窗的形式显示在应程序界面底部
public class ToastUtil {

    public static void toastShort(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();//使消息较短时间消失
    }

    public static void toastLong(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();//使消息较长时间消失
    }
}