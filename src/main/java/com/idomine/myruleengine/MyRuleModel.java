package com.idomine.myruleengine;

import java.util.List;

public class MyRuleModel
{

    private Object rule;
    
    private List<MyRuleMethod> metodoRule;

    public Object getRule()
    {
        return rule;
    }

    public List<MyRuleMethod> getMetodoRule()
    {
        return metodoRule;
    }

    public void setRule(Object rule)
    {
        this.rule = rule;
    }

    public void setMetodoRule(List<MyRuleMethod> metodoRule)
    {
        this.metodoRule = metodoRule;
    }

 
    
    
}
