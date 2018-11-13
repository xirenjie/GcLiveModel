package com.guocai.gclive.recycle;

/**
 * Created by a on 2017/6/16.
 *
 * @auther XRJ
 */
public interface OnItemClickListeners<T> {
    void onItemClick(ViewHolder viewHolder, T data, int position);
}
