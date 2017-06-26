package com.idomine.teste;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.idomine.model.Entidade;
import com.idomine.model.Fatura;
import com.idomine.ruleengine.Fato;
import com.idomine.ruleengine.helper.RuleEngineHelper;
import com.idomine.rules.FaturaRule;

public class TesteRuleEngineHelper
{

    @Test
    public void testeAnotacao()
    {
        FaturaRule faturaRule = new FaturaRule();
        
        List<Fato> fatos = new ArrayList<>();
        fatos.add(new Fato("fatura", Fatura.getFake()));
        fatos.add(new Fato("entidade",Entidade.getFake()));
        fatos.add(new Fato("valorMinimo",BigDecimal.TEN));

        RuleEngineHelper.prepareFacts(faturaRule, fatos);
         Assert.assertTrue(faturaRule.fatura.getCodigo() == 1);
        
    }

}
