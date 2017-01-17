package com.bwei.everydaystudy.bean;

import java.io.Serializable;
import java.util.List;

/**
 * author by LiKe on 2017/1/15.
 */

public class HotTitlesBean implements Serializable{

    /**
     * code : 200
     * data : [{"ctime":"1466753392","hottopic":"0","ishot":"1","isrecommend":"1","name":"星座","recommend_sort":"2","sort":"2","status":"1","tid":"77"},{"ctime":"1467117856","hottopic":"0","ishot":"1","isrecommend":"1","name":"健身","recommend_sort":"1","sort":"1","status":"1","tid":"98"},{"ctime":"1467201978","hottopic":"0","ishot":"1","isrecommend":"1","name":"穿搭","recommend_sort":"1","sort":"1","status":"1","tid":"110"},{"ctime":"1467203112","hottopic":"0","ishot":"1","isrecommend":"1","name":"吐槽","recommend_sort":"1","sort":"1","status":"1","tid":"111"},{"ctime":"1466753300","hottopic":"0","ishot":"1","isrecommend":"1","name":"美妆","recommend_sort":"1","sort":"1","status":"1","tid":"76"},{"ctime":"1466753543","hottopic":"0","ishot":"1","isrecommend":"1","name":"冷知识","recommend_sort":"0","sort":"0","status":"1","tid":"86"},{"ctime":"1466753448","hottopic":"0","ishot":"1","isrecommend":"1","name":"手作","recommend_sort":"0","sort":"0","status":"1","tid":"79"},{"ctime":"1467354890","hottopic":"0","ishot":"0","isrecommend":"1","name":"音乐","recommend_sort":"0","sort":"0","status":"1","tid":"68"},{"ctime":"1466753589","hottopic":"0","ishot":"1","isrecommend":"1","name":"摄影","recommend_sort":"0","sort":"0","status":"1","tid":"89"},{"ctime":"1466753463","hottopic":"0","ishot":"1","isrecommend":"1","name":"烘焙","recommend_sort":"0","sort":"0","status":"1","tid":"80"}]
     * msg : 请求成功
     */

    public int code;
    public String msg;
    public List<DataBean> data;

    public static class DataBean implements Serializable{
        public DataBean(String ctime, String hottopic, String ishot, String isrecommend, String name, String recommend_sort, String sort, String status, String tid) {
            this.ctime = ctime;
            this.hottopic = hottopic;
            this.ishot = ishot;
            this.isrecommend = isrecommend;
            this.name = name;
            this.recommend_sort = recommend_sort;
            this.sort = sort;
            this.status = status;
            this.tid = tid;
        }

        /**
         * ctime : 1466753392
         * hottopic : 0
         * ishot : 1
         * isrecommend : 1
         * name : 星座
         * recommend_sort : 2
         * sort : 2
         * status : 1
         * tid : 77
         */



        public String ctime;
        public String hottopic;
        public String ishot;
        public String isrecommend;
        public String name;
        public String recommend_sort;
        public String sort;
        public String status;
        public String tid;
    }
}
