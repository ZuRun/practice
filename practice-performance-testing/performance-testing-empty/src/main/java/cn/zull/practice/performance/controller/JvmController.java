package cn.zull.practice.performance.controller;

import cn.zull.practice.performance.service.JvmService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zurun
 * @date 2019-06-23 21:11:54
 */
@Slf4j
@RestController
@RequestMapping("jvm")
public class JvmController {

    @Autowired
    JvmService jvmService;

    @GetMapping("test1")
    public JSONObject test() throws InterruptedException {
//        log.info("[t]");
        Thread.sleep(5);
        JSONObject json = jvmService.test();
        json.put("a", "fk");
        return json;
    }

}
