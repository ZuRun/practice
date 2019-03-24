package cn.zull.practice;

import cn.zull.practice.tracing.provider.BaseAppender;
import com.alibaba.fastjson.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zurun
 * @date 2019/3/3 21:56:25
 */
@SpringBootApplication
public class TraceProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(TraceProviderApplication.class, args);
        new TraceProviderApplication().provider();
    }

    public void provider() {
        int poolSize = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
        for (int i = 0; i < poolSize; i++) {
            executorService.execute(providerRunnable);
        }
    }

    private Runnable providerRunnable = () -> {
        while (true) {
            try {
                Thread.sleep(1);
                long now = System.currentTimeMillis();
                JSONObject json = new JSONObject();
                json.put("traceId", UUID.randomUUID().toString());
                json.put("spanId", "0.1.2");
                json.put("traceType", "resttemplate");
                json.put("endPoint", "192.158.1.1");
                json.put("url", "http://baidu.com/234141/dsfaf/wqerq14/adsfawf");
                json.put("ctm", now);
                json.put("stm", now);
                json.put("etm", now);
                json.put("cost", 12);
                json.put("status", "SUCCESS");

                BaseAppender.blockingQueue.put(json.toJSONString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

}
