package cn.bugstack.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author: chen
 * @Date: 2024/12/16 15:12
 */

@Data
public class StrategyRule {

    private Long id;
    private Long strategyId;
    private Integer awardId;
    private Integer ruleType;
    private String ruleModel;
    private String ruleValue;
    private String ruleDesc;
    private Date createTime;
    private Date updateTime;

}
