package cn.zull.practice.mysql.a.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zurun
 * @date 2019/10/10 16:30:22
 */
@Data
@TableName("t_goods")
public class Goods {
    //    @TableId(value = "id", type = IdType.AUTO)
    @TableId
    private Integer id;
    private String name;
    @TableField()
    private Integer state;
    /**
     * specification 规格
     */
    private String spec;
}
