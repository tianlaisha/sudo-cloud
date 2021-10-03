package sudo.client_user.sentinelrule;

import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;

/**
 * @author glz
 * @version 1.0
 * @description
 * @date 2021/10/314:22
 */
public class FlowRuleInitFunc implements InitFunc {

    @Override
    public void init() throws Exception {
        ArrayList<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setCount(20);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setResource("hello");
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }

}
