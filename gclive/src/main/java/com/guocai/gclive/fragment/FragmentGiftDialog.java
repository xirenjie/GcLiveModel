package com.guocai.gclive.fragment;

import android.app.Dialog;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.guocai.gclive.R;
import com.guocai.gclive.adapter.GiftGridViewAdapter;
import com.guocai.gclive.bean.GiftListBean;
import com.guocai.gclive.entity.Gift;
import com.guocai.gclive.entity.GiftEvent;
import com.guocai.gclive.utils.DensityUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Create  By xrj ON 2018/9/13 0013
 */
public class FragmentGiftDialog extends DialogFragment implements View.OnClickListener{
    private Dialog dialog ;
    private ViewPager vp ;
    private List<View> gridViews;
    private LayoutInflater layoutInflater;
    private ArrayList<Gift> catogarys;
    private String[] catogary_names;
    private int[] catogary_resourceIds;
    private RadioGroup radio_group ;

    private TextView common_gift_num;
    private TextView common_gift_send;
    private TextView common_gift_bean_num;
    private LinearLayout comment_num_linear;
    Gift gifts;
    int position;//选择礼物数量
    static String total_bean;//彩豆数量
    private ImageView comment_check_img;
    static GiftListBean giftSendBean;
    View rootView;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        rootView = LayoutInflater.from(getActivity()).inflate(R.layout.common_gift_dialog_layout, null, false);
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        initDialogStyle(rootView);
        initView(rootView);
        return dialog;

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(GiftEvent messageEvent) {
        total_bean=messageEvent.getMessage();
        common_gift_bean_num.setText(" "+total_bean);
    }

    private void initView(View rootView) {
        common_gift_num=rootView.findViewById(R.id.common_gift_num);
        comment_check_img=rootView.findViewById(R.id.comment_check_img);
        common_gift_send=rootView.findViewById(R.id.common_gift_send);
        comment_num_linear=rootView.findViewById(R.id.comment_num_linear);
        common_gift_bean_num=rootView.findViewById(R.id.common_gift_bean_num);
        common_gift_bean_num.setText(" "+total_bean);
        common_gift_send.setOnClickListener(this);
        common_gift_num.setOnClickListener(this);

        Bundle args=getArguments();
        if(args==null)
            return;
        initdata(giftSendBean);
    }

    public void initdata(GiftListBean giftListBean)
    {
//        catogary_names = getResources().getStringArray(R.array.catogary_names);
//        TypedArray typedArray = getResources().obtainTypedArray(R.array.catogary_resourceIds);
//        catogary_resourceIds = new int[typedArray.length()];
//        for (int i = 0; i < typedArray.length(); i++) {
//            catogary_resourceIds[i] = typedArray.getResourceId(i, 0);
//        }
        layoutInflater = getActivity().getLayoutInflater();
        vp =  rootView.findViewById(R.id.view_pager);
        radio_group =  rootView.findViewById(R.id.radio_group);
        RadioButton radioButton = (RadioButton)radio_group.getChildAt(0);
        radioButton.setChecked(true);
        catogarys = new ArrayList<>();
        for (int i = 0; i < giftListBean.getResp().size(); i++) {
            Gift catogary = new Gift();
            catogary.name = giftListBean.getResp().get(i).getGiftMoney()+"";
            catogary.giftType = Integer.valueOf(giftListBean.getResp().get(i).getGiftId()).intValue();
            catogary.giftName = giftListBean.getResp().get(i).getGiftName();
            catogary.img = giftListBean.getResp().get(i).getImgUrl();
            catogarys.add(catogary);
        }
        initViewPager();
    }

