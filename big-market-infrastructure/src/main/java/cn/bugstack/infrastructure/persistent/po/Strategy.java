package cn.bugstack.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author: chen
 * @Date: 2024/12/16 15:11
 */

@Data
public class Strategy {

    private Long id;
    private Long strategyId;
    private String strategyDesc;
    private Date createTime;
    private Date updateTime;

}
