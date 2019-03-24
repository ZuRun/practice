package cn.zull.practice.tracing.service;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zurun
 * @date 2019/3/23 18:30:47
 */
@Slf4j
@Service
public class TraceUpload2EsService {
    @Autowired
    TransportClient transportClient;
    private final String indexName = "tracing";
    private final String type = "t";

    public void upload(String traceInfo) {
        JSONObject json = JSONObject.parseObject(traceInfo);
        IndexResponse indexResponse = transportClient.prepareIndex(indexName, type).setSource(json).get();
        String id = indexResponse.getId();
//        log.info("[写入es] id:{}", id);
    }

    public void batchExecute(List<String> traceInfos) {
        BulkRequestBuilder bulkRequest = transportClient.prepareBulk();
        traceInfos.forEach(traceInfo -> {
            JSONObject json = JSONObject.parseObject(traceInfo);
            bulkRequest.add(transportClient.prepareIndex(indexName, type).setSource(json));
        });
        BulkResponse bulkResponse = bulkRequest.execute().actionGet();
//        BulkItemResponse[] items = bulkResponse.getItems();
//        log.info("[批量写入es] bulkResponse:{}", JSONObject.toJSONString(bulkResponse));
    }
}
