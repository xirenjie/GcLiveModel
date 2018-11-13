package com.guocai.gclive.bean;

/**
 * Create  By xrj ON 2018/10/30 0030
 */
public class RoomMessageBean {


    /**
     * code : 20000
     * msg : 查询成功
     * resp : {"roomCoverUrl":"http://thirdqq.qlogo.cn/qqapp/1107777885/57711CAF3AAEDAFBE9494835C05F2F0F/100","userAddress":null,"totalTime":0,"openPlay":"09:00:00","id":1,"userNickName":"新人主播求关注","roomTitle":"新人主播求鲜花","userId":1,"endPlay":"18:00:00","age":null,"roomInfo":"欢迎大家来看我的直播"}
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
         * roomCoverUrl : http://thirdqq.qlogo.cn/qqapp/1107777885/57711CAF3AAEDAFBE9494835C05F2F0F/100
         * userAddress : null
         * totalTime : 0.0
         * openPlay : 09:00:00
         * id : 1
         * userNickName : 新人主播求关注
         * roomTitle : 新人主播求鲜花
         * userId : 1
         * endPlay : 18:00:00
         * age : null
         * roomInfo : 欢迎大家来看我的直播
         */

        private String roomCoverUrl;
        private Object userAddress;
        private double totalTime;
        private String openPlay;
        private int id;
        private String userNickName;
        private String roomTitle;
        private int userId;
        private String endPlay;
        private Object age;
        private String roomInfo;
        private String tellAll;

        public String getTellAll() {
            return tellAll;
        }

        public void setTellAll(String tellAll) {
            this.tellAll = tellAll;
        }

        public String getRoomCoverUrl() {
            return roomCoverUrl;
        }

        public void setRoomCoverUrl(String roomCoverUrl) {
            this.roomCoverUrl = roomCoverUrl;
        }

        public Object getUserAddress() {
            return userAddress;
        }

        public void setUserAddress(Object userAddress) {
            this.userAddress = userAddress;
        }

        public double getTotalTime() {
            return totalTime;
        }

        public void setTotalTime(double totalTime) {
            this.totalTime = totalTime;
        }

        public String getOpenPlay() {
            return openPlay;
        }

        public void setOpenPlay(String openPlay) {
            this.openPlay = openPlay;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserNickName() {
            return userNickName;
        }

        public void setUserNickName(String userNickName) {
            this.userNickName = userNickName;
        }

        public String getRoomTitle() {
            return roomTitle;
        }

        public void setRoomTitle(String roomTitle) {
            this.roomTitle = roomTitle;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getEndPlay() {
            return endPlay;
        }

        public void setEndPlay(String endPlay) {
            this.endPlay = endPlay;
        }

        public Object getAge() {
            return age;
        }

        public void setAge(Object age) {
            this.age = age;
        }

        public String getRoomInfo() {
            return roomInfo;
        }

        public void setRoomInfo(String roomInfo) {
            this.roomInfo = roomInfo;
        }
    }
}
