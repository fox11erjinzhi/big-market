package cn.bugstack.domain.strategy.model.entity;

import cn.bugstack.types.common.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: chen
 * @Date: 2024/12/17 11:01
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyRuleEntity {

    private Long strategyId;
    /** 抽奖奖品ID【规则类型为策略，则不需要奖品ID】 */
    private Integer awardId;
    /** 抽象规则类型；1-策略规则、2-奖品规则 */
    private Integer ruleType;
    /** 抽奖规则类型【rule_random - 随机值计算、rule_lock - 抽奖几次后解锁、rule_luck_award - 幸运奖(兜底奖品)】 */
    private String ruleModel;
    /** 抽奖规则比值 */
    private String ruleValue;
    /** 抽奖规则描述 */
    private String ruleDesc;

    public Map<String, List<Integer>> getRuleWeightValues(){
        if(!"rule_weight".equals(ruleModel)) return null;
        String[] ruleValueGroups = ruleValue.split(Constants.SPACE);

        Map<String, List<Integer>> resultMap = new HashMap<>();
        for(String ruleValueGroup : ruleValueGroups) {
            if(ruleValueGroup == null || ruleValueGroup.isEmpty()){
                return resultMap;
            }

            String[] parts = ruleValueGroup.split(Constants.COLON);
            if(parts.length != 2){
                throw new IllegalArgumentException("rule_weight rule_rule invalid input format" + ruleValueGroup);
            }

            String[] valueStrings = parts[1].split(Constants.SPLIT);
            List<Integer> values = new ArrayList<>();
            for(String valueString : valueStrings){
                values.add(Integer.parseInt(valueString));
            }
            resultMap.put(ruleValueGroup, values);
        }

        return resultMap;

    }

}
