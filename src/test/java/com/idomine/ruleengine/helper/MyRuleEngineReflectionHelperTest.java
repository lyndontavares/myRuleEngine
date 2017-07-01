package com.idomine.ruleengine.helper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.idomine.business.SaleRule;
import com.idomine.model.Person;
import com.idomine.model.Sale;
import com.idomine.myruleengine.MyRuleFact;
import com.idomine.myruleengine.helper.MyRuleReflectionHelper;

public class MyRuleEngineReflectionHelperTest
{

    @Test
    public void testeAnotacao()
    {
        SaleRule faturaRule = new SaleRule();
        
        List<MyRuleFact> fatos = new ArrayList<>();
        fatos.add(new MyRuleFact("fatura", Sale.getFake()));
        fatos.add(new MyRuleFact("entidade",Person.getFake()));
        fatos.add(new MyRuleFact("valorMinimo",BigDecimal.TEN));

        MyRuleReflectionHelper.prepareFacts(faturaRule, fatos);
         Assert.assertTrue(faturaRule.fatura.getCodigo() == 1);
        
    }

}
