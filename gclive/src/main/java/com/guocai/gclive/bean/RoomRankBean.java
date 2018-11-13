package com.guocai.gclive.bean;

import java.util.List;

/**
 * Create  By xrj ON 2018/10/30 0030
 */
public class RoomRankBean {

    /**
     * code : 20000
     * msg : 查询成功
     * resp : [{"useBean":485120,"nickName":"袭大大","icon":"http://img4.duitang.com/uploads/item/201410/03/20141003160129_nUfjf.thumb.700_0.jpeg","id":33},{"useBean":13330,"nickName":null,"icon":null,"id":45}]
     */

    private int code;
    private String msg;
    private List<RespBean> resp;

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

    public List<RespBean> getResp() {
        return resp;
    }

    public void setResp(List<RespBean> resp) {
        this.resp = resp;
    }

    public static class RespBean {
        /**
         * useBean : 485120
         * nickName : 袭大大
         * icon : http://img4.duitang.com/uploads/item/201410/03/20141003160129_nUfjf.thumb.700_0.jpeg
         * id : 33
         */

        private int useBean;
        private String nickName;
        private String icon;
        private int id;
        private String exp;

        public String getExp() {
            return exp;
        }

        public void setExp(String exp) {
            this.exp = exp;
        }

        public int getUseBean() {
            return useBean;
        }

        public void setUseBean(int useBean) {
            this.useBean = useBean;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
