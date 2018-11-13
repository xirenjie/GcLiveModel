package com.guocai.gclive.net.okhttp;

import android.util.Log;

import com.guocai.gclive.net.Callback;
import com.guocai.gclive.net.okhttp.other.GetBuilder;
import com.guocai.gclive.net.okhttp.other.OkHttpUtils;
import com.guocai.gclive.net.okhttp.other.PostFormBuilder;

import java.io.File;
import java.util.Map;

import okhttp3.MediaType;

/**
 * create by MrGongB at 2017/2/14 0014
 * 网络提交
 */
public class OkHttpUtil {
    private static OkHttpUtil instance;
    private static String httpUrl;
    private static String token;
    public static OkHttpUtil getInstance(String tokens, String url) {
        if (instance == null) {
            instance = new OkHttpUtil();
        }
        httpUrl = url;
        token=tokens;
        Log.e("xiren",url);
        return instance;
    }

    /**
     * get方法提交
     *
     * @param params
     * @param callback
     */
    public void get(Map<String, String> params, Callback callback) {
        GetBuilder builder = OkHttpUtils.get().url(httpUrl).addHeader("","");
        for (String key : params.keySet()) {
            builder.addParams(key, params.get(key));
        }
        builder.build().execute(callback);
    }

    /**
     * 螃蟹
     * @param callback
     */
    public void get_two(Map<String, String> params, Callback callback) {
        GetBuilder builder = OkHttpUtils.get().url(httpUrl);
//        for (String key : params.keySet()) {
//            builder.addParams(key, params.get(key));
//        }
        builder.build().execute(callback);
    }

    public void get1( Callback callback){
        GetBuilder builder = OkHttpUtils.get().url(httpUrl);
        builder.build().execute(callback);
    }

    /**
     * 普通post提交
     */
    public void post(Map<String, String> params, Callback callback) {
        PostFormBuilder postBuilder = OkHttpUtils.post().url(httpUrl).addHeader("Authorization",token);
        for (String key : params.keySet()) {
            postBuilder.addParams(key, params.get(key));
        }
        postBuilder.build().execute(callback);
    }

    /**
     * post表单提交文件，包括图片
     *
     * @param params   参数
     * @param files    文件
     * @param callback 回调方法
     */
    public void postFile(Map<String, String> params, Map<String, File> files, Callback callback) {
        PostFormBuilder builder = OkHttpUtils.post().url(httpUrl);
        for (String key : params.keySet()) {
            builder.addParams(key, params.get(key));
        }
        for (String key : files.keySet()) {
            File file = files.get(key);
            builder.addFile(key, file.getName(), file);
        }
        builder.build().execute(callback);
    }

    /**
     * post提交Json格式数据
     *
     * @param jsonStr  json字符串
     * @param callback 回调
     */
    public void postJson(String jsonStr, Callback callback) {
        OkHttpUtils
                .postString()
                .url(httpUrl)
                .content(jsonStr)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(callback);
    }

}
