package cn.bugstack.domain.strategy.service.rule.chain;

/**
 * @Description:
 * @Author: chen
 * @Date: 2024/12/18 12:55
 */

public abstract class AbstractLogicChain implements ILogicChain {

    private ILogicChain next;

    @Override
    public ILogicChain next() {
        return next;
    }

    @Override
    public ILogicChain appendNext(ILogicChain next) {
        this.next = next;
        return next;
    }

    protected abstract String ruleModel();

}
