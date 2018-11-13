package com.guocai.gclive.net.okhttp.other;

import java.util.Map;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by ${吴心良}
 * on 2017/4/27.
 * description:
 */

public class GetRequest extends OkHttpRequest {
    public GetRequest(String url, Object tag, Map<String, String> params, Map<String, String> headers, int id) {
        super(url, tag, params, headers, id);
    }

    @Override
    protected RequestBody buildRequestBody() {
        return null;
    }

    @Override
    protected Request buildRequest(RequestBody requestBody) {
        return builder.get().build();
    }


}
