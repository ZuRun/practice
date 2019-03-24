package cn.zull.practice.common.redisson;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

/**
 * @author zurun
 * @date 2019/3/23 22:39:28
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedissonApplication.class)
public class RedissonUtilsTest {
    @Autowired
    RedissonUtils redissonUtils;

    /**
     * 测试批量lpop命令
     */
    @Test
    public void testLPop() {
        final String key = "p:test:list";
        for (int i = 0; i < 10; i++) {
            redissonUtils.rPush(key, "a" + i);
        }
        List<String> strings = redissonUtils.matchLPop(key, 5);
        log.info("[结果] {}", JSON.toJSONString(strings));
        List<String> strings2 = redissonUtils.matchLPop(key, 6);
        log.info("[结果] {}", JSON.toJSONString(strings2));
        List<String> strings3 = redissonUtils.matchLPop(key, 5);
        log.info("[结果] {}", JSON.toJSONString(strings3));
    }

    @Test
    public void hSet() throws InterruptedException {
        String key = "p:test:hash";
        Map<String, String> map = new HashMap<>();
        map.put("hk1", "hv1");
        map.put("hk2", "hv2");
        redissonUtils.hSet(key, map, 30, TimeUnit.SECONDS);
        long pttl = redissonUtils.pttl(key);
        log.info("[使用过期时间] key:{} pttl:{}", key, pttl);
        Assert.assertThat(pttl, lessThan(30000L));
        Assert.assertThat(pttl, greaterThan(29000L));

        Thread.sleep(5000L);
        pttl = redissonUtils.pttl(key);
        log.info("[使用过期时间] key:{} pttl:{}", key, pttl);
        Assert.assertThat(pttl, lessThan(25000L));
        Assert.assertThat(pttl, greaterThan(24000L));

        Thread.sleep(5000L);
        pttl = redissonUtils.pttl(key);
        log.info("[使用过期时间] key:{} pttl:{}", key, pttl);
        Assert.assertThat(pttl, lessThan(20000L));
    }

}