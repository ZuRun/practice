package cn.zull.practice.performance.redis.service;

import cn.zull.practice.performance.RedisApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StopWatch;

/**
 * @author zurun
 * @date 2019/10/16 11:11:39
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisApplication.class)
public class RedisTestServiceTest {
    @Autowired
    RedisTestService redisTestService;

    @Test
    public void batchWrite() {
        final int threadSize = 50;
        // 单线程循环次数
        final int cycleNum = 10000;


        redisTestService.batchWrite(threadSize, cycleNum);

    }
}