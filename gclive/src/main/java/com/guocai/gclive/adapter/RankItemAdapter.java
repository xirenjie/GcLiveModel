package com.guocai.gclive.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.guocai.gclive.R;
import com.guocai.gclive.bean.RoomRankBean;
import com.guocai.gclive.recycle.BaseAdapter;
import com.guocai.gclive.recycle.ViewHolder;

import java.util.List;


/**
 * Create  By xrj ON 2018/7/30 0030
 */
public class RankItemAdapter extends BaseAdapter<RoomRankBean.RespBean> {
    private int position=1;
    public RankItemAdapter(Context context, List<RoomRankBean.RespBean> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }


    @Override
    protected void convert(ViewHolder holder, RoomRankBean.RespBean data,int position) {
        holder.setText(R.id.room_rank_title,data.getExp());
        holder.setText(R.id.room_rank_msg,data.getNickName());
        holder.setText(R.id.rank_bean_num,data.getUseBean()+"");
        TextView textView=holder.getView(R.id.rank_item_text);
        ImageView imageView=holder.getView(R.id.rank_item_img);
        if(position==0)
        {
            imageView.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
            imageView.setBackgroundResource(R.mipmap.champion_img);
            textView.setText("");
        }else
        {
            imageView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
            imageView.setBackgroundResource(R.mipmap.champion_img);
            textView.setText(position+1+"");
        }

    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.rank_item_layout;
    }
}
