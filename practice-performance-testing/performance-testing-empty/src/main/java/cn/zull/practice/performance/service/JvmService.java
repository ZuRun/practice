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
        JSONObject json = new JSONObject(14);
        json.put("code", 0);
        json.put("msg", "成功！");
        json.put("xx","xxxx1");
        json.put("xx2","xxxx1");
        json.put("xx3","xxxx1");
        json.put("xx4","xxxx1");
        json.put("xx5","xxxx1");
        json.put("xx6","xxxx1");
        json.put("xx7","xxxx1");
        json.put("xx8","xxxx1");
        json.put("xx9","xxxx1");
        json.put("xx10","xxxx1");
        json.put("xx11","xxxx1");
        json.put("xx12","xxxx1");
        json.put("xx13","xxxx1");
        json.put("xx14","xxxx1");
        json.put("xx15","xxxx1");
        json.put("xx16","xxxx1");
        json.put("xx17","xxxx1");
        json.put("xx18","xxxx1");
        json.put("xx19","xxxx1");
        json.put("xx20","xxxx1");

        return json;
    }
}
