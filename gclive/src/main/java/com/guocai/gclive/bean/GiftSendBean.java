package com.guocai.gclive.bean;

/**
 * Create  By xrj ON 2018/10/30 0030
 */
public class GiftSendBean {

    /**
     * code : 20000
     * msg : 发送成功
     * resp : {"level":"彩师3","total_bean":9799989}
     */

    private int code;
    private String msg;
    private RespBean resp;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public RespBean getResp() {
        return resp;
    }

    public void setResp(RespBean resp) {
        this.resp = resp;
    }

    public static class RespBean {
        /**
         * level : 彩师3
         * total_bean : 9799989.0
         */

        private String level;
        private double total_bean;

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public double getTotal_bean() {
            return total_bean;
        }

        public void setTotal_bean(double total_bean) {
            this.total_bean = total_bean;
        }
    }
}
