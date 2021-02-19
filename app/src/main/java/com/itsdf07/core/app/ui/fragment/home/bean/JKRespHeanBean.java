package com.itsdf07.core.app.ui.fragment.home.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/1/20
 */
public class JKRespHeanBean extends JKBaseRespBean {

    /**
     * data : {"banners":[{"id":1,"img":"http://static.imjk.club/banner/1606383859810.png?501_240","url":"imjk://app/navigate?params=JTdCJTIydGFyZ2V0JTIyOiUyMmNyZWF0ZWdyaWQlMjIsJTIyb3B0aW9ucyUyMjolN0IlN0QlN0Q=","is_login":0}],"activitys":[{"id":24,"img":"https://static.imjk.club/banner/1611235917679.png?1035_684","url":"https://static.imjk.club/operate/school_uniform/index2.html?nav_alpha=0&simple_left=0","is_login":0,"broadcasts":[{"name":"","avatar":"","msg":""}]}],"eggs":[{"id":14,"title":"样机","img":"https://static.imjk.club/banner/1611467894833.png?156_156","url":"https://www.imjk.club/web/test/02/#/prototype?nav_alpha=0&simple_left=0&canUse=false","is_login":0}],"topic_title":"热门话题","turns":[{"id":15,"img":"https://static.imjk.club/banner/1609477316017.png?1035_330","url":"taobao://shop593468610.taobao.com","is_login":0}],"blocks":[{"id":16,"title":"教程","img":"http://static.imjk.club/banner/1608192263874.png?156_156","url":"https://www.imjk.club/web/test/02/#/tutorials?nav_alpha=2","is_login":0}],"topics":[{"name":"格饼练习生3","icon":"https://static.imjk.club/banner/1609840631401.png?108_108","tag":"浏览","num":"0"}]}
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
         * banners : [{"id":1,"img":"http://static.imjk.club/banner/1606383859810.png?501_240","url":"imjk://app/navigate?params=JTdCJTIydGFyZ2V0JTIyOiUyMmNyZWF0ZWdyaWQlMjIsJTIyb3B0aW9ucyUyMjolN0IlN0QlN0Q=","is_login":0}]
         * activitys : [{"id":24,"img":"https://static.imjk.club/banner/1611235917679.png?1035_684","url":"https://static.imjk.club/operate/school_uniform/index2.html?nav_alpha=0&simple_left=0","is_login":0,"broadcasts":[{"name":"","avatar":"","msg":""}]}]
         * eggs : [{"id":14,"title":"样机","img":"https://static.imjk.club/banner/1611467894833.png?156_156","url":"https://www.imjk.club/web/test/02/#/prototype?nav_alpha=0&simple_left=0&canUse=false","is_login":0}]
         * topic_title : 热门话题
         * turns : [{"id":15,"img":"https://static.imjk.club/banner/1609477316017.png?1035_330","url":"taobao://shop593468610.taobao.com","is_login":0}]
         * blocks : [{"id":16,"title":"教程","img":"http://static.imjk.club/banner/1608192263874.png?156_156","url":"https://www.imjk.club/web/test/02/#/tutorials?nav_alpha=2","is_login":0}]
         * topics : [{"name":"格饼练习生3","icon":"https://static.imjk.club/banner/1609840631401.png?108_108","tag":"浏览","num":"0"}]
         */

        private String topic_title;
        private List<BannersBean> banners = new ArrayList();
        private List<ActivitysBean> activitys = new ArrayList();
        private List<EggsBean> eggs = new ArrayList();
        private List<TurnsBean> turns = new ArrayList();
        private List<BlocksBean> blocks = new ArrayList();
        private List<TopicsBean> topics = new ArrayList();

        public String getTopic_title() {
            return topic_title;
        }

        public void setTopic_title(String topic_title) {
            this.topic_title = topic_title;
        }

        public List<BannersBean> getBanners() {
            return banners;
        }

        public void setBanners(List<BannersBean> banners) {
            this.banners = banners;
        }

        public List<ActivitysBean> getActivitys() {
            return activitys;
        }

        public void setActivitys(List<ActivitysBean> activitys) {
            this.activitys = activitys;
        }

