package com.guocai.gclive.activity;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.guocai.gclive.R;
import com.guocai.gclive.bean.GiftListBean;
import com.guocai.gclive.bean.GiftSendBean;
import com.guocai.gclive.bean.TestBean;
import com.guocai.gclive.entity.Gift;
import com.guocai.gclive.entity.GiftEvent;
import com.guocai.gclive.entity.MessageEvent;
import com.guocai.gclive.fragment.AnchorInfoFragment;
import com.guocai.gclive.fragment.ChatCFragment;
import com.guocai.gclive.fragment.FragmentGiftDialog;
import com.guocai.gclive.fragment.RankFragment;
import com.guocai.gclive.fragment.VideoFragment;
import com.guocai.gclive.net.OkhttpRequest;
import com.guocai.gclive.net.StringCallback;
import com.guocai.gclive.utils.DensityUtil;
import com.guocai.gclive.utils.EditHintUtils;
import com.guocai.gclive.utils.LogUtils;
import com.guocai.gclive.view.CommentDialog;
import com.guocai.gclive.view.GiftItemView;
import com.guocai.gclive.view.ViewPagerFragmentAdapter;
import com.xrj.imframework.bean.OnLineUserBean;
import com.xrj.imframework.bean.UserGroupResponseBean;
import com.xrj.imframework.entrance.WsManager;
import com.xrj.imframework.utils.L;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.ui.widget.DanmakuView;
import okhttp3.Call;

/**
 * Create  By xrj ON 2018/9/12 0012
 */
public class LiveActivity extends BaseActivity implements View.OnTouchListener {
    private List<CharSequence> listTitle;
    private List<Fragment> listData;
    private ViewPagerFragmentAdapter viewPagerFragmentAdapter;
    private VideoFragment videoFragment;
    ViewPager viewPager;
    TabLayout tabLayout;
    FrameLayout frameVideo;
    ImageView ivBack;
    ImageView ivFullScreen;
    LinearLayout llRoomChat;
    RelativeLayout videoContent;


    /**
     * 最大声音
     */
    private int mMaxVolume;
    /**
     * 当前声音
     */
    private int mVolume = -1;
    /**
     * 当前亮度
     */
    private float mBrightness = -1f;
    private AudioManager mAudioManager;
    private GestureDetector mGestureDetector;

    private View mVolumeBrightnessLayout;
    private ImageView mOperationBg;
    private ImageView mOperationPercent;

    /**
     * 弹幕
     */
    private DanmakuView mDanmakuView;
    private boolean showDanmaku;
    private DanmakuContext danmakuContext;


    /**
     * 输入框  礼物
     */
    private TextView etChat;
    private View room_bottom_linear;
    private GiftItemView giftView;
    private ArrayList<Gift> gifts;
    private String message;


    /**
     * 上布局
     */
    private RelativeLayout live_top_relate;
    /**
     * 全屏标签
     */
    private ImageView ivGif_screen;
    private ImageView ivEmoji_screen;
    private ImageView ivDanmu_screen;
    private TextView etChat_screen;
    private LinearLayout linear_screen;
    private LinearLayout screen_bottom_linear;
    private LinearLayout live_btm_online_linear;
    private TextView live_btm_online_text;

    private String username = "xirenjie";
    private boolean isbanned = true;

    private OnLineUserBean onLineUserBean;
    public static String token = "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIxODc2NDExNzcwNiIsInVzZXJJZCI6IjMzIiwibmFtZSI6IuiireWkp-WkpyIsImV4cCI6MTU0MjE3NTQ5Mn0.RK4bLgmu4hROLMmLio0zQ5URS3l-CH6DfpBNuTmUPRg3k0kh9klh6xlz_ySltVImyrCqPxl_mY6NJyt59DUIrZkUqVQY1B-NRY0VLWY5XQkIGcKPTScwBalHxnmRbIOl9h2tnePjSDxeTcP8jRKoSHSFxRKeUZyiFmhz-SctgGM";
    private String leave;
    private String total_bean;
    private String avatar;
    private String nickname;
    GiftListBean giftSendBean;
    @Override
    public void setContentLayout() {
        setContentView(R.layout.live_layout);

    }

