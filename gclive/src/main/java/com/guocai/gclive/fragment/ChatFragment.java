package com.guocai.gclive.fragment;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.guocai.gclive.R;
import com.guocai.gclive.entity.Gift;

import java.util.ArrayList;

/**
 * Create  By xrj ON 2018/9/12 0012
 * 聊天
 */
public class ChatFragment extends BaseFragment {

    TextView tvTips;
    ImageView ivEmoji;
    EditText etChat;
    ImageView ivDanmu;
    ImageView ivGif;


    private ArrayList<Gift> gifts ;
    @Override
    public View initLayout(LayoutInflater inflater) {
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mView = View.inflate(getContext(), R.layout.chat_layout, null);
        //取消状态栏
        return mView;
    }
    public static ChatFragment newInstance() {
        Bundle args = new Bundle();
        ChatFragment fragment = new ChatFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void initView() {
        tvTips=mView.findViewById(R.id.tvTips);
        ivGif=mView.findViewById(R.id.ivGif);

    }

    @Override
    public void setListener() {
        gifts = new ArrayList<>();
        ivGif.setOnClickListener(this);
    }

    @Override
    public void initData() {

        SpannableStringBuilder ssb = new SpannableStringBuilder();
        //系统通知图片
        ImageSpan imageSpan = new ImageSpan(getActivity(),R.mipmap.img_dm_xttz);
        SpannableString spannableString = new SpannableString("tips");
        spannableString.setSpan(imageSpan,0,spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.append(spannableString);
        //系统通知内容
        ssb.append(getText(R.string.tips_notice_desc));
        tvTips.setText(ssb);
    }

    @Override
    public void onClickEvent(View v) {
        int i = v.getId();
        if (i == R.id.ivGif) {
//            FragmentGiftDialog.newInstance().setOnGridViewClickListener(new FragmentGiftDialog.OnGridViewClickListener() {
//                @Override
//                public void click(Gift gift) {
////                        gift.name="文人骚客";
////                        gift.giftName = "送你一个小礼物" ;
////                        if (!gifts.contains(gift)){
////                            gifts.add(gift);
////                            giftView.setGift(gift);
////                        }
////                        giftView.addNum(1);
//                }
//            }).show(getChildFragmentManager(), "dialog");
//
        }
    }
}
