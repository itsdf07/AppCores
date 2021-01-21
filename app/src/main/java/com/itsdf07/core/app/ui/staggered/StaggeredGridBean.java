package com.itsdf07.core.app.ui.staggered;

/**
 * @Description: 瀑布流布局对于的Item数据
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/1/20
 */
public class StaggeredGridBean {
    /**
     * 作品地址
     */
    private String productionUrl;
    private int localProduction;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 用户昵称
     */
    private String dispName;
    /**
     * Item内容
     */
    private String content;

    /**
     * Item类型
     */
    private int type;

    /**
     * 数量的数据
     */
    private int count;


    public String getProductionUrl() {
        return productionUrl;
    }

    public void setProductionUrl(String productionUrl) {
        this.productionUrl = productionUrl;
    }

    public int getLocalProduction() {
        return localProduction;
    }

    public void setLocalProduction(int localProduction) {
        this.localProduction = localProduction;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDispName() {
        return dispName;
    }

    public void setDispName(String dispName) {
        this.dispName = dispName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
