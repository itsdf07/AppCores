package com.itsdf07.core.app.ui.activity.dynamic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author Aso
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/1/25
 */
public class DynamicCommentsBean {

    private List<CommentsBean> comments;

    public List<CommentsBean> getComments() {
        return comments;
    }

    public void setComments(List<CommentsBean> comments) {
        this.comments = comments;
    }

    public static class CommentsBean implements Serializable {
        /**
         * user_id : 21644
         * content : 666666666666666666
         * reply_num : 2
         * comment_id : 1
         * created_time : 1611058861
         * user_name : RL11
         * user_avatar : http://static.imjk.club/avatar/avatar_1608447649.jpg
         * is_auth : 0
         * is_author : 1
         * like_num : 0
         * is_like : 0
         */

        private int user_id;
        private String content;
        private int reply_num;
        private int comment_id;
        private long created_time;
        private String user_name;
        private String user_avatar;
        private int is_auth;
        private int is_author;
        private int like_num;
        private int is_like;
        /**
         * 评论对应的回复信息
         */
        private ArrayList<DynamicCommentReplysBean.ReplysBean> replys = new ArrayList<>();

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

        public int getReply_num() {
            return reply_num;
        }

        public void setReply_num(int reply_num) {
            this.reply_num = reply_num;
        }

        public int getComment_id() {
            return comment_id;
        }

        public void setComment_id(int comment_id) {
            this.comment_id = comment_id;
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

        public ArrayList<DynamicCommentReplysBean.ReplysBean> getReplys() {
            return replys;
        }

        public void setReplys(ArrayList<DynamicCommentReplysBean.ReplysBean> replys) {
            this.replys = replys;
        }
    }
}
