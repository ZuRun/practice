package cn.zull.practice.es.repository;

import cn.zull.practice.es.entity.GoodsInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @author zurun
 * @date 2019/3/23 17:19:53
 */
@Component
public interface GoodsRepository extends ElasticsearchRepository<GoodsInfo, Long> {
}