    public void initViewPager() {
        gridViews = new ArrayList<>();
        ///定义第一个GridView
        GridView gridView1 =
                (GridView) layoutInflater.inflate(R.layout.grid_fragment_home, null);
        GiftGridViewAdapter myGridViewAdapter1 = new GiftGridViewAdapter(getActivity(),0, 8);
        gridView1.setAdapter(myGridViewAdapter1);
        myGridViewAdapter1.setGifts(catogarys);
        myGridViewAdapter1.setOnGridViewClickListener(new GiftGridViewAdapter.OnGridViewClickListener() {
            @Override
            public void click(Gift gift) {
                ObtainGift(gift);
            }
        });
        ///定义第二个GridView
        GridView gridView2 = (GridView)
                layoutInflater.inflate(R.layout.grid_fragment_home, null);
        GiftGridViewAdapter myGridViewAdapter2 = new GiftGridViewAdapter(getActivity(),1, 8);
        gridView2.setAdapter(myGridViewAdapter2);
        myGridViewAdapter2.setGifts(catogarys);
        myGridViewAdapter2.setOnGridViewClickListener(new GiftGridViewAdapter.OnGridViewClickListener() {
            @Override
            public void click(Gift gift) {
                ObtainGift(gift);
            }
        });
        ///定义第三个GridView
        GridView gridView3 = (GridView)
                layoutInflater.inflate(R.layout.grid_fragment_home, null);
        GiftGridViewAdapter myGridViewAdapter3 = new GiftGridViewAdapter(getActivity(),2, 8);
        gridView3.setAdapter(myGridViewAdapter3);
        myGridViewAdapter3.setGifts(catogarys);
        myGridViewAdapter3.setOnGridViewClickListener(new GiftGridViewAdapter.OnGridViewClickListener() {
            @Override
            public void click(Gift gift) {
                ObtainGift(gift);
            }
        });
        gridViews.add(gridView1);
        gridViews.add(gridView2);
        gridViews.add(gridView3);

        ///定义viewpager的PagerAdapter
        vp.setAdapter(new PagerAdapter() {
            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                // TODO Auto-generated method stub
                return arg0 == arg1;
            }
            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return gridViews.size();
            }
            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                // TODO Auto-generated method stub
                container.removeView(gridViews.get(position));
                //super.destroyItem(container, position, object);
            }
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                // TODO Auto-generated method stub
                container.addView(gridViews.get(position));
                return gridViews.get(position);
            }
        });
        ///注册viewPager页选择变化时的响应事件
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrollStateChanged(int position) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageSelected(int position) {
                RadioButton radioButton = (RadioButton)
                        radio_group.getChildAt(position);
                radioButton.setChecked(true);
            }
        });
    }
    public static final FragmentGiftDialog newInstance(String totalbean,GiftListBean giftbean){
        total_bean=totalbean;
        giftSendBean=giftbean;
        Log.i("xiren","礼物列表数量"+giftSendBean.getResp().size());
        FragmentGiftDialog fragment = new FragmentGiftDialog();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }
    public  OnGridViewClickListener onGridViewClickListener;

    public FragmentGiftDialog setOnGridViewClickListener(OnGridViewClickListener onGridViewClickListener) {
        this.onGridViewClickListener = onGridViewClickListener;
        return this ;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if(i==R.id.common_gift_num)
        {
            showPopWindow(comment_num_linear);
        }else if(i==R.id.common_gift_send)
        {
            if(gifts==null)
            {
                Toast.makeText(getActivity(),"请选择要发送的礼物",Toast.LENGTH_SHORT).show();
                return;
            }
            position=Integer.valueOf(common_gift_num.getText().toString().trim());
            if(position!=0)
            {
                Log.i("xiren","选定结果值"+position);
                gifts.num=position;
                if (onGridViewClickListener!=null){
                    onGridViewClickListener.click(gifts);
                }
            }

        }else if(i==R.id.pop1)
        {
            common_gift_num.setText("1314");
            mPopWindow1.dismiss();

        }else if(i==R.id.pop2)
        {
            common_gift_num.setText("520");
            mPopWindow1.dismiss();
        }else if(i==R.id.pop3)
        {
            common_gift_num.setText("188");
            mPopWindow1.dismiss();
        }else if(i==R.id.pop4)
        {
            common_gift_num.setText("66");
            mPopWindow1.dismiss();
        }else if(i==R.id.pop5)
        {
            common_gift_num.setText("30");
            mPopWindow1.dismiss();
        }else if(i==R.id.pop6)
        {
            common_gift_num.setText("10");
            mPopWindow1.dismiss();
        }else if(i==R.id.pop7)
        {
            common_gift_num.setText("1");
            mPopWindow1.dismiss();
        }
    }

    /**
     * 获取礼物信息
     */
    public void ObtainGift(Gift gift)
    {
        comment_check_img.setImageResource(gift.giftType);
        gifts=new Gift();
        gifts=gift;
    }

    public interface OnGridViewClickListener{
        void click(Gift gift);
    }
    private void initDialogStyle(View view) {
        dialog = new Dialog(getActivity(), R.style.CustomGiftDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true); // 外部点击取消
        // 设置宽度为屏宽, 靠近屏幕底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        window.setAttributes(lp);
    }

    PopupWindow mPopWindow1;
    /**
     * 弹出选择数量框
     */
    public void showPopWindow(View v)
    {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_num_layout, null);
        //我这里给的是固定高度  固定高度需要在外边设置  在布局中设置外边不设置是没有效果的
        mPopWindow1 = new PopupWindow(contentView, DensityUtil.dp2px(getActivity(),130), ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopWindow1.setBackgroundDrawable(new BitmapDrawable());
        // 设置弹窗外可点击
        mPopWindow1.setOutsideTouchable(true);
        mPopWindow1.setFocusable(true);// 加上这个popupwindow中的子项才可以接收点击事件
        mPopWindow1.setWidth( DensityUtil.dp2px(getActivity(),130));
        mPopWindow1.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopWindow1.setContentView(contentView);
        //获取自身的长宽高
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int  popupHeight = contentView.getMeasuredHeight();
        int  popupWidth = contentView.getMeasuredWidth();
        //获取需要在其上方显示的控件的位置信息
//        int[] location = new int[2];
//        v.getLocationOnScreen(location);
//        //在控件上方显示    向上移动y轴是负数
//        mPopWindow1.showAtLocation(v, Gravity.NO_GRAVITY, location[0] , location[1] - popupHeight);

        //得到button的左上角坐标
        int[] positions = new int[2];
        v.getLocationOnScreen(positions);
        mPopWindow1.showAtLocation(v, Gravity.NO_GRAVITY, positions[0], -positions[1] - mPopWindow1.getHeight()-30);
        RelativeLayout re1=contentView.findViewById(R.id.pop1);
        RelativeLayout re2=contentView.findViewById(R.id.pop2);
        RelativeLayout re3=contentView.findViewById(R.id.pop3);
        RelativeLayout re4=contentView.findViewById(R.id.pop4);
        RelativeLayout re5=contentView.findViewById(R.id.pop5);
        RelativeLayout re6=contentView.findViewById(R.id.pop6);
        RelativeLayout re7=contentView.findViewById(R.id.pop7);
        re1.setOnClickListener(this);
        re2.setOnClickListener(this);
        re3.setOnClickListener(this);
        re4.setOnClickListener(this);
        re5.setOnClickListener(this);
        re6.setOnClickListener(this);
        re7.setOnClickListener(this);
    }






    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();

    }
}
