package com.idomine.ruleengine;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.idomine.business.NotificationsHelper;
import com.idomine.business.PersonRule;
import com.idomine.business.SaleRule;
import com.idomine.model.Person;
import com.idomine.model.Sale;
import com.idomine.ruleengine.RuleEngine;

public class AddAllRuleTest
{

    @Test
    public void testeRuleEngine()
    {
        // fatos
        Sale fatura = Sale.getFake();
        Person entidade = Person.getFake();
        fatura.setEntidade(entidade);

        // rules
        SaleRule faturaRule = new SaleRule();
        PersonRule entidadeRule = new PersonRule();

        // Engine 1
        RuleEngine re = RuleEngine
                .Builder()
                .addFato("entidade", entidade)
                .addFato("fatura", fatura)
                .addFato("valorMinimo", new BigDecimal(1L))

                .addClasseRule(faturaRule)
                .addAllMetodoRule()

                .addNovoClasseRule(entidadeRule)
                .addMetodoRule("validarEmail")
                .buildRules();

        re.setMensagemCheckTrue("Gravado com sucesso!");
        re.setMensagemCheckFalse("Validacoes falharam!");
        re.setMensagemChecking("Checando validacoes!");
        re.setClassOutputMesagem(NotificationsHelper.class);

        boolean res = re.fireRules();
        System.out.println(">>> engine result " + res);

        Assert.assertTrue(res);

    }

}
