package com.idomine.ruleengine;

import java.util.List;

public class RuleModel
{

    private Rule dolphinRule;
    
    private List<String> metodoRule;

    public Rule getDolphinRule()
    {
        return dolphinRule;
    }

    public void setDolphinRule(Rule dolphinRule)
    {
        this.dolphinRule = dolphinRule;
    }

    public List<String> getMetodoRule()
    {
        return metodoRule;
    }

    public void setMetodoRule(List<String> metodoRule)
    {
        this.metodoRule = metodoRule;
    }

    @Override
    public String toString()
    {
        return "DolphinRuleModel [dolphinRule=" + dolphinRule + ", metodoRule=" + metodoRule + "]";
    }
    
    
    
}
