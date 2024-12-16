package cn.bugstack.domain.strategy.service.armory;

import cn.bugstack.domain.strategy.model.StrategyAwardEntity;
import cn.bugstack.domain.strategy.repository.IStrategyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.*;

/**
 * @Description:
 * @Author: chen
 * @Date: 2024/12/16 17:31
 */

@Slf4j
@Service
public class StrategyArmory implements IStrategyArmory {

    @Resource
    private IStrategyRepository repository;

    /*
    * 装配就是查找好商品，再算好概率并把抽奖表做出来
    * */
    @Override
    public boolean assembleLotteryStrategy(Long strategyId) {
        List<StrategyAwardEntity> strategyAwardEntities = repository.queryStrategyAwardList(strategyId);

        BigDecimal minAwardRate = strategyAwardEntities.stream()
                .map(StrategyAwardEntity::getAwardRate)
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        BigDecimal totalAwardRate = strategyAwardEntities.stream()
                .map(StrategyAwardEntity::getAwardRate)
                .reduce(BigDecimal.ZERO,BigDecimal::add);

        BigDecimal rateRange = totalAwardRate.divide(minAwardRate,0, RoundingMode.CEILING);

        List<Integer> strategyAwardSearchRateTables = new ArrayList<>(rateRange.intValue());
        for(StrategyAwardEntity strategyAward:strategyAwardEntities){
            Integer awardId = strategyAward.getAwardId();
            BigDecimal awardRate = strategyAward.getAwardRate();
            for(int i = 0;i < rateRange.multiply(awardRate).setScale(0,RoundingMode.CEILING).intValue();i++){
                strategyAwardSearchRateTables.add(awardId);
            }
        }

        Collections.shuffle(strategyAwardSearchRateTables);

        Map<Integer,Integer> shuffleStrategyAwardSearchRateTable = new LinkedHashMap<>();
        for (int i = 0; i < shuffleStrategyAwardSearchRateTable.size();i++){
            shuffleStrategyAwardSearchRateTable.put(i,strategyAwardSearchRateTables.get(i));
        }

        return true;
    }

    @Override
    public Integer getRandomAwardId(Long strategyId) {
        int rateRange = repository.getRateRange(strategyId);

        return repository.getStrategyAwardAssemble(strategyId, new SecureRandom().nextInt(rateRange));
    }

}
