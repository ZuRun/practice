package cn.zull.practice.test.mongo.prformance;

import cn.zull.practice.common.basis.utils.RandomUtils;
import cn.zull.practice.common.basis.utils.UUIDUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.Arrays;
import java.util.List;

/**
 * 初始插入数据进行性能测试
 *
 * @author zurun
 * @date 2019/1/13 20:03:57
 */
@Slf4j
//@Component
public class PerformanceService implements CommandLineRunner {
    @Autowired
    PerformanceManager performanceManager;
    private final List<String> deviceNameList = Arrays.asList("空调", "电视", "插座", "灯", "机顶盒");

    @Override
    public void run(String... args) throws Exception {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            JSONObject object = new JSONObject();
            object.put("index", i);
            object.put("name", "张三_" + i);
            object.put("alias", UUIDUtils.simpleUUID());
            object.put("devices", Arrays.asList(getRandomDevice(), getRandomDevice(), getRandomDevice()));
            object.put("account", UUIDUtils.simpleUUID());
            object.put("boxId", Arrays.asList(UUIDUtils.simpleUUID(), UUIDUtils.simpleUUID(), UUIDUtils.simpleUUID()));
            performanceManager.add(object);
        }
        long endTime = System.currentTimeMillis();

        log.info("[批量插入数据] 耗时:{}", endTime - startTime);
    }

    private JSONObject getRandomDevice() {
        JSONObject device = new JSONObject();
        device.put("name", getRandomDeviceName());
        device.put("room", "客厅");
        device.put("price", RandomUtils.randomNumber(998));
        return device;
    }

    private String getRandomDeviceName() {
        return deviceNameList.get(RandomUtils.randomNumber(deviceNameList.size() - 1));
    }
}
