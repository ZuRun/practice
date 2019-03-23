package cn.zull.practice.es;

import cn.zull.practice.es.entity.GoodsInfo;
import cn.zull.practice.es.repository.GoodsRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zurun
 * @date 2019/3/22 23:49:52
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EsTestApplication.class)
public class ElasticSerachTest {
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    GoodsRepository goodsRepository;

    @Test
    public void tst() {
//        List<AliasMetaData> person = elasticsearchTemplate.queryForAlias("account");
//        log.info(JSON.toJSONString(person));
        GoodsInfo goodsInfo = new GoodsInfo();
        goodsInfo.setId(1231231231L);
        goodsInfo.setDesc("ddesc");
        goodsInfo.setName("name1");
        goodsRepository.save(goodsInfo);

        Iterable<GoodsInfo> all = goodsRepository.findAll();
        all.forEach(goods -> {
            log.info("[goodsinfo] {}", goods);
        });
    }
}
