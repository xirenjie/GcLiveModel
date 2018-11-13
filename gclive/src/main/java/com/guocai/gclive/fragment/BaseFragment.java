package com.guocai.gclive.fragment;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guocai.gclive.BaseApplication;


@SuppressLint("NewApi")
public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    //    protected BaseActivity mActivity;
    protected Activity mActivity;
    public Context mContext;
    protected LayoutInflater inflater;
    // mContent:当前的Fragment public Fragment mContent;
    protected View mView;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 所依赖的activity
        mActivity = getActivity();
        mContext = BaseApplication.getContext();

        // 初始化控件
//        initView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 初始化布局
        mView = initLayout(inflater);
        // 初始化控件
        initView();
        // 设置监听器
        setListener();

        // 初始化数据   /*联网请求时调用此方法进行初始化数据来填充数据*/
        initData();
        // 初始化U_id

        return mView;
    }


    public abstract View initLayout(LayoutInflater inflater);

    public abstract void initView();

    public abstract void setListener();

    public abstract void initData();

    /**
     * onClick方法的封装
     */
    public abstract void onClickEvent(View v);

    @Override
    public void onClick(View v) {
        onClickEvent(v);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = getActivity();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

