package com.itsdf07.core.lib.ad;

import com.itsdf07.core.lib.ad.csj.AdCsj;
import com.itsdf07.core.lib.ad.qq.AdYlh;

/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2021/3/24
 */
public class AdPaltformFactory {
    /**
     * @param adPlatfrom 1<<0-优亮汇，1<<1-穿山甲
     * @return
     */
    public AdAdvance createAdPlatform(int adPlatfrom) {
        AdAdvance adPlatform = null;
        switch (adPlatfrom) {
            case 1 << 0://优亮汇
                adPlatform = new AdYlh();
                break;
            case 1 << 1://穿山甲
                adPlatform = new AdCsj();
                break;
            default:
                break;
        }
        return adPlatform;
    }
}
