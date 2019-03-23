package cn.zull.practice.es.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author zurun
 * @date 2019/3/23 17:17:51
 */
@Data
@Document(indexName = "test",type = "goods")
public class GoodsInfo {
    @Id
    private Long id;
    private String name;
    private String desc;

}
