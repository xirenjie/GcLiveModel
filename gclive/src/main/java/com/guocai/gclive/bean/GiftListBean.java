package com.guocai.gclive.bean;

import java.util.List;

/**
 * Create  By xrj ON 2018/10/30 0030
 */
public class GiftListBean {

    /**
     * code : 20000
     * msg : 获取完成
     * resp : [{"id":1,"giftId":"2131492893","giftName":"鲜花","giftMoney":1,"imgUrl":"http://59.110.26.16:8082/gcnAPP/2018-10-30/43fb204c1daa4712b0a9d1d26b3f1930.png"},{"id":2,"giftId":"2131492894","giftName":"棒棒糖","giftMoney":1,"imgUrl":"http://59.110.26.16:8082/gcnAPP/2018-10-30/2456a81a81084368a44c6cc727ae18a5.png"},{"id":3,"giftId":"2131492895","giftName":"蝴蝶","giftMoney":1,"imgUrl":"http://59.110.26.16:8082/gcnAPP/2018-10-30/259b7b60ebbd41319bf78dbca409be95.png"},{"id":4,"giftId":"2131492896","giftName":"花环","giftMoney":2,"imgUrl":"http://59.110.26.16:8082/gcnAPP/2018-10-30/e08dd86d5ead43709640b3d6b8247bfa.png"},{"id":5,"giftId":"2131492897","giftName":"皇冠","giftMoney":4,"imgUrl":"http://59.110.26.16:8082/gcnAPP/2018-10-30/3e94e0ed80b943d09cfb73907050035a.png"},{"id":6,"giftId":"2131492898","giftName":"钻戒","giftMoney":6,"imgUrl":"http://59.110.26.16:8082/gcnAPP/2018-10-30/87d17aa31b364e62b538271f11b6ee66.png"},{"id":7,"giftId":"2131492899","giftName":"魔法镜","giftMoney":8,"imgUrl":"http://59.110.26.16:8082/gcnAPP/2018-10-30/ec329d979de842c29ad92d3c307f72e0.png"},{"id":8,"giftId":"2131492900","giftName":"蝴蝶结","giftMoney":10,"imgUrl":"http://59.110.26.16:8082/gcnAPP/2018-10-30/f92a49b32a7a43c2b2b4db6e99a493dd.png"},{"id":9,"giftId":"2131492901","giftName":"领结","giftMoney":20,"imgUrl":"http://59.110.26.16:8082/gcnAPP/2018-10-30/dc1e55752bbc453ba379e028b3ae726a.png"},{"id":10,"giftId":"2131492902","giftName":"话筒","giftMoney":30,"imgUrl":"http://59.110.26.16:8082/gcnAPP/2018-10-30/aa43120cfa2348ef929f5c89ef2f6811.png"},{"id":11,"giftId":"2131492903","giftName":"帽子","giftMoney":40,"imgUrl":"http://59.110.26.16:8082/gcnAPP/2018-10-30/96abb1cddee548dbbde716c56d7b252f.png"},{"id":12,"giftId":"2131492904","giftName":"天马","giftMoney":80,"imgUrl":"http://59.110.26.16:8082/gcnAPP/2018-10-30/063940c5aae7473b8c0a8ed466058840.png"},{"id":13,"giftId":"2131492905","giftName":"气球","giftMoney":100,"imgUrl":"http://59.110.26.16:8082/gcnAPP/2018-10-30/6022891ea16c4c869ffc5ad24ae58a28.png"},{"id":14,"giftId":"2131492906","giftName":"四叶草","giftMoney":500,"imgUrl":"http://59.110.26.16:8082/gcnAPP/2018-10-30/6d2de9e0f8244f13946f5188b8d2545d.png"},{"id":15,"giftId":"2131492907","giftName":"太阳","giftMoney":666,"imgUrl":"http://59.110.26.16:8082/gcnAPP/2018-10-30/070efc1254134df89c51c0f2a2b5668d.png"},{"id":16,"giftId":"2131492908","giftName":"兔耳朵","giftMoney":888,"imgUrl":"http://59.110.26.16:8082/gcnAPP/2018-10-30/800400bf69c0417297d7320ced2f0afe.png"},{"id":17,"giftId":"2131492909","giftName":"项链","giftMoney":999,"imgUrl":"http://59.110.26.16:8082/gcnAPP/2018-10-30/326dce62393f42f28b85ceff0ddfc057.png"},{"id":18,"giftId":"2131492910","giftName":"爱心","giftMoney":1333,"imgUrl":"http://59.110.26.16:8082/gcnAPP/2018-10-30/e8c0677df2624f799f01e4e07ff76ab6.png"},{"id":19,"giftId":"2131492911","giftName":"情书","giftMoney":2000,"imgUrl":"http://59.110.26.16:8082/gcnAPP/2018-10-30/ac779b6b3fe1454b8a317a8d29d1746a.png"},{"id":20,"giftId":"2131492912","giftName":"星星","giftMoney":5000,"imgUrl":"http://59.110.26.16:8082/gcnAPP/2018-10-30/c9164657016c40fa8902caf788c0477c.png"},{"id":21,"giftId":"2131492913","giftName":"魔法棒","giftMoney":6666,"imgUrl":"http://59.110.26.16:8082/gcnAPP/2018-10-30/a4add8d1379748c9963b306ce25c3a03.png"},{"id":22,"giftId":"2131492914","giftName":"小黄鸭","giftMoney":8888,"imgUrl":"http://59.110.26.16:8082/gcnAPP/2018-10-30/66995b5bad3e45d393cfcffbe330eb6d.png"},{"id":23,"giftId":"2131492915","giftName":"啤酒","giftMoney":9999,"imgUrl":"http://59.110.26.16:8082/gcnAPP/2018-10-30/4d76afe2709842238965956518f99ecd.png"},{"id":24,"giftId":"2131492916","giftName":"红唇","giftMoney":10000,"imgUrl":"http://59.110.26.16:8082/gcnAPP/2018-10-30/181b1d6ef2ae455fb3ee1324486465de.png"}]
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
         * id : 1
         * giftId : 2131492893
         * giftName : 鲜花
         * giftMoney : 1
         * imgUrl : http://59.110.26.16:8082/gcnAPP/2018-10-30/43fb204c1daa4712b0a9d1d26b3f1930.png
         */

        private int id;
        private String giftId;
        private String giftName;
        private int giftMoney;
        private String imgUrl;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGiftId() {
            return giftId;
        }

        public void setGiftId(String giftId) {
            this.giftId = giftId;
        }

        public String getGiftName() {
            return giftName;
        }

        public void setGiftName(String giftName) {
            this.giftName = giftName;
        }

        public int getGiftMoney() {
            return giftMoney;
        }

        public void setGiftMoney(int giftMoney) {
            this.giftMoney = giftMoney;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }
    }
}
