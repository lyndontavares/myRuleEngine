package com.idomine.teste;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.idomine.model.Entidade;
import com.idomine.model.Fatura;
import com.idomine.model.rules.EntidadeRule;
import com.idomine.model.rules.FaturaRule;
import com.idomine.model.rules.NotificacaoHelper;
import com.idomine.ruleengine.RuleEngine;

public class TesteAddAllRuleRuleEngineBuider
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

        // Engine 1
        RuleEngine re = RuleEngine
                .Builder()
                .addFato("entidade", entidade)
                .addFato("fatura", fatura)
                .addFato("valorMinimo", new BigDecimal(11))

                .addClasseRule(faturaRule)
                .addAllMetodoRule()

                .addNovoClasseRule(entidadeRule)
                .addAllMetodoRule()
                .buildRules();

        re.setMensagemCheckTrue("Gravado com sucesso!");
        re.setMensagemCheckFalse("Validacoes falharam!");
        re.setMensagemChecking("Checando validacoes!");
        re.setClassOutputMesagem(NotificacaoHelper.class);

        boolean res = re.check();
        System.out.println(">>> engine result " + res);

        Assert.assertTrue(res);

    }

}
