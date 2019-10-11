package cn.zull.practice.mysql.a.mapper;

import cn.zull.practice.mysql.a.entity.BuyerInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author zurun
 * @date 2019/10/10 16:33:07
 */
@Mapper
@Repository
public interface BuyerInfoMapper extends BaseMapper<BuyerInfo> {
}
