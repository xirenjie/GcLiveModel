package com.guocai.gclive.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import com.guocai.gclive.R;
import com.guocai.gclive.adapter.MessageAdapter;
import com.guocai.gclive.entity.Message;
import com.guocai.gclive.entity.MessageEvent;
import com.guocai.gclive.utils.CharUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

/**
 * Create  By xrj ON 2018/9/12 0012
 * 聊天C
 */
public class ChatCFragment extends BaseFragment{

    TextView tvTips;
    private ListView messageList ;

    private MessageAdapter messageAdapter ;
    private ArrayList<Message> messages ;
    private Random mRandom ;
    private Timer mTimer = new Timer();

    @Override
    public View initLayout(LayoutInflater inflater) {
        EventBus.getDefault().register(this);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mView = View.inflate(getContext(), R.layout.chat_c_layout, null);
        //取消状态栏
        initData();
        return mView;
    }
    public static ChatCFragment newInstance() {
        Bundle args = new Bundle();
        ChatCFragment fragment = new ChatCFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void initView() {
        mRandom = new Random();
        messageList = mView.findViewById(R.id.list_message2);
        messageAdapter = new MessageAdapter(getActivity());
        messageList.setAdapter(messageAdapter);
        handler.postDelayed(runnable, 2000);//每5秒执行一次runnable.
        initdata2();
    }

    @Override
    public void setListener() {
    }

    @Override
    public void initData() {

    }

    public void initdata2()
    {
        tvTips=mView.findViewById(R.id.tvTips);
        SpannableStringBuilder ssb = new SpannableStringBuilder();
        //系统通知图片
        ImageSpan imageSpan = new ImageSpan(getActivity(),R.mipmap.img_dm_xttz);
        SpannableString spannableString = new SpannableString("tips");
        spannableString.setSpan(imageSpan,0,spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.append(spannableString);
        //系统通知内容
        ssb.append(getText(R.string.tips_notice_desc));
        tvTips.setText(ssb);


        messages = new ArrayList<>();
        for (int i=0;i<1;i++){
            Message m = new Message();
            m.img = "http://www.ld12.com/upimg358/allimg/c150808/143Y5Q9254240-11513_lit.png" ;
            m.name="系统通知:" ;
            m.level = "管理员" ;
            m.message="欢迎来到直播间" ;
            messages.add(m);
        }
        messageAdapter.setDatas(messages);
    }

    @Override
    public void onClickEvent(View v) {
        switch (v.getId())
        {

        }
    }



    Handler handler=new Handler();
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            if (messages!=null){
                if(messages.size()>100)
                {
                    Log.i("xiren","执行清除");
                    messages.remove(0);
                }
                Message m = new Message();
                m.img = "http://v1.qzone.cc/avatar/201503/06/18/27/54f981200879b698.jpg%21200x200.jpg" ;
                m.name= CharUtils.getRandomString(8) ;
                m.level = "菜鸟1" ;
                m.message= CharUtils.getRandomString(20);
                messages.add(m);
                messageAdapter.notifyDataSetChanged();
                messageList.setSelection(messageAdapter.getCount()-1);
            }
            handler.postDelayed(this, 1000);
        }
    };


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private int randomColor() {
        return Color.rgb(mRandom.nextInt(255), mRandom.nextInt(255), mRandom.nextInt(255));
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        Log.i("xiren","收到传递过来的数据");
        String msg=messageEvent.getMessage();
        if(msg!=null&& !msg.equals(""))
        {
            if (messages!=null){
                Message m = new Message();
                m.img = "http://v1.qzone.cc/avatar/201503/06/18/27/54f981200879b698.jpg%21200x200.jpg" ;
                m.name=messageEvent.getName();
                m.level = messageEvent.getLeave() ;
                m.message= msg;
                messages.add(m);
                messageAdapter.notifyDataSetChanged();
                messageList.setSelection(messageAdapter.getCount()-1);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTimer.cancel();
        handler.removeCallbacks(runnable);
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }




}
