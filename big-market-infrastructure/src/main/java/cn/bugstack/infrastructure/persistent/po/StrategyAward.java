package cn.bugstack.infrastructure.persistent.po;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description:
 * @Author: chen
 * @Date: 2024/12/16 15:12
 */

@Data
public class StrategyAward {

    private Long id;
    private Long strategyId;
    private Integer awardId;
    private String awardTitle;
    private String awardSubtitle;
    private Integer awardCount;
    private Integer awardCountSurplus;
    private BigDecimal awardRate;
    private String ruleModels;
    private Integer sort;
    private Date createTime;
    private Date updateTime;

}
