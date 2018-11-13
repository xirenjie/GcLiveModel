package com.guocai.gclive.adapter.common;

import android.content.Context;

import java.util.List;

/**
 * Created by ${吴心良}
 * on 2017/4/28.
 * description:
 */

public abstract class CommonAdapter<T> extends MyCommonAdapter {

    public CommonAdapter(Context context, int layoutId, List datas) {
        super(context, layoutId, datas);
    }



}
