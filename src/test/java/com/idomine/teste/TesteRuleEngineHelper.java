package com.idomine.teste;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.idomine.model.Entidade;
import com.idomine.model.Fatura;
import com.idomine.ruleengine.RuleFact;
import com.idomine.ruleengine.helper.RuleEngineHelper;
import com.idomine.rules.FaturaRule;

public class TesteRuleEngineHelper
{

    @Test
    public void testeAnotacao()
    {
        FaturaRule faturaRule = new FaturaRule();
        
        List<RuleFact> fatos = new ArrayList<>();
        fatos.add(new RuleFact("fatura", Fatura.getFake()));
        fatos.add(new RuleFact("entidade",Entidade.getFake()));
        fatos.add(new RuleFact("valorMinimo",BigDecimal.TEN));

        RuleEngineHelper.prepareFacts(faturaRule, fatos);
         Assert.assertTrue(faturaRule.fatura.getCodigo() == 1);
        
    }

}
