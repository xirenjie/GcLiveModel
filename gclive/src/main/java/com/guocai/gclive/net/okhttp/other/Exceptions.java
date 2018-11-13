package com.guocai.gclive.net.okhttp.other;

/**
 * Created by ${吴心良}
 * on 2017/4/27.
 * description:
 */
public class Exceptions
{
    public static void illegalArgument(String msg, Object... params)
    {
        throw new IllegalArgumentException(String.format(msg, params));
    }

}

