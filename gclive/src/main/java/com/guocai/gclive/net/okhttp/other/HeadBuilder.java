package com.guocai.gclive.net.okhttp.other;

/**
 * Created by ${吴心良}
 * on 2017/4/27.
 * description:
 */
public class HeadBuilder extends GetBuilder {
    @Override
    public RequestCall build() {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers, id).build();
    }
}
