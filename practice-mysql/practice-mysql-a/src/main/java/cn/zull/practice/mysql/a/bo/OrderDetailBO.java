package cn.zull.practice.mysql.a.bo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zurun
 * @date 2019/10/12 15:35:24
 */
@Data
public class OrderDetailBO {
    private String userName;
    private Integer orderId;
    private String orderNumber;
    private Integer orderStatus;
    private BigDecimal orderAmount;
    private String goodsName;
    private BigDecimal goodsPrice;
    private BigDecimal goodsNumber;
    private BigDecimal goodsAmount;

}
