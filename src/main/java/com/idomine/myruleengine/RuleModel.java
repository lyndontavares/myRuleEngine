package com.idomine.myruleengine;

import java.util.List;

public class RuleModel
{

    private Object rule;
    
    private List<RuleMethod> metodoRule;

    public Object getRule()
    {
        return rule;
    }

    public List<RuleMethod> getMetodoRule()
    {
        return metodoRule;
    }

    public void setRule(Object rule)
    {
        this.rule = rule;
    }

    public void setMetodoRule(List<RuleMethod> metodoRule)
    {
        this.metodoRule = metodoRule;
    }

 
    
    
}
