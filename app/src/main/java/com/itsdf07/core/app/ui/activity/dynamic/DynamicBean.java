package com.itsdf07.core.app.ui.activity.dynamic;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/1/24
 */
public class DynamicBean implements Serializable {
    /**
     * is_author : 0
     * is_like : 0
     * like_num : 2
     * like_users : [{"name":"111","avatar":"http://thirdqq.qlogo.cn/g?b=oidb&k=pgHzDqgGZwFxf2iaptq2xYQ&s=100&t=1590772613"},{"name":"RL11","avatar":"http://static.imjk.club/avatar/avatar_1608447649.jpg"}]
     * author_relation : 0
     * author_id : 21644
     * author_name : RL11
     * author_avatar : http://static.imjk.club/avatar/avatar_1608447649.jpg
     * theme_content : #格饼练习生 发布内容发布内容
     * theme_imgs : [{"t_id":3,"img":"https://static.imjk.club/template/1609927938986.png?1035_1035"},{"t_id":0,"img":"https://static.imjk.club/template/1609927938986.png?1035_1035"}]
     * theme_time : 1610620073
     */

    private int is_author;
    private int is_like;
    private int like_num;
    private int author_relation;
    private int author_id;
    private String author_name;
    private String author_avatar;
    private String theme_content;
    private long theme_time;
    private List<LikeUsersBean> like_users;
    private List<ThemeImgsBean> theme_imgs;

    public int getIs_author() {
        return is_author;
    }

    public void setIs_author(int is_author) {
        this.is_author = is_author;
    }

    public int getIs_like() {
        return is_like;
    }

    public void setIs_like(int is_like) {
        this.is_like = is_like;
    }

    public int getLike_num() {
        return like_num;
    }

    public void setLike_num(int like_num) {
        this.like_num = like_num;
    }

    public int getAuthor_relation() {
        return author_relation;
    }

    public void setAuthor_relation(int author_relation) {
        this.author_relation = author_relation;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getAuthor_avatar() {
        return author_avatar;
    }

    public void setAuthor_avatar(String author_avatar) {
        this.author_avatar = author_avatar;
    }

    public String getTheme_content() {
        return theme_content;
    }

    public void setTheme_content(String theme_content) {
        this.theme_content = theme_content;
    }

    public long getTheme_time() {
        return theme_time;
    }

    public void setTheme_time(long theme_time) {
        this.theme_time = theme_time;
    }

    public List<LikeUsersBean> getLike_users() {
        return like_users;
    }

    public void setLike_users(List<LikeUsersBean> like_users) {
        this.like_users = like_users;
    }

    public List<ThemeImgsBean> getTheme_imgs() {
        return theme_imgs;
    }

    public void setTheme_imgs(List<ThemeImgsBean> theme_imgs) {
        this.theme_imgs = theme_imgs;
    }

    public static class LikeUsersBean implements Serializable {
        /**
         * name : 111
         * avatar : http://thirdqq.qlogo.cn/g?b=oidb&k=pgHzDqgGZwFxf2iaptq2xYQ&s=100&t=1590772613
         */

        private String name;
        private String avatar;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }

    public static class ThemeImgsBean implements Serializable {
        /**
         * t_id : 3
         * img : https://static.imjk.club/template/1609927938986.png?1035_1035
         */

        private int t_id;
        private String img;

        public int getT_id() {
            return t_id;
        }

        public void setT_id(int t_id) {
            this.t_id = t_id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
