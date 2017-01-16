package com.bwei.everydaystudy.bean;

/**
 * Created by Administrator on 2017/1/15.
 */

public class LoginMessageBean {


    /**
     * status : 200
     * data : {"user_id":"49947","user_name":"15330276178","user_ctime":"1484102511","user_phone":"15330276178","user_email":null,"user_sex":"1","user_big_log":"http://img.dianfu.net/img/20170112/5d5a78348e9b5c2678c4dc47f6dc1d3c.jpg","user_middle_log":"http://img.dianfu.net/img/20170112/5d5a78348e9b5c2678c4dc47f6dc1d3c.jpg","user_small_log":"http://img.dianfu.net/img/20170112/5d5a78348e9b5c2678c4dc47f6dc1d3c.jpg","ischange":"1"}
     * msg : 登录成功
     */

    private int status;
    private DataBean data;
    private String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * user_id : 49947
         * user_name : 15330276178
         * user_ctime : 1484102511
         * user_phone : 15330276178
         * user_email : null
         * user_sex : 1
         * user_big_log : http://img.dianfu.net/img/20170112/5d5a78348e9b5c2678c4dc47f6dc1d3c.jpg
         * user_middle_log : http://img.dianfu.net/img/20170112/5d5a78348e9b5c2678c4dc47f6dc1d3c.jpg
         * user_small_log : http://img.dianfu.net/img/20170112/5d5a78348e9b5c2678c4dc47f6dc1d3c.jpg
         * ischange : 1
         */

        private String user_id;
        private String user_name;
        private String user_ctime;
        private String user_phone;
        private Object user_email;
        private String user_sex;
        private String user_big_log;
        private String user_middle_log;
        private String user_small_log;
        private String ischange;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_ctime() {
            return user_ctime;
        }

        public void setUser_ctime(String user_ctime) {
            this.user_ctime = user_ctime;
        }

        public String getUser_phone() {
            return user_phone;
        }

        public void setUser_phone(String user_phone) {
            this.user_phone = user_phone;
        }

        public Object getUser_email() {
            return user_email;
        }

        public void setUser_email(Object user_email) {
            this.user_email = user_email;
        }

        public String getUser_sex() {
            return user_sex;
        }

        public void setUser_sex(String user_sex) {
            this.user_sex = user_sex;
        }

        public String getUser_big_log() {
            return user_big_log;
        }

        public void setUser_big_log(String user_big_log) {
            this.user_big_log = user_big_log;
        }

        public String getUser_middle_log() {
            return user_middle_log;
        }

        public void setUser_middle_log(String user_middle_log) {
            this.user_middle_log = user_middle_log;
        }

        public String getUser_small_log() {
            return user_small_log;
        }

        public void setUser_small_log(String user_small_log) {
            this.user_small_log = user_small_log;
        }

        public String getIschange() {
            return ischange;
        }

        public void setIschange(String ischange) {
            this.ischange = ischange;
        }
    }
}
