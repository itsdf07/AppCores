package com.itsdf07.core.app.ui.home.bean;

import java.io.Serializable;
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
     * data : {"banners":[{"id":1,"img":"http://static.imjk.club/banner/1606383859810.png?501_240","url":"imjk://app/navigate?params=JTdCJTIydGFyZ2V0JTIyOiUyMmNyZWF0ZWdyaWQlMjIsJTIyb3B0aW9ucyUyMjolN0IlN0QlN0Q=","is_login":0},{"id":2,"img":"http://static.imjk.club/banner/1606383988410.png?501_240","url":"imjk://app/navigate?params=JTdCJTIydGFyZ2V0JTIyOiUyMmNyZWF0ZWNvbG9yJTIyLCUyMm9wdGlvbnMlMjI6JTdCJTdEJTdE","is_login":0}],"activitys":[{"id":3,"img":"https://static.imjk.club/banner/1609237397254.png?1035_480","url":"https://static.imjk.club/gblxs/index.html?nav_alpha=0&simple_left=0","is_login":0,"broadcasts":[{"name":"JK 设计师","avatar":"https://static.imjk.club/avatar/avatar_1611125157359.jpg","msg":"格子酱广播内容"}]},{"id":4,"img":"http://static.imjk.club/banner/1607662919857.png?1035_480","url":"http://mvvideo10.meitudata.com/5fc645e2de3a7vurdqojcq6006_H264_1_48487ee838d5a4.mp4","is_login":0,"broadcasts":[{"name":"JK 设计师","avatar":"https://static.imjk.club/avatar/avatar_1611125157359.jpg","msg":"格子酱广播内容"}]},{"id":6,"img":"http://static.imjk.club/banner/1607662908989.png?1035_480","url":"http://mvvideo10.meitudata.com/5fc89d4f2468d23sy5nd6c7261_H264_1_48d40b937be184.mp4","is_login":0,"broadcasts":[{"name":"JK 设计师","avatar":"https://static.imjk.club/avatar/avatar_1611125157359.jpg","msg":"格子酱广播内容"}]},{"id":10,"img":"http://static.imjk.club/banner/1608178377372.png?1035_480","url":"http://mvvideo10.meitudata.com/5fdac60f9d629fz5puw20f483_H264_1_4d0e6cf2bf7b4f.mp4","is_login":0,"broadcasts":[{"name":"JK 设计师","avatar":"https://static.imjk.club/avatar/avatar_1611125157359.jpg","msg":"格子酱广播内容"}]}],"eggs":[{"id":14,"title":"样机","img":"http://static.imjk.club/banner/1608192107346.png?156_156","url":"https://www.imjk.club/web/test/02/#/prototype?nav_alpha=0&simple_left=0&canUse=false","is_login":0},{"id":15,"title":"色卡","img":"http://static.imjk.club/banner/1608192191778.png?156_156","url":"https://www.imjk.club/web/test/02/#/color-card?nav_alpha=0&simple_left=0","is_login":0},{"id":16,"title":"教程","img":"http://static.imjk.club/banner/1608192263874.png?156_156","url":"https://www.imjk.club/web/test/02/#/tutorials?nav_alpha=2","is_login":0}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        private List<BannersBean> banners;
        private List<ActivitysBean> activitys;
        private List<EggsBean> eggs;

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
             * id : 3
             * img : https://static.imjk.club/banner/1609237397254.png?1035_480
             * url : https://static.imjk.club/gblxs/index.html?nav_alpha=0&simple_left=0
             * is_login : 0
             * broadcasts : [{"name":"JK 设计师","avatar":"https://static.imjk.club/avatar/avatar_1611125157359.jpg","msg":"格子酱广播内容"}]
             */

            private int id;
            private String img;
            private String url;
            private int is_login;
            private List<BroadcastsBean> broadcasts;

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
                 * name : JK 设计师
                 * avatar : https://static.imjk.club/avatar/avatar_1611125157359.jpg
                 * msg : 格子酱广播内容
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
             * img : http://static.imjk.club/banner/1608192107346.png?156_156
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
    }
}
