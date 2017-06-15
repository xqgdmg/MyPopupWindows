package com.example.qhsj.mypopupwindows.utils;

import android.view.View;

public class CommonUtil {
    /**
     * 测量View的宽高
     *  其实就是直接写 measure(0,0) 就可以了，这个东西并没有什么意义
     * @param view View
     */
    public static void measureWidthAndHeight(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
    }

}
