package com.rachein.mmzf2.redis.myPrefixKey;

import com.rachein.mmzf2.redis.BasePrefix;

/**
 * @author 物联网工程系 ITAEM 唐奕泽
 * @Description
 * @date 2022/10/7 19:55
 */
public class ArticleKey extends BasePrefix {

    public static final int TOKEN_EXPIRE = 3600;//默认一个小时
    public static final String PREFIX = "article-id";

    /**
     * 防止被外面实例化
     * @param expireSeconds
     * @param prefix
     */
    public ArticleKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    /**
     * 需要缓存的字段：
     */
    public static ArticleKey getById = new ArticleKey(TOKEN_EXPIRE, PREFIX);

}
