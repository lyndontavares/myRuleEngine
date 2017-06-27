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
        //entidade.setEmail(null);
        fatura.setEntidade(entidade);

        // rules
        FaturaRule faturaRule = new FaturaRule();
        EntidadeRule entidadeRule = new EntidadeRule();

        //Engine
        RuleEngine re = RuleEngine
                .Builder()
                .addFato("entidade", entidade)
                .addFato("fatura", fatura)
                .addFato("valorMinimo", new BigDecimal(11))

                .addClasseRule(faturaRule)
                .addMetodoRule("validarValor")
                .addMetodoRule("validarEntidade")
                .addMetodoRule("validarData")
                .addMetodoRule("validarListaRegras")
                
                .addNovoClasseRule(entidadeRule)
                .addMetodoRule("validarNome")
                .addMetodoRule("validarEmail")
                .buildRules();

        re.setMensagemCheckTrue("Gravado com sucesso!");
        re.setMensagemCheckFalse("Validações falharam!");
        re.setMensagemChecking("Checando validações!");


        boolean res  = re.check();
        System.out.println(">>> engine result "+res);
        
        Assert.assertTrue(res);

    }

}
