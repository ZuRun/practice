package cn.zull.practice.test.mongo.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @author zurun
 * @date 2018/10/24 15:04:18
 */
@Data
//@Document(collection = "DeviceCmd")
public abstract class BaseDeviceCmd {
    @Id
    public String id;
    /**
     * 设备类型
     */
    private String deviceType;

    /**
     * AndLink协议标准key
     */
    private String cmdKey;
    /**
     * AndLink协议value类型
     */
    private String cmdType;

    /**
     * aiui语义key
     */
    private String aiuiKey;

}
