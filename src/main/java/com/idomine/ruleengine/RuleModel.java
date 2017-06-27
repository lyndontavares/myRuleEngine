package com.idomine.ruleengine;

import java.util.List;

public class RuleModel
{

    private Object rule;
    
    private List<String> metodoRule;

    public Object getRule()
    {
        return rule;
    }

    public List<String> getMetodoRule()
    {
        return metodoRule;
    }

    public void setRule(Object rule)
    {
        this.rule = rule;
    }

    public void setMetodoRule(List<String> metodoRule)
    {
        this.metodoRule = metodoRule;
    }
    
    
}
