package com.guocai.gclive.net;

import com.guocai.gclive.net.okhttp.HttpUrls;

/**
 * Created by 袭人杰 on 2017/11/29.
 */
public class Config extends HttpUrls {
    //发送礼物
    public static final String LOGIN_URL=HTTP_URL+"gcn/sendgift";
    //获取主播信息
    public static final String ROOM_MSG=HTTP_URL+"gcn/getLiverInfo";
    //获取礼物列表
    public static final String GIFT_LIST=HTTP_URL+"gcn/getGiftList";
    //获取房间礼物排行
    public static final String ROOM_RANK=HTTP_URL+"gcn/getroomrank";

}
