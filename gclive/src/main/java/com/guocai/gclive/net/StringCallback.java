package com.guocai.gclive.net;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by ${吴心良}
 * on 2017/4/27.
 * description:
 */

public abstract class  StringCallback extends Callback<String> {
    @Override
    public String parseNetworkResponse(Response response, int id) throws IOException {
        return response.body().string();
    }
}
