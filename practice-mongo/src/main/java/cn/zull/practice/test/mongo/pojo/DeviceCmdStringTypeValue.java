package cn.zull.practice.test.mongo.pojo;

import lombok.Data;

/**
 * @author zurun
 * @date 2018/10/24 15:09:52
 */
@Data
public class DeviceCmdStringTypeValue {
    private String aiuiVal;
    private String cmdVal;

    public DeviceCmdStringTypeValue() {
    }

    public DeviceCmdStringTypeValue(String aiuiVal, String cmdVal) {
        this.aiuiVal = aiuiVal;
        this.cmdVal = cmdVal;
    }
}
