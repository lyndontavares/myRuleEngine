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
        // entidade.setEmail(null);
        fatura.setEntidade(entidade);

        // rules
        FaturaRule faturaRule = new FaturaRule();
        EntidadeRule entidadeRule = new EntidadeRule();
        MercadoriaRule mercadoriaRule = new MercadoriaRule();

        // Engine 1
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
        re.setMensagemCheckFalse("Validacoes falharam!");
        re.setMensagemChecking("Checando validacoes!");
        re.setClassOutputMesagem(NotificacaoHelper.class);

        // Engine 2
        RuleEngine re2 = RuleEngine
                .Builder()
                .addFato("mercadoria", new Mercadoria("merc 1"))
                .addClasseRule(mercadoriaRule)
                .addMetodoRule("validarNomeMercadoria")
                .buildRules();

        // Engine 1 + 2
        re.addRuleEngine(re2);

        boolean res = re.check();
        System.out.println(">>> engine result " + res);

        Assert.assertTrue(res);

    }

}
