package cn.zull.practice.mysql.a.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author zurun
 * @date 2019/10/11 10:24:00
 */
@Data
@TableName("t_buyer_info")
public class BuyerInfo {
    @TableId
    private Integer buyerId;
    private String name;
    private Integer state;
    private Integer sex;
    private Date registerTime;
}
