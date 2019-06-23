package cn.zull.practice.performance.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @author zurun
 * @date 2019-06-23 21:20:12
 */
@Service
public class JvmService {

    public JSONObject test() {
        JSONObject json = new JSONObject();
        json.put("code", 0);
        json.put("msg", "成功！");
        return json;
    }
}
