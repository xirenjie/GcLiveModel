package com.guocai.gclive.net.okhttp.other;

import java.util.Map;

/**
 * Created by ${吴心良}
 * on 2017/4/27.
 * description:
 */

public interface HasParamsable {
    OkHttpRequestBuilder params(Map<String, String> params);

    OkHttpRequestBuilder addParams(String key, String val);
}