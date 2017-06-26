package com.idomine.teste;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.idomine.model.Entidade;
import com.idomine.model.Fatura;
import com.idomine.ruleengine.RuleEngine;
import com.idomine.rules.EntidadeRule;
import com.idomine.rules.FaturaRule;

public class TesteRuleEngineBuider
{

    @Test
    public void testeRuleEngine()
    {
        // fatos
        Fatura fatura = Fatura.getFake();
        Entidade entidade = Entidade.getFake();
        fatura.setEntidade(entidade);

        // rules
        FaturaRule faturaRule = new FaturaRule();
        EntidadeRule entidadeRule = new EntidadeRule();

        //Engine
        RuleEngine re = RuleEngine
                .Builder()
                .addFato("entidade", entidade)
                .addFato("fatura", fatura)
                .addFato("valorMinimo", new BigDecimal(100))

                .addClasseRule(faturaRule)
                .addMetodoRule("validarValor")
                .addMetodoRule("validarEntidade")
                .addMetodoRule("validarData")
                
                .addNovoClasseRule(entidadeRule)
                .addMetodoRule("validarNome")
                .addMetodoRule("validarEmail")
                .buildRules();

        Assert.assertTrue(re.check());

    }

}
