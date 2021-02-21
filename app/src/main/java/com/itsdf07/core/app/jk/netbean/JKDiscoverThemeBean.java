package com.itsdf07.core.app.jk.netbean;

import com.itsdf07.core.app.ui.fragment.home.bean.JKBaseRespBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * @Description: 动态列表数据结构
 * @Author Aso
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/1/26
 */
public class JKDiscoverThemeBean extends JKBaseRespBean {

    /**
     * data : {"tags":[{"t_id":2,"tag":"最新"}],"show_type":1,"themes":[{"id":6,"user_id":21644,"content":"#格饼练习生 发布内容发布内容","user_name":"RL11","user_avatar":"http://static.imjk.club/avatar/avatar_1608447649.jpg","img":"https://static.imjk.club/template/1609927938986.png?1035_1035","view_num":0,"like_num":0,"is_like":0}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * tags : [{"t_id":2,"tag":"最新"}]
         * show_type : 1
         * themes : [{"id":6,"user_id":21644,"content":"#格饼练习生 发布内容发布内容","user_name":"RL11","user_avatar":"http://static.imjk.club/avatar/avatar_1608447649.jpg","img":"https://static.imjk.club/template/1609927938986.png?1035_1035","view_num":0,"like_num":0,"is_like":0}]
         */

        private int show_type;
        private List<TagsBean> tags = new ArrayList<>();
        private List<ThemesBean> themes = new ArrayList<>();

        public int getShow_type() {
            return show_type;
        }

        public void setShow_type(int show_type) {
            this.show_type = show_type;
        }

        public List<TagsBean> getTags() {
            return tags;
        }

        public void setTags(List<TagsBean> tags) {
            this.tags = tags;
        }

        public List<ThemesBean> getThemes() {
            return themes;
        }

        public void setThemes(List<ThemesBean> themes) {
            this.themes = themes;
        }

        public static class TagsBean implements Serializable {
            /**
             * t_id : 2
             * tag : 最新
             */

            private int t_id;
            private String tag;

            public int getT_id() {
                return t_id;
            }

            public void setT_id(int t_id) {
                this.t_id = t_id;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }
        }

        public static class ThemesBean implements Serializable {
            /**
             * id : 6
             * user_id : 21644
             * content : #格饼练习生 发布内容发布内容
             * user_name : RL11
             * user_avatar : http://static.imjk.club/avatar/avatar_1608447649.jpg
             * img : https://static.imjk.club/template/1609927938986.png?1035_1035
             * view_num : 0
             * like_num : 0
             * is_like : 0
             */

            private int id;
            private int user_id;
            private String content;
            private String user_name;
            private String user_avatar;
            private String img;
            private int view_num;
            private int like_num;
            private int is_like;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getUser_avatar() {
                return user_avatar;
            }

            public void setUser_avatar(String user_avatar) {
                this.user_avatar = user_avatar;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getView_num() {
                return view_num;
            }

            public void setView_num(int view_num) {
                this.view_num = view_num;
            }

            public int getLike_num() {
                return like_num;
            }

            public void setLike_num(int like_num) {
                this.like_num = like_num;
            }

            public int getIs_like() {
                return is_like;
            }

            public void setIs_like(int is_like) {
                this.is_like = is_like;
            }
        }
    }
}
