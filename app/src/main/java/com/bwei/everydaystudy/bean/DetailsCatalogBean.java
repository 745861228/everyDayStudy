package com.bwei.everydaystudy.bean;

import java.util.List;

/**
 * Created by qwe on 2017/1/17.
 */

public class DetailsCatalogBean {


    /**
     * code : 200
     * data : [{"id":"24","step_name":"C4D手机广告实战案例","step_course_id":"17","step_order":"1","nodes":[{"seid":"52","sections_name":"C4D手机广告实战案例01_C4D手机广告实战案列三维部分_1","sections_chid":"24","sections_des":"视频主要讲解【C4D材质和克隆工具运用】","sections_isfree":"2","sections_sort":"1","vtime":1047935},{"seid":"53","sections_name":"C4D手机广告实战案例01_C4D手机广告实战案列三维部分_2","sections_chid":"24","sections_des":"视频主要讲解【C4D融球动画案例制作】","sections_isfree":"2","sections_sort":"2","vtime":1036672},{"seid":"54","sections_name":"C4D手机广告实战案例01_C4D手机广告实战案列三维部分_3","sections_chid":"24","sections_des":"视频主要讲解【C4D光线材质和夜景气氛片头学习】","sections_isfree":"2","sections_sort":"3","vtime":1047935},{"seid":"55","sections_name":"C4D手机广告实战案例02_C4D手机广告实战案列三维部分_1","sections_chid":"24","sections_des":"视频主要讲解【C4D新闻地球和布尔运算的学习】","sections_isfree":"2","sections_sort":"4","vtime":1169867},{"seid":"56","sections_name":"C4D手机广告实战案例02_C4D手机广告实战案列三维部分_2","sections_chid":"24","sections_des":"视频主要讲解【C4D大场景把控的学习】","sections_isfree":"2","sections_sort":"5","vtime":903867},{"seid":"57","sections_name":"C4D手机广告实战案例03_C4D手机广告实战案例后期部分_1","sections_chid":"24","sections_des":"视频主要讲解【C4D综合运用学习】","sections_isfree":"2","sections_sort":"6","vtime":896383},{"seid":"58","sections_name":"C4D手机广告实战案例03_C4D手机广告实战案例后期部分_2","sections_chid":"24","sections_des":"视频主要讲解【C4D案例IPHOEN 4S广告】","sections_isfree":"2","sections_sort":"7","vtime":1160575}]}]
     * msg :
     * course_name : C4D手机广告实战案例
     */

    private int code;
    private String msg;
    private String course_name;
    private List<DataEntity> data;

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

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public static class DataEntity {
        /**
         * id : 24
         * step_name : C4D手机广告实战案例
         * step_course_id : 17
         * step_order : 1
         * nodes : [{"seid":"52","sections_name":"C4D手机广告实战案例01_C4D手机广告实战案列三维部分_1","sections_chid":"24","sections_des":"视频主要讲解【C4D材质和克隆工具运用】","sections_isfree":"2","sections_sort":"1","vtime":1047935},{"seid":"53","sections_name":"C4D手机广告实战案例01_C4D手机广告实战案列三维部分_2","sections_chid":"24","sections_des":"视频主要讲解【C4D融球动画案例制作】","sections_isfree":"2","sections_sort":"2","vtime":1036672},{"seid":"54","sections_name":"C4D手机广告实战案例01_C4D手机广告实战案列三维部分_3","sections_chid":"24","sections_des":"视频主要讲解【C4D光线材质和夜景气氛片头学习】","sections_isfree":"2","sections_sort":"3","vtime":1047935},{"seid":"55","sections_name":"C4D手机广告实战案例02_C4D手机广告实战案列三维部分_1","sections_chid":"24","sections_des":"视频主要讲解【C4D新闻地球和布尔运算的学习】","sections_isfree":"2","sections_sort":"4","vtime":1169867},{"seid":"56","sections_name":"C4D手机广告实战案例02_C4D手机广告实战案列三维部分_2","sections_chid":"24","sections_des":"视频主要讲解【C4D大场景把控的学习】","sections_isfree":"2","sections_sort":"5","vtime":903867},{"seid":"57","sections_name":"C4D手机广告实战案例03_C4D手机广告实战案例后期部分_1","sections_chid":"24","sections_des":"视频主要讲解【C4D综合运用学习】","sections_isfree":"2","sections_sort":"6","vtime":896383},{"seid":"58","sections_name":"C4D手机广告实战案例03_C4D手机广告实战案例后期部分_2","sections_chid":"24","sections_des":"视频主要讲解【C4D案例IPHOEN 4S广告】","sections_isfree":"2","sections_sort":"7","vtime":1160575}]
         */

        private String id;
        private String step_name;
        private String step_course_id;
        private String step_order;
        private List<NodesEntity> nodes;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStep_name() {
            return step_name;
        }

        public void setStep_name(String step_name) {
            this.step_name = step_name;
        }

        public String getStep_course_id() {
            return step_course_id;
        }

        public void setStep_course_id(String step_course_id) {
            this.step_course_id = step_course_id;
        }

        public String getStep_order() {
            return step_order;
        }

        public void setStep_order(String step_order) {
            this.step_order = step_order;
        }

        public List<NodesEntity> getNodes() {
            return nodes;
        }

        public void setNodes(List<NodesEntity> nodes) {
            this.nodes = nodes;
        }

        public static class NodesEntity {
            /**
             * seid : 52
             * sections_name : C4D手机广告实战案例01_C4D手机广告实战案列三维部分_1
             * sections_chid : 24
             * sections_des : 视频主要讲解【C4D材质和克隆工具运用】
             * sections_isfree : 2
             * sections_sort : 1
             * vtime : 1047935
             */

            private String seid;
            private String sections_name;
            private String sections_chid;
            private String sections_des;
            private String sections_isfree;
            private String sections_sort;
            private int vtime;

            public String getSeid() {
                return seid;
            }

            public void setSeid(String seid) {
                this.seid = seid;
            }

            public String getSections_name() {
                return sections_name;
            }

            public void setSections_name(String sections_name) {
                this.sections_name = sections_name;
            }

            public String getSections_chid() {
                return sections_chid;
            }

            public void setSections_chid(String sections_chid) {
                this.sections_chid = sections_chid;
            }

            public String getSections_des() {
                return sections_des;
            }

            public void setSections_des(String sections_des) {
                this.sections_des = sections_des;
            }

            public String getSections_isfree() {
                return sections_isfree;
            }

            public void setSections_isfree(String sections_isfree) {
                this.sections_isfree = sections_isfree;
            }

            public String getSections_sort() {
                return sections_sort;
            }

            public void setSections_sort(String sections_sort) {
                this.sections_sort = sections_sort;
            }

            public int getVtime() {
                return vtime;
            }

            public void setVtime(int vtime) {
                this.vtime = vtime;
            }
        }
    }
}
