package cn.bugstack.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author: chen
 * @Date: 2024/12/16 15:10
 */

@Data
public class Award {

    private Long id;
    private Integer awardId;
    private String awardKey;
    private String awardConfig;
    private String awardDesc;
    private Date createTime;
    private Date updateTime;



}
