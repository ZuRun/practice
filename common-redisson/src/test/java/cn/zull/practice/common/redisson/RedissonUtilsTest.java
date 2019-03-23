package cn.zull.practice.common.redisson;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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

    @Test
    public void testSubList() {
        final String key = "p:tracing:log";

        List<String> strings = redissonUtils.matchBlPop(key, 5);
        strings.forEach(s -> {
            log.info("[结果] {}", s);
        });
    }

}