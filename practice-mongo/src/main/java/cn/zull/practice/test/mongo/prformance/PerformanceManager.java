package cn.zull.practice.test.mongo.prformance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * @author zurun
 * @date 2019/1/13 20:10:13
 */
@Component
public class PerformanceManager {
    @Autowired
    MongoTemplate mongoTemplate;

    public void add(Object object) {
        mongoTemplate.save(object,"performance-test");
    }
}
