package cn.zull.practice.common.redisson;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author zurun
 * @date 2018/10/15 00:03:25
 */
public interface RedisUtils<V, HK, HV> {
    boolean expire(String key, long expirationTime, TimeUnit unit);

    V get(String key);

    void set(String key, V value);

    /**
     * @param key
     * @param value
     * @param expirationTime 过期时间
     * @param unit           TimeUnit
     */
    void set(String key, V value, long expirationTime, TimeUnit unit);

    /**
     * redis SETNX命令
     *
     * @param key
     * @param value
     * @return {@code true} 之前不存在, or {@code false} 缓存中已存在
     * @see <a href="http://redis.io/commands/setnx">Redis Documentation: SETNX</a>
     */
    Boolean setNx(String key, V value);

    Boolean setNx(String key, V value, long expirationTime, TimeUnit unit);


    /**
     * @param key
     * @param hashKey
     * @return
     */
    HV hGet(String key, HK hashKey);

    /**
     * hGet多个hashKey值
     * <p>
     * 如果key不存在,返回不为null,而是一个空的linkedMap.
     * 如果hashKey未找到,map中不包含此key
     * <p>
     * 不可以通过map.size来判断是否有key
     *
     * @param key
     * @param hashKey
     * @return
     */
    Map<String, String> hGetMap(String key, String... hashKey);

    /**
     * 获取hash类型的集合
     *
     * @param key
     * @return
     */
    Map<String, String> hGetAll(String key);

    /**
     * HSET
     *
     * @param key
     * @param hKey
     * @param hValue
     */
    void hSet(String key, HK hKey, HV hValue);

    void hSet(String key, Map<String, String> map);

    /**
     * 是否包含key
     *
     * @param key
     * @return
     */
    Boolean hasKey(String key);

    /**
     * 根据前缀批量删除
     *
     * @param prefix
     * @return
     */
    long deleteByPrefix(String prefix);

    /**
     * 删除
     *
     * @param key
     * @return
     */
    boolean delete(String key);

    /**
     * set中是否包含member
     *
     * @param key
     * @param member
     * @return
     */
    boolean sisMember(String key, String member);

    /**
     * set中新增
     *
     * @param key
     * @param member
     * @return
     */
    boolean sAdd(String key, String member);

    boolean sRem(String key, String member);

    /**
     * 目前只支持每次pop一个数据,需要多个的话,考虑用脚本来做
     *
     * @param key
     * @param timeout
     * @return
     * @throws InterruptedException
     */
    String bLPop(String key, long timeout) throws InterruptedException;

    String bLPop(String key, long timeout, TimeUnit unit) throws InterruptedException;

    List<String> matchBlPop(String key, int length);

    boolean rPush(String key, String... value);

    boolean rPush(String key, List<String> values);

    /**
     * list长度
     *
     * @param key
     * @return
     */
    int llen(String key);
}
