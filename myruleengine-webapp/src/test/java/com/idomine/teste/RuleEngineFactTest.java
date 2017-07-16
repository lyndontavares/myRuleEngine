package com.idomine.teste;

 

import org.junit.Assert;
import org.junit.Test;

import com.idomine.myruleengine.MyRuleEngine;
import com.idomine.myruleengine.annotations.InjectFact;
import com.idomine.myruleengine.annotations.RuleCondition;
import com.idomine.myruleengine.notification.Notification;

public class RuleEngineFactTest
{

    public class BusinessRule{
        
        @InjectFact
        private String fact1;

        @InjectFact 
        private String fact2;
        
        @RuleCondition
        public Notification rule()
        {
            return new Notification()
                    .addMessageInfo(fact1)
                    .condition("Lyndon".equals(fact1) && "Tavares".equals(fact2));
        }
    }
    
    @Test
    public void factTest()
    {
        
        MyRuleEngine re = MyRuleEngine.Builder()
                .addFact("fact1", "Lyndon")
                .addFact("fact2", "Tavares")
                .addClassRule( new BusinessRule() )
                .addAllMethods()
                .buildRules();
        Assert.assertTrue(re.checkRules());
    }
    
    
}