    @Override
    public void beforeInitView() {

        listTitle = new ArrayList<>();
        listTitle.add(getText(R.string.room_chat));
        listTitle.add(getText(R.string.room_ranking));
        listTitle.add(getText(R.string.room_anchor));
        listData = new ArrayList<>();
        listData.add(ChatCFragment.newInstance());
        listData.add(RankFragment.newInstance());
        listData.add(AnchorInfoFragment.newInstance());
        viewPagerFragmentAdapter = new ViewPagerFragmentAdapter(getSupportFragmentManager(), listData, listTitle);
    }

    @Override
    public void initView() {
        ivBack = findViewById(R.id.ivBack);
        frameVideo = findViewById(R.id.frameVideo);
        llRoomChat = findViewById(R.id.llRoomChat);
        ivFullScreen = findViewById(R.id.ivFullScreen);
        videoContent = findViewById(R.id.videoContent);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager.setAdapter(viewPagerFragmentAdapter);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setOnPageChangeListener(onPageChangeListener);
        tabLayout.setupWithViewPager(viewPager);

        mVolumeBrightnessLayout = findViewById(R.id.operation_volume_brightness);
        mOperationBg = findViewById(R.id.operation_bg);
        mOperationPercent = findViewById(R.id.operation_percent);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        mMaxVolume = mAudioManager
                .getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        mGestureDetector = new GestureDetector(this, new MyGestureListener());

        mDanmakuView = findViewById(R.id.danmaku_view);

        findViewById(R.id.ivDanmu).setOnClickListener(this);
        etChat = findViewById(R.id.etChat);
        findViewById(R.id.ivGif).setOnClickListener(this);
        room_bottom_linear = findViewById(R.id.room_bottom_linear);
        giftView = findViewById(R.id.gift_item_first);

        live_top_relate = findViewById(R.id.live_top_relate);

        ivGif_screen = findViewById(R.id.ivGif_screen);
        ivDanmu_screen = findViewById(R.id.ivDanmu_screen);
        screen_bottom_linear = findViewById(R.id.screen_bottom_linear);
        etChat_screen = findViewById(R.id.etChat_screen);
        linear_screen = findViewById(R.id.linear_screen);
        ivEmoji_screen = findViewById(R.id.ivEmoji_screen);
        live_btm_online_linear = findViewById(R.id.live_btm_online_linear);
        live_btm_online_text = findViewById(R.id.live_btm_online_text);


        //监听由于输入法弹出所致的沉浸问题
        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if (visibility == View.SYSTEM_UI_FLAG_VISIBLE) {
                    onWindowFocusChanged(true);
                }
            }
        });

        initDanmaku();
        updateVideoLayoutParams();
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void afterInitView() {
        Intent intent = getIntent();
        username = intent.getStringExtra("usern");
        String configUrl = "ws://192.168.1.78:8855?username=" + username + "&password=1231237";
        WsManager.getInstance().init(this, configUrl, username, "123");
        filter();

        etChat.setOnClickListener(this);
        ivFullScreen.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        videoContent.setOnClickListener(this);
        videoContent.setOnTouchListener(this);//监听滑动

        ivGif_screen.setOnClickListener(this);
        linear_screen.setOnClickListener(this);
        ivDanmu_screen.setOnClickListener(this);
        etChat_screen.setOnClickListener(this);

        LogUtils.d("playUrl:" + "http://flv15.quanmin.tv/live/6289685_L4.flv");
        String paths="http://flv15.quanmin.tv/live/6289685_L4.flv";
        //String paths="rtmp://rtmp.guocai.com/live/lntv";
        if (videoFragment == null) {
            videoFragment = VideoFragment.newInstance(paths, false);
        }
        replaceChildFragment(R.id.frameVideo, videoFragment);

        gifts = new ArrayList<>();
        aotuVisible();
    }

    @Override
    public void onClickEvent(View v) {
        int i = v.getId();
        if (i == R.id.ivGif_screen) {
            AddImgDialog();

        } else if (i == R.id.etChat_screen) {
            AddTextDialog();

        } else if (i == R.id.ivDanmu_screen) {

        } else if (i == R.id.linear_screen) {

        } else if (i == R.id.videoContent) {
            showHintVisible();
        } else if (i == R.id.frameVideo) {

        } else if (i == R.id.ivBack) {
            clickBack();

        } else if (i == R.id.ivShare) {
        } else if (i == R.id.ivFullScreen) {
            clickFullScreen();

        } else if (i == R.id.tvFollow) {
        } else if (i == R.id.ivDanmu) {
        } else if (i == R.id.etChat) {
            if (isbanned) {
                AddTextDialog();
            }

        } else if (i == R.id.ivGif) {
            getGiftList();


        }
    }

    /**
     * 导航栏隐藏显示
     */
    public void showHintVisible() {
        if (isToolsHide) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //显示状态栏
            live_top_relate.setVisibility(View.VISIBLE);
            screen_bottom_linear.setVisibility(View.VISIBLE);
            isToolsHide = false;
        } else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //隐藏状态栏
            live_top_relate.setVisibility(View.GONE);
            screen_bottom_linear.setVisibility(View.GONE);
            isToolsHide = true;
        }
    }


    Handler handler = new Handler();


    public void aotuVisible() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showHintVisible();
            }
        }, 3000);//3秒后执行Runnable中的run方法
    }


    /**
     * 弹出输入框
     */
    public void AddTextDialog() {
        CommentDialog commentDialog = new CommentDialog(this, new CommentDialog.OnclickCommentListener() {
            @Override
            public void CommentSuccess(String content) {
                Toast.makeText(LiveActivity.this, content, Toast.LENGTH_SHORT).show();
                message = content;
                sendText(message);
            }

            @Override
            public void dialogDismiss() {

            }
        });
        commentDialog.show();
    }

    public void AddImgDialog() {
        FragmentGiftDialog.newInstance(total_bean,giftSendBean).setOnGridViewClickListener(new FragmentGiftDialog.OnGridViewClickListener() {
            @Override
            public void click(Gift gift) {
                sendGift(gift.giftType+ "", gift.num+ "",gift.img);
            }
        }).show(getSupportFragmentManager(), "dialog");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (isLandscape()) {
            llRoomChat.setVisibility(View.GONE);
            // ivFullScreen.setVisibility(View.GONE);
            if (mDanmakuView != null) {
                mDanmakuView.setVisibility(View.VISIBLE);
                generateSomeDanmaku();
            }
        } else {
            llRoomChat.setVisibility(View.VISIBLE);
            //ivFullScreen.setVisibility(View.VISIBLE);
            if (mDanmakuView != null) {
                mDanmakuView.setVisibility(View.GONE);
            }
        }
        updateVideoLayoutParams();

    }

    /**
     * 更新布局高度大小
     */
    public void updateVideoLayoutParams() {
        ViewGroup.LayoutParams lp = videoContent.getLayoutParams();
        if (isLandscape()) {
            lp.height = DensityUtil.getDisplayMetrics(this).heightPixels;
        } else {
            lp.height = (int) (DensityUtil.getDisplayMetrics(this).widthPixels / 16.0f * 9.0f);
        }

        videoContent.setLayoutParams(lp);
    }

    /**
     * 判断横竖屏
     * @return
     */
    public boolean isLandscape() {
        return getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
    }

    /**
     * 点击返回按钮执行顺序
     */
    public void clickBack() {
        if (isLandscape()) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            room_bottom_linear.setVisibility(View.VISIBLE);
            HintScreenVisible();
        } else {
            finish();
        }
    }

    /**
     * 横竖屏切换
     */
    public void clickFullScreen() {
        if (isLandscape()) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            room_bottom_linear.setVisibility(View.VISIBLE);
            HintScreenVisible();
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            room_bottom_linear.setVisibility(View.GONE);
            ShowScreenVisible();
        }
    }

    public void ShowScreenVisible() {
        ivEmoji_screen.setVisibility(View.VISIBLE);
        linear_screen.setVisibility(View.VISIBLE);
        ivGif_screen.setVisibility(View.VISIBLE);
        live_btm_online_linear.setVisibility(View.GONE);
    }

    public void HintScreenVisible() {
        ivEmoji_screen.setVisibility(View.GONE);
        linear_screen.setVisibility(View.GONE);
        ivGif_screen.setVisibility(View.GONE);
        live_btm_online_linear.setVisibility(View.VISIBLE);
    }

    public void replaceChildFragment(@IdRes int id, Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(id, fragment).commit();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.i("xiren", "是否监听到有反应");
        if (mGestureDetector.onTouchEvent(event))
            return true;

        // 处理手势结束
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                endGesture();
                break;
        }
        return super.onTouchEvent(event);
    }


    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        /**
         * 双击
         */
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.i("xiren", "是否监听到双击");
            return true;
        }

        /**
         * 滑动
         */
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {

            Log.i("xiren", "是否监听到滑动");

            float mOldX = e1.getX(), mOldY = e1.getY();
            int y = (int) e2.getRawY();
            Display disp = getWindowManager().getDefaultDisplay();
            int windowWidth = disp.getWidth();
            int windowHeight = disp.getHeight();

            if (mOldX > windowWidth * 4.0 / 5)// 右边滑动
                onVolumeSlide((mOldY - y) / windowHeight);
            else if (mOldX < windowWidth / 5.0)// 左边滑动
                onBrightnessSlide((mOldY - y) / windowHeight);

            return super.onScroll(e1, e2, distanceX, distanceY);
        }

    }

    /**
     * 滑动改变声音大小
     *
     * @param percent
     */
    private void onVolumeSlide(float percent) {
        if (mVolume == -1) {
            mVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            if (mVolume < 0)
                mVolume = 0;

            // 显示
            mOperationBg.setImageResource(R.mipmap.video_volumn_bg);
            mVolumeBrightnessLayout.setVisibility(View.VISIBLE);
        }

        int index = (int) (percent * mMaxVolume) + mVolume;
        if (index > mMaxVolume)
            index = mMaxVolume;
        else if (index < 0)
            index = 0;

        // 变更声音
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, index, 0);
        // 变更进度条
        ViewGroup.LayoutParams lp = mOperationPercent.getLayoutParams();
        lp.width = findViewById(R.id.operation_full).getLayoutParams().width
                * index / mMaxVolume;
        mOperationPercent.setLayoutParams(lp);
    }

    /**
     * 滑动改变亮度
     *
     * @param percent
     */
    private void onBrightnessSlide(float percent) {
        if (mBrightness < 0) {
            mBrightness = getWindow().getAttributes().screenBrightness;
            if (mBrightness <= 0.00f)
                mBrightness = 0.50f;
            if (mBrightness < 0.01f)
                mBrightness = 0.01f;

            // 显示
            mOperationBg.setImageResource(R.mipmap.video_brightness_bg);
            mVolumeBrightnessLayout.setVisibility(View.VISIBLE);
        }
        WindowManager.LayoutParams lpa = getWindow().getAttributes();
        lpa.screenBrightness = mBrightness + percent;
        if (lpa.screenBrightness > 1.0f)
            lpa.screenBrightness = 1.0f;
        else if (lpa.screenBrightness < 0.01f)
            lpa.screenBrightness = 0.01f;
        getWindow().setAttributes(lpa);

        ViewGroup.LayoutParams lp = mOperationPercent.getLayoutParams();
        lp.width = (int) (findViewById(R.id.operation_full).getLayoutParams().width * lpa.screenBrightness);
        mOperationPercent.setLayoutParams(lp);
    }


    /**
     * 定时隐藏
     */
    private Handler mDismissHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mVolumeBrightnessLayout.setVisibility(View.GONE);
        }
    };


    /**
     * 手势结束
     */
    private void endGesture() {
        mVolume = -1;
        mBrightness = -1f;

        // 隐藏
        mDismissHandler.removeMessages(0);
        mDismissHandler.sendEmptyMessageDelayed(0, 500);
    }

    /**
     * 弹幕解析器
     */
    private BaseDanmakuParser parser = new BaseDanmakuParser() {
        @Override
        protected IDanmakus parse() {
            return new Danmakus();
        }
    };

    /**
     * 初始化弹幕组件
     */
    private void initDanmaku() {
        //给弹幕视图设置回调，在准备阶段获取弹幕信息并开始
        mDanmakuView.setCallback(new DrawHandler.Callback() {
            @Override
            public void prepared() {
                showDanmaku = true;
                mDanmakuView.start();

            }

            @Override
            public void updateTimer(DanmakuTimer timer) {

            }

            @Override
            public void danmakuShown(BaseDanmaku danmaku) {

            }

            @Override
            public void drawingFinished() {

            }
        });
        //缓存，提升绘制效率
        mDanmakuView.enableDanmakuDrawingCache(true);
        //DanmakuContext主要用于弹幕样式的设置
        danmakuContext = DanmakuContext.create();
        danmakuContext.setDanmakuStyle(IDisplayer.DANMAKU_STYLE_STROKEN, 3);//描边
        danmakuContext.setDuplicateMergingEnabled(true);//重复合并
        danmakuContext.setScrollSpeedFactor(1.2f);//弹幕滚动速度
        //让弹幕进入准备状态，传入弹幕解析器和样式设置
        mDanmakuView.prepare(parser, danmakuContext);
        //显示fps、时间等调试信息
        mDanmakuView.showFPS(true);
    }

    /**
     * 随机生成一些弹幕内容以供测试
     */
    private void generateSomeDanmaku() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (showDanmaku) {
                    int time = new Random().nextInt(300);
                    String content = "" + time + time;
                    addDanmaku(content, false);
                    try {
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


    /**
     * 向弹幕View中添加一条弹幕
     *
     * @param content    弹幕的具体内容
     * @param withBorder 弹幕是否有边框
     */
    private void addDanmaku(String content, boolean withBorder) {
        //弹幕实例BaseDanmaku,传入参数是弹幕方向
        BaseDanmaku danmaku = danmakuContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);
        danmaku.text = content;
        danmaku.padding = 5;
        danmaku.textSize = sp2px(15);
        danmaku.textColor = Color.WHITE;
        danmaku.setTime(mDanmakuView.getCurrentTime());
        //加边框
        if (withBorder) {
            danmaku.borderColor = Color.GREEN;
        }
        mDanmakuView.addDanmaku(danmaku);
    }

    /**
     * sp转px的方法。
     */
    public int sp2px(float spValue) {
        final float fontScale = getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position == 0) {
                room_bottom_linear.setVisibility(View.VISIBLE);
            } else if (position == 1) {
                room_bottom_linear.setVisibility(View.GONE);
            } else if (position == 2) {
                room_bottom_linear.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mDanmakuView != null && mDanmakuView.isPrepared()) {
            mDanmakuView.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mDanmakuView != null && mDanmakuView.isPrepared() && mDanmakuView.isPaused()) {
            mDanmakuView.resume();
        }
    }

    @Override
    public void onBackPressed() {
        clickBack();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
        WsManager.getInstance().disconnect();
        showDanmaku = false;
        if (mDanmakuView != null) {
            mDanmakuView.release();
            mDanmakuView = null;
        }
    }

    private int TIME_ANIMATION = 500;
    private boolean isToolsHide = false;


    private void filter() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.xrjframework.demo.websocket.data");
        filter.addAction("com.xrjframework.demo.websocket.ondisconnected");
        filter.addAction("com.xrjframework.demo.websocket.onconnecterror");
        filter.addAction("com.xrjframework.demo.websocket.onconnected");
        filter.addAction("com.xrjframework.demo.websocket.onlineuser");
        filter.addAction("com.xrjframework.demo.websocket.quitgroup");
        filter.addAction("com.xrjframework.demo.websocket.joingroup");
        filter.setPriority(Integer.MAX_VALUE);
        registerReceiver(myReceiver, filter);
    }

    /*
     * 广播接收
     */
    private BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String aciton = intent.getAction();
            if (aciton.equals("com.xrjframework.demo.websocket.data")) {
                String mass = intent.getStringExtra("data");
                try {
                    JSONObject jsonObject = new JSONObject(mass);
                    int content = jsonObject.getInt("command");
                    if (content == 11) {
                        TestBean liveBean = new Gson().fromJson(mass, TestBean.class);
                        switch (liveBean.getData().getMsgType()) {
                            case 1://全体消息
                                Log.i("onTextMessage", liveBean.getData().getNickName());
                                receiveText(liveBean.getData().getNickName(), liveBean.getData().getContent(), liveBean.getData().getLeave());
                                if (isLandscape()) {
                                    addDanmaku(liveBean.getData().getContent(), false);
                                }
                                break;
                            case 2://礼物消息
                                receiveGift(liveBean.getData().getNickName(), liveBean.getData().getContent(),liveBean.getData().getImgUrl());
                                break;
                            case 3://全体禁言
                                Toast.makeText(LiveActivity.this, "群体禁言", Toast.LENGTH_SHORT).show();
                                if (isbanned) {
                                    isbanned = false;
                                    etChat.setText("您已被禁言");
                                } else {
                                    etChat.setText("和主播说点什么...");
                                    isbanned = true;
                                }
                                break;
                            case 4://私聊消息
                                receiveText(liveBean.getData().getNickName(), liveBean.getData().getContent(), liveBean.getData().getLeave());
                                break;
                            case 5://个人禁言
                                Toast.makeText(LiveActivity.this, "私人禁言", Toast.LENGTH_SHORT).show();
                                if (isbanned) {
                                    isbanned = false;
                                    etChat.setText("您已被禁言");
                                } else {
                                    etChat.setText("和主播说点什么...");
                                    isbanned = true;
                                }
                                break;
                        }
                    }
                } catch (JSONException e) {
                    L.i("数据解析异常" + e.toString());
                }
            }
            if (aciton.equals("com.xrjframework.demo.websocket.ondisconnected")) {
                Toast.makeText(LiveActivity.this, "当前连接已断开", Toast.LENGTH_SHORT).show();
                finish();
            }
            if (aciton.equals("com.xrjframework.demo.websocket.onconnecterror")) {
                Toast.makeText(LiveActivity.this, "连接错误", Toast.LENGTH_SHORT).show();
            }
            if (aciton.equals("com.xrjframework.demo.websocket.onconnected")) {
                Toast.makeText(LiveActivity.this, "已连接", Toast.LENGTH_SHORT).show();
                //WsManager.getInstance().OnlineSend();
            }
            if (aciton.equals("com.xrjframework.demo.websocket.onlineuser"))//获取在线用户数
            {
                String mass = intent.getStringExtra("data");
                onLineUserBean = new Gson().fromJson(mass, OnLineUserBean.class);
                onlineUser(onLineUserBean);
            }
            if (aciton.equals("com.xrjframework.demo.websocket.quitgroup"))//退出群组
            {
                //WsManager.getInstance().OnlineSend();
            }
            if (aciton.equals("com.xrjframework.demo.websocket.joingroup"))//加入群组
            {
                String mass = intent.getStringExtra("data");
                UserGroupResponseBean userGroupResponseBean = new Gson().fromJson(mass, UserGroupResponseBean.class);
                getUserMsg(userGroupResponseBean);
                WsManager.getInstance().OnlineSend();
            }
        }
    };
    private boolean isfirst = true;

    /**
     * 获取在线用户数
     *
     * @param onLineUserBean
     */
    private void onlineUser(OnLineUserBean onLineUserBean) {
        Log.i("onLineUserBean", "当前用户数" + onLineUserBean.getData().size());
        live_btm_online_text.setText(onLineUserBean.getData().size() + "");
    }

    /**
     * 获取用户信息
     *
     * @param userGroupResponseBean
     */
    private void getUserMsg(UserGroupResponseBean userGroupResponseBean) {
        if (isfirst) {
            leave = userGroupResponseBean.getData().getUser().getLeave();
            total_bean = userGroupResponseBean.getData().getUser().getTotal_beans() + "";
            avatar = userGroupResponseBean.getData().getUser().getAvatar();
            nickname = userGroupResponseBean.getData().getUser().getNick();
            isfirst = false;
        }
    }

    /**
     * 接收消息
     *
     * @param msg
     */
    private void receiveText(String nick, String msg, String leave) {
        EventBus.getDefault().post(new MessageEvent(msg, nick, leave));
    }

    /**
     * 接收礼物
     */
    private void receiveGift(String name, String gifttype,String userimg) {

        String s3 = gifttype;
        String [] temp = null;
        temp = s3.split("_");
        String gifttp=temp[0];
        String nums=temp[1];
        String giftimgs=null;

        Gift gift = new Gift();
        gift.giftType = Integer.valueOf(gifttp).intValue();
        gift.num = 0;
        gift.name = name;
        if(giftSendBean!=null)
        {
            for(int i=0;i<giftSendBean.getResp().size();i++)
            {
                if(giftSendBean.getResp().get(i).getGiftId().equals(gifttp))
                {
                    giftimgs=giftSendBean.getResp().get(i).getImgUrl();
                }
            }
        }
        gift.giftimg=giftimgs;
        gift.giftName = "送你一个小礼物";
        gift.img=userimg;
        if (!gifts.contains(gift)) {
            gifts.add(gift);
            giftView.setGift(gift);
        }
        giftView.addNum(Integer.valueOf(nums).intValue());
    }

    /**
     * 发送消息
     */
    private void sendText(String content) {
        if (!content.isEmpty()) {

            WsManager.getInstance().sendGrouptext(content, username, getTime(), 1 + "", avatar, leave, nickname);
        } else
            EditHintUtils.hideKeyboard(this);
    }



    public String getTime() {
        long time = System.currentTimeMillis();//获取系统时间的10位的时间戳
        String str = String.valueOf(time);
        return str;
    }


    /**
     * 隐藏工具栏竖屏
     */
    private void hinttools() {
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(ivFullScreen, "y", ivFullScreen.getY(),
                ivFullScreen.getY() + ivFullScreen.getHeight() + DensityUtil.dp2px(this, 10));
        anim4.setDuration(TIME_ANIMATION);
        anim4.start();
        isToolsHide = true;
    }

    /**
     * 显示工具栏竖屏
     */
    private void sowtools() {
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(ivFullScreen, "y",
                ivFullScreen.getY(), ivFullScreen.getY() - ivFullScreen.getHeight() - DensityUtil.dp2px(this, 10));
        anim2.setDuration(TIME_ANIMATION);
        anim2.start();
        isToolsHide = false;
    }

    /**
     * 隐藏工具栏top
     */
    private void hinttoolsTop() {
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(room_bottom_linear, "y", 0,
                room_bottom_linear.getX() - room_bottom_linear.getHeight());
        anim3.setDuration(TIME_ANIMATION);
        anim3.start();
        isToolsHide = false;
    }


    private void sowtoolstop() {
        Log.i("xiren", "    " + room_bottom_linear.getX() + "    " + room_bottom_linear.getY() + "    " + room_bottom_linear.getHeight());
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(room_bottom_linear, "y", 0,
                room_bottom_linear.getY() + room_bottom_linear.getHeight());
        anim1.setDuration(TIME_ANIMATION);
        anim1.start();
        isToolsHide = true;
    }


