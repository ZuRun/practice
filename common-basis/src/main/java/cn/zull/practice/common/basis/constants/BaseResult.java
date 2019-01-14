package cn.zull.practice.common.basis.constants;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * @author zurun
 * @date 2018/3/11 12:51:57
 */
@Data
public abstract class BaseResult {
    private int code = 0;
    private String message;
    private Object data;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
