package com.guocai.gclive.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.guocai.gclive.R;
import com.guocai.gclive.entity.Member;

/**
 * Created by WZH on 2016/12/25.
 */

public class MemberAdapter extends BaseListAdapter<Member> {
    public MemberAdapter(Context ctx) {
        super(ctx);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder ;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_menber, null, false);
            viewHolder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Member member = datas.get(position);
        if (!TextUtils.isEmpty(member.img)) {
            Glide.with(ctx).load(member.img).into(viewHolder.avatar);

        }else {
            viewHolder.avatar.setImageResource(R.mipmap.default_head);
        }
        return  convertView ;
    }
    public class ViewHolder{
        ImageView avatar ;
    }
}
