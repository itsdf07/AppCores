package com.itsdf07.core.app.ui.activity.dynamic;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Author Aso
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/1/25
 */
public class DynamicCommentReplysBean {

    private List<ReplysBean> replys;

    public List<ReplysBean> getReplys() {
        return replys;
    }

    public void setReplys(List<ReplysBean> replys) {
        this.replys = replys;
    }

    public static class ReplysBean implements Serializable {
        /**
         * user_id : 21644
         * content : 7777777777
         * reply_uid : 0
         * reply_id : 2
         * created_time : 1611060260
         * user_name : RL11
         * user_avatar : http://static.imjk.club/avatar/avatar_1608447649.jpg
         * is_auth : 0
         * is_author : 1
         * reply_avatar :
         * reply_name :
         * like_num : 0
         * is_like : 0
         */

        private int user_id;
        private String content;
        private int reply_uid;
        private int reply_id;
        private long created_time;
        private String user_name;
        private String user_avatar;
        private int is_auth;
        private int is_author;
        private String reply_avatar;
        private String reply_name;
        private int like_num;
        private int is_like;

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

        public int getReply_uid() {
            return reply_uid;
        }

        public void setReply_uid(int reply_uid) {
            this.reply_uid = reply_uid;
        }

        public int getReply_id() {
            return reply_id;
        }

        public void setReply_id(int reply_id) {
            this.reply_id = reply_id;
        }

        public long getCreated_time() {
            return created_time;
        }

        public void setCreated_time(long created_time) {
            this.created_time = created_time;
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

        public int getIs_auth() {
            return is_auth;
        }

        public void setIs_auth(int is_auth) {
            this.is_auth = is_auth;
        }

        public int getIs_author() {
            return is_author;
        }

        public void setIs_author(int is_author) {
            this.is_author = is_author;
        }

        public String getReply_avatar() {
            return reply_avatar;
        }

        public void setReply_avatar(String reply_avatar) {
            this.reply_avatar = reply_avatar;
        }

        public String getReply_name() {
            return reply_name;
        }

        public void setReply_name(String reply_name) {
            this.reply_name = reply_name;
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