        public List<EggsBean> getEggs() {
            return eggs;
        }

        public void setEggs(List<EggsBean> eggs) {
            this.eggs = eggs;
        }

        public List<TurnsBean> getTurns() {
            return turns;
        }

        public void setTurns(List<TurnsBean> turns) {
            this.turns = turns;
        }

        public List<BlocksBean> getBlocks() {
            return blocks;
        }

        public void setBlocks(List<BlocksBean> blocks) {
            this.blocks = blocks;
        }

        public List<TopicsBean> getTopics() {
            return topics;
        }

        public void setTopics(List<TopicsBean> topics) {
            this.topics = topics;
        }

        public static class BannersBean implements Serializable {
            /**
             * id : 1
             * img : http://static.imjk.club/banner/1606383859810.png?501_240
             * url : imjk://app/navigate?params=JTdCJTIydGFyZ2V0JTIyOiUyMmNyZWF0ZWdyaWQlMjIsJTIyb3B0aW9ucyUyMjolN0IlN0QlN0Q=
             * is_login : 0
             */

            private int id;
            private String img;
            private String url;
            private int is_login;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getIs_login() {
                return is_login;
            }

            public void setIs_login(int is_login) {
                this.is_login = is_login;
            }
        }

        public static class ActivitysBean implements Serializable {
            /**
             * id : 24
             * img : https://static.imjk.club/banner/1611235917679.png?1035_684
             * url : https://static.imjk.club/operate/school_uniform/index2.html?nav_alpha=0&simple_left=0
             * is_login : 0
             * broadcasts : [{"name":"","avatar":"","msg":""}]
             */

            private int id;
            private String img;
            private String url;
            private int is_login;
            private List<BroadcastsBean> broadcasts = new ArrayList<>();

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getIs_login() {
                return is_login;
            }

            public void setIs_login(int is_login) {
                this.is_login = is_login;
            }

            public List<BroadcastsBean> getBroadcasts() {
                return broadcasts;
            }

            public void setBroadcasts(List<BroadcastsBean> broadcasts) {
                this.broadcasts = broadcasts;
            }

            public static class BroadcastsBean implements Serializable {
                /**
                 * name :
                 * avatar :
                 * msg :
                 */

                private String name;
                private String avatar;
                private String msg;

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

                public String getMsg() {
                    return msg;
                }

                public void setMsg(String msg) {
                    this.msg = msg;
                }
            }
        }

        public static class EggsBean implements Serializable {
            /**
             * id : 14
             * title : 样机
             * img : https://static.imjk.club/banner/1611467894833.png?156_156
             * url : https://www.imjk.club/web/test/02/#/prototype?nav_alpha=0&simple_left=0&canUse=false
             * is_login : 0
             */

            private int id;
            private String title;
            private String img;
            private String url;
            private int is_login;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getIs_login() {
                return is_login;
            }

            public void setIs_login(int is_login) {
                this.is_login = is_login;
            }
        }

        public static class TurnsBean implements Serializable {
            /**
             * id : 15
             * img : https://static.imjk.club/banner/1609477316017.png?1035_330
             * url : taobao://shop593468610.taobao.com
             * is_login : 0
             */

            private int id;
            private String img;
            private String url;
            private int is_login;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getIs_login() {
                return is_login;
            }

            public void setIs_login(int is_login) {
                this.is_login = is_login;
            }
        }

        public static class BlocksBean implements Serializable {
            /**
             * id : 16
             * title : 教程
             * img : http://static.imjk.club/banner/1608192263874.png?156_156
             * url : https://www.imjk.club/web/test/02/#/tutorials?nav_alpha=2
             * is_login : 0
             */

            private int id;
            private String title;
            private String img;
            private String url;
            private int is_login;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getIs_login() {
                return is_login;
            }

            public void setIs_login(int is_login) {
                this.is_login = is_login;
            }
        }

        public static class TopicsBean implements Serializable {
            /**
             * name : 格饼练习生3
             * icon : https://static.imjk.club/banner/1609840631401.png?108_108
             * tag : 浏览
             * num : 0
             */

            private String name;
            private String icon;
            private String tag;
            private String num;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }
        }
    }
}
