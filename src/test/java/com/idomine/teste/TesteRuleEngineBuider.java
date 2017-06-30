package com.idomine.teste;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.idomine.model.Entidade;
import com.idomine.model.Fatura;
import com.idomine.model.Mercadoria;
import com.idomine.model.rules.EntidadeRule;
import com.idomine.model.rules.FaturaRule;
import com.idomine.model.rules.MercadoriaRule;
import com.idomine.model.rules.NotificacaoHelper;
import com.idomine.ruleengine.RuleEngine;

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
        MercadoriaRule mercadoriaRule = new MercadoriaRule();

        // RuleEngine 1
        RuleEngine re1 = new RuleEngine();
        re1.setMensagemCheckTrue("Gravado com sucesso!");
        re1.setMensagemCheckFalse("Validacoes falharam!");
        re1.setMensagemChecking("Checando validacoes!");
        re1.setClassOutputMesagem(NotificacaoHelper.class);

        //RuleEngine 2
        RuleEngine re2 = RuleEngine
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

        // RuleEngine 3
        RuleEngine re3 = RuleEngine
                .Builder()
                .addFato("mercadoria", new Mercadoria("merc 1"))
                .addClasseRule(mercadoriaRule)
                .addMetodoRule("validarNomeMercadoria")
                .buildRules();

        // RuleEngine 1 + 2 + 3
        re1.addRuleEngine(re2).addRuleEngine(re3);

        boolean res = re1.checkRules();
        System.out.println(">>> engine result " + res);

        Assert.assertTrue(res);

    }

}
