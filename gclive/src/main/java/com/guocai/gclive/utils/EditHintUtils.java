package com.guocai.gclive.utils;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Create  By xrj ON 2018/8/2 0002
 */
public class EditHintUtils {
    /**
     * 隐藏软键盘
     * @param context
     * @param v
     * @return
     */
    public static  Boolean hideInputMethod(Context context, View v) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            return imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
        return false;
    }



    public static void hideKeyboard(Context ctx) {
        if (ctx != null) {
            View view = ((Activity) ctx).getCurrentFocus();
            if (view != null) {
                InputMethodManager inputManager = (InputMethodManager) ctx
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    /**
     * 点击其他地方隐藏软键盘
     * @param view
     * @param ctx
     */
    public static void setupUI(View view, final Context ctx) {
        if(!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideKeyboard(ctx);
                    return false;
                }
            });
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView, ctx);
            }
        }
    }

}
