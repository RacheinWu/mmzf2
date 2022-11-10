package com.rachein.mmzf2.redis.myPrefixKey;

import com.rachein.mmzf2.redis.BasePrefix;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/11/10
 * @Description
 */
public class TableHeadPrefix  extends BasePrefix {

    public static final int TOKEN_EXPIRE = -1;
    public static final String DRAFT_HEAD_PREFIX = "draft-head";

    /**
     * 防止被外面实例化
     * @param expireSeconds
     * @param prefix
     */
    private TableHeadPrefix(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    /**
     * 需要缓存的字段：
     */
    public static TableHeadPrefix GET_DRAFT_HEAD = new TableHeadPrefix(TOKEN_EXPIRE, DRAFT_HEAD_PREFIX);
}
