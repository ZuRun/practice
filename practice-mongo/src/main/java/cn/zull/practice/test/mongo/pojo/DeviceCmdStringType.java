package cn.zull.practice.test.mongo.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author zurun
 * @date 2018/10/24 14:59:45
 */
@Data
public class DeviceCmdStringType extends BaseDeviceCmd {

    private List<DeviceCmdStringTypeValue> values;
}
