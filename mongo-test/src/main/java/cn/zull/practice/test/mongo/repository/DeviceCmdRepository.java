package cn.zull.practice.test.mongo.repository;

import cn.zull.practice.test.mongo.pojo.BaseDeviceCmd;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zurun
 * @date 2018/10/24 15:11:20
 */
@Component
public interface DeviceCmdRepository extends MongoRepository<BaseDeviceCmd, String> {
//    Customer findByFirstName(String firstName);
//,'values.aiuiVal':'开'
    @Query(value = "{'deviceType':?0}",fields = "{'cmdKey':1,'values':1}")
    List<Object> mytest(String deviceType);
    @Query(value = "{'deviceType':?0}",fields = "{'cmdKey':1,'values':1}")
    List<BaseDeviceCmd> list(String deviceType);
}
