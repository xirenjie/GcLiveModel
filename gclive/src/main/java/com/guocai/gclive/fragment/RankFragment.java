package com.guocai.gclive.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.guocai.gclive.R;
import com.guocai.gclive.activity.LiveActivity;
import com.guocai.gclive.adapter.RankItemAdapter;
import com.guocai.gclive.bean.RoomRankBean;
import com.guocai.gclive.net.OkhttpRequest;
import com.guocai.gclive.net.StringCallback;
import com.guocai.gclive.recycle.OnItemClickListeners;
import com.guocai.gclive.recycle.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

/**
 * Create  By xrj ON 2018/9/12 0012
 * 排行
 */
public class RankFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{
    private TextView rank_text1;
    private TextView rank_text2;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private RankItemAdapter rankItemAdapter;
    List<RoomRankBean.RespBean> ls=new ArrayList<>();
    @Override
    public View initLayout(LayoutInflater inflater) {
        mView = View.inflate(getContext(), R.layout.rank_layout, null);
        //取消状态栏
        return mView;
    }

    public static RankFragment newInstance() {

        Bundle args = new Bundle();

        RankFragment fragment = new RankFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initView() {
        rank_text1=mView.findViewById(R.id.rank_text1);
        rank_text2=mView.findViewById(R.id.rank_text2);
        swipeRefreshLayout=mView.findViewById(R.id.rank_swip);
        recyclerView=mView.findViewById(R.id.rank_recy);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void setListener() {
        rank_text1.setOnClickListener(this);
        rank_text2.setOnClickListener(this);
    }

    @Override
    public void initData() {
        rankItemAdapter = new RankItemAdapter(getActivity(),ls,true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(rankItemAdapter);
        rankItemAdapter.setOnItemClickListener(onItemClickListeners);
        getRoomRank(1+"",2+"");
    }

    OnItemClickListeners onItemClickListeners=new OnItemClickListeners<String>() {
        @Override
        public void onItemClick(ViewHolder viewHolder, String data, int position) {
            //Toast.makeText(DidTicketActivity.this,data,Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onClickEvent(View v) {
        int i = v.getId();
        if (i == R.id.rank_text1) {
            onColorClick(1);
            getRoomRank(1+"",2+"");
        } else if (i == R.id.rank_text2) {
            onColorClick(2);
            getRoomRank(1+"",3+"");
        }
    }


    public void onColorClick(int type)
    {
        if(type==1)
        {
            rank_text1.setBackgroundResource(R.drawable.linear_border_red_bg);
            rank_text1.setTextColor(getResources().getColor(R.color.white));
            rank_text2.setBackgroundColor(0);
            rank_text2.setTextColor(getResources().getColor(R.color.room_lable_color));
        }else
        {
            rank_text2.setBackgroundResource(R.drawable.linear_border_red_bg);
            rank_text2.setTextColor(getResources().getColor(R.color.white));
            rank_text1.setBackgroundColor(0);
            rank_text1.setTextColor(getResources().getColor(R.color.room_lable_color));
        }
    }

    public void getRoomRank(String roomNum,String type)
    {
        OkhttpRequest.getRoomRankRequest(LiveActivity.token,roomNum,type, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(getActivity(),"发送失败",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response, int id) {
                Log.i("xiren","打印结果:"+response);
                RoomRankBean giftSendBean=new Gson().fromJson(response,RoomRankBean.class);
                if(giftSendBean.getCode()==20000)
                {
                    ls=giftSendBean.getResp();
                    rankItemAdapter.setNewData(ls);
                    Log.i("xiren","打印结果:"+ls.size());
                }else
                {
                    Toast.makeText(getActivity(),giftSendBean.getMsg(),Toast.LENGTH_SHORT).show();
                }


            }
        });






    }



    public String stes(int strs)
    {
        String str="";

        if(strs==2000)
        {

        }
        return str;
    }


    @Override
    public void onRefresh() {
        new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                    swipeRefreshLayout.setRefreshing(false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
