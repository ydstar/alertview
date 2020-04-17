package com.alertview.lib;

import android.view.Gravity;

/**
 * Author: Jett
 * Date: 2020-04-17 18:32
 * Email: hydznsq@163.com
 * Des:
 */
public class AlertAnimateUtil {
    private static final int INVALID = -1;

    static int getAnimationResource(int gravity, boolean isInAnimation) {
        switch (gravity) {
            case Gravity.BOTTOM:
                return isInAnimation ? R.anim.slide_in_bottom : R.anim.slide_out_bottom;
            case Gravity.CENTER:
                return isInAnimation ? R.anim.fade_in_center : R.anim.fade_out_center;
        }
        return INVALID;
    }
}