//   private void showTools() {
//  
//        ObjectAnimator anim = ObjectAnimator.ofFloat(img_tools, "y", img_tools.getY(),  
//                                img_tools.getY() - img_tools.getHeight());  
//        anim.setDuration(TIME_ANIMATION);  
//        anim.start();  
//  
//        isToolsHide = false;  
//    }  
//              
//                /** 
//          * 隐藏工具栏 
//          */  
//    private void hideTools() {  
//  
//        ObjectAnimator anim = ObjectAnimator.ofFloat(img_tools, "y", img_tools.getY(),  
//                                img_tools.getY() + img_tools.getHeight());  
//        anim.setDuration(TIME_ANIMATION);  
//        anim.start();  
//  
//        isToolsHide = true;  
//  
//    }  


    /**
     * 如果触摸事件下有控件点击事件，则重写下面方法
     *
     * @return
     */
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if(mGestureDetector.onTouchEvent(ev)){
//            return mGestureDetector.onTouchEvent(ev);
//        }
//
//        // 处理手势结束
//        switch (ev.getAction() & MotionEvent.ACTION_MASK) {
//            case MotionEvent.ACTION_UP:
//                endGesture();
//                break;
//        }
//
//        return super.dispatchTouchEvent(ev);
//    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        Log.i("xiren","是否监听到有反应");
//        if (mGestureDetector.onTouchEvent(event))
//            return true;
//
//        // 处理手势结束
//        switch (event.getAction() & MotionEvent.ACTION_MASK) {
//            case MotionEvent.ACTION_UP:
//                endGesture();
//                break;
//        }
//
//        return super.onTouchEvent(event);
//    }
    public void sendGift(final String giftid,final String num,final String img) {
        Log.i("xiren", num+"礼物数量和种类:" + giftid);
        OkhttpRequest.sendGiftRequest(token, giftid, num, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(LiveActivity.this, "发送失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response, int id) {
                Log.i("xiren", "打印结果:" + response);
                GiftSendBean giftSendBean = new Gson().fromJson(response, GiftSendBean.class);
                if (giftSendBean.getCode() == 20000) {
                    total_bean=giftSendBean.getResp().getTotal_bean()+"";
                    EventBus.getDefault().post(new GiftEvent(total_bean));
                    String content=giftid+"_"+num;
                    WsManager.getInstance().sendGrouptext(content, username, getTime(), 2 + "", avatar, leave, nickname);
                } else {
                    Toast.makeText(LiveActivity.this, giftSendBean.getMsg(), Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    /**
     * 获取礼物列表
     */
    public void getGiftList()
    {
        OkhttpRequest.getGiftListRequest(token, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(LiveActivity.this, "发送失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response, int id) {
                Log.i("xiren", "打印结果:" + response);
                giftSendBean = new Gson().fromJson(response, GiftListBean.class);
                if (giftSendBean.getCode() == 20000) {
                    AddImgDialog();
                } else {
                    Toast.makeText(LiveActivity.this, giftSendBean.getMsg(), Toast.LENGTH_SHORT).show();
                }


            }
        });
    }


    private void setFullScreens(boolean enable) {
        if (enable) {
            WindowManager.LayoutParams attrs = getWindow().getAttributes();
            attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            getWindow().setAttributes(attrs);
        } else {
            WindowManager.LayoutParams attrs = getWindow().getAttributes();
            attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
            getWindow().setAttributes(attrs);
        }
    }

}
