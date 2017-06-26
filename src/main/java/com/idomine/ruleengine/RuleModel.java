package com.idomine.ruleengine;

import java.util.List;

public class RuleModel
{

    private Rule rule;
    
    private List<String> metodoRule;

    public Rule getRule()
    {
        return rule;
    }

    public List<String> getMetodoRule()
    {
        return metodoRule;
    }

    public void setRule(Rule rule)
    {
        this.rule = rule;
    }

    public void setMetodoRule(List<String> metodoRule)
    {
        this.metodoRule = metodoRule;
    }
    
    
}
