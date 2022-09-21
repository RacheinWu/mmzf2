package com.rachein.mmzf2.redis.myPrefixKey;


import com.rachein.mmzf.redis.BasePrefix;

public class UserKey extends BasePrefix {

    public static final int TOKEN_EXPIRE = 3600*24 *2;//默认两天

    /**
     * 防止被外面实例化
     * @param expireSeconds
     * @param prefix
     */
    private UserKey(int expireSeconds, String prefix) {super(expireSeconds,prefix);}

    /**
     * 需要缓存的字段：
     */
    public static UserKey token = new UserKey(TOKEN_EXPIRE,"token");

    public static UserKey getById = new UserKey(TOKEN_EXPIRE,"user-id");

}