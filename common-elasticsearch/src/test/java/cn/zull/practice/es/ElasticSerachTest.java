package cn.zull.practice.es;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.cluster.metadata.AliasMetaData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author zurun
 * @date 2019/3/22 23:49:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EsTestApplication.class)
public class ElasticSerachTest {
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void tst() {
        List<AliasMetaData> person = elasticsearchTemplate.queryForAlias("account");
        System.out.printf(JSON.toJSONString(person));
    }
}
