package com.guocai.gclive.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.guocai.gclive.R;
import com.guocai.gclive.activity.LiveActivity;
import com.guocai.gclive.bean.RoomMessageBean;
import com.guocai.gclive.net.OkhttpRequest;
import com.guocai.gclive.net.StringCallback;
import com.guocai.gclive.view.CircleImageView;

import okhttp3.Call;

/**
 * Create  By xrj ON 2018/9/12 0012
 */
public class AnchorInfoFragment extends BaseFragment{
    private CircleImageView civAvatar;
    private TextView tvAnchorName;
    private TextView tvStartLiveTime;
    private TextView tvEmotionalState;
    private TextView tvOccupation;
    private TextView tvAccount;
    private TextView tvAge;
    private TextView tvLocation;
    private TextView tvRoomNotice;
    @Override
    public View initLayout(LayoutInflater inflater) {
        mView = View.inflate(getContext(), R.layout.anchorinfo_layout, null);
        //取消状态栏
        return mView;
    }

    public static AnchorInfoFragment newInstance() {

        Bundle args = new Bundle();

        AnchorInfoFragment fragment = new AnchorInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initView() {
        civAvatar=mView.findViewById(R.id.civAvatar);
        tvAnchorName= mView.findViewById(R.id.tvAnchorName);
        tvStartLiveTime=mView.findViewById(R.id.tvStartLiveTime);
        tvEmotionalState=mView.findViewById(R.id.tvEmotionalState);
        tvOccupation=mView.findViewById(R.id.tvOccupation);
        tvAccount=mView.findViewById(R.id.tvAccount);
        tvAge=mView.findViewById(R.id.tvAge);
        tvLocation=mView.findViewById(R.id.tvLocation);
        tvRoomNotice=mView.findViewById(R.id.tvRoomNotice);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void initData() {
        sendGift(1+"",2+"");
    }

    @Override
    public void onClickEvent(View v) {

    }

    public void sendGift(final String giftid,String num)
    {
        OkhttpRequest.getRoomMsgRequest(LiveActivity.token,giftid,num, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(getActivity(),"发送失败",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response, int id) {
                Log.i("xiren","打印结果:"+response);
                RoomMessageBean giftSendBean=new Gson().fromJson(response,RoomMessageBean.class);
                if(giftSendBean.getCode()==20000)
                {
                    Glide.with(getActivity()).load(giftSendBean.getResp().getRoomCoverUrl()).into(civAvatar);
                    tvAnchorName.setText(giftSendBean.getResp().getUserNickName());
                    tvStartLiveTime.setText(giftSendBean.getResp().getRoomInfo());
                    tvOccupation.setText(giftSendBean.getResp().getEndPlay());
                    tvEmotionalState.setText(giftSendBean.getResp().getOpenPlay());
                    tvAccount.setText(giftSendBean.getResp().getId()+"");
                    tvAge.setText(giftSendBean.getResp().getAge()+"");
                    tvLocation.setText(giftSendBean.getResp().getUserAddress()+"");
                    tvRoomNotice.setText(giftSendBean.getResp().getTellAll());

                }else
                {
                    Toast.makeText(getActivity(),giftSendBean.getMsg(),Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

}
