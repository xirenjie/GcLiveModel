package com.guocai.gclive.net;

import com.guocai.gclive.net.okhttp.OkHttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 袭人杰 on 2017/11/29.
 */
public class OkhttpRequest extends Config{
    /**
     * 发送礼物请求
     * @param callback
     */
    public static void sendGiftRequest(String token,String giftid,String giftnum,StringCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("giftid",giftid);
        map.put("giftnum",giftnum);
        map.put("touser","1");
        OkHttpUtil.getInstance(token,LOGIN_URL).post(map, callback);
    }

    /**
     * 查询主播信息
     * @param token
     * @param room
     * @param type
     * @param callback
     */
    public static void getRoomMsgRequest(String token,String room,String type,StringCallback callback)
    {
        Map<String,String> map=new HashMap<>();
        map.put("room",room);
        map.put("type",type);
        OkHttpUtil.getInstance(token,ROOM_MSG).post(map, callback);
    }

    /**
     * 获取礼物列表
     * @param token
     * @param callback
     */
    public static void getGiftListRequest(String token,StringCallback callback)
    {
        Map<String,String> map=new HashMap<>();
        OkHttpUtil.getInstance(token,GIFT_LIST).post(map, callback);
    }

    /**
     * 获取房间礼物排行
     * @param token
     * @param roomNum
     * @param type
     * @param callback
     */
    public static void getRoomRankRequest(String token,String roomNum,String type,StringCallback callback)
    {
        Map<String,String> map=new HashMap<>();
        map.put("roomNum",roomNum);
        map.put("type",type);
        OkHttpUtil.getInstance(token,ROOM_RANK).post(map, callback);
    }

}
