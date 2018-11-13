package com.guocai.gclive.bean;

/**
 * Create  By xrj ON 2018/10/31 0031
 */
public class TestBean {

    /**
     * command : 11
     * data : {"chatType":1,"cmd":11,"content":"回家给我看吧","createTime":1540958446171,"from":"18764117706","group_id":"123","id":"87bc7b347663417c94f006ff3d817ade","imgUrl":"http://img4.duitang.com/uploads/item/201410/03/20141003160129_nUfjf.thumb.700_0.jpeg","leave":"彩王2","msgType":1,"nickName":"袭大大"}
     */

    private int command;
    private DataBean data;

    public int getCommand() {
        return command;
    }

    public void setCommand(int command) {
        this.command = command;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * chatType : 1
         * cmd : 11
         * content : 回家给我看吧
         * createTime : 1540958446171
         * from : 18764117706
         * group_id : 123
         * id : 87bc7b347663417c94f006ff3d817ade
         * imgUrl : http://img4.duitang.com/uploads/item/201410/03/20141003160129_nUfjf.thumb.700_0.jpeg
         * leave : 彩王2
         * msgType : 1
         * nickName : 袭大大
         */

        private int chatType;
        private int cmd;
        private String content;
        private long createTime;
        private String from;
        private String group_id;
        private String id;
        private String imgUrl;
        private String leave;
        private int msgType;
        private String nickName;

        public int getChatType() {
            return chatType;
        }

        public void setChatType(int chatType) {
            this.chatType = chatType;
        }

        public int getCmd() {
            return cmd;
        }

        public void setCmd(int cmd) {
            this.cmd = cmd;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getGroup_id() {
            return group_id;
        }

        public void setGroup_id(String group_id) {
            this.group_id = group_id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getLeave() {
            return leave;
        }

        public void setLeave(String leave) {
            this.leave = leave;
        }

        public int getMsgType() {
            return msgType;
        }

        public void setMsgType(int msgType) {
            this.msgType = msgType;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }
    }
}
