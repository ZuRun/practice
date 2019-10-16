package cn.zull.practice.performance.redis.dto;

import lombok.Data;

/**
 * @author zurun
 * @date 2019/10/16 11:53:00
 */
@Data
public class RedisTpsRespDTO {
    private Double totalTime;
    private Integer threadSize;
    private Integer cycleNum;
    private double tps;
}
