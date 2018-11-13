package com.guocai.gclive.net.okhttp.other;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * author: 霍彦朋 (dell) .
 * date: 2017/9/21.
 * function:
 */
public class UrlHttps {
    public static  String streamToString(InputStream is) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            baos.close();
            is.close();
            byte[] byteArray = baos.toByteArray();
            return new String(byteArray);
        } catch (Exception e) {

            return null;
        }
    }

}
