package com.idomine.ruleengine;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.idomine.business.NotificationsHelper;
import com.idomine.business.PersonRule;
import com.idomine.business.ProductRule;
import com.idomine.business.SaleRule;
import com.idomine.model.Person;
import com.idomine.model.Sale;
import com.idomine.model.Product;
import com.idomine.ruleengine.RuleEngine;

public class RuleEngineBuiderTest
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
        ProductRule mercadoriaRule = new ProductRule();

        // RuleEngine 1
        RuleEngine re1 = new RuleEngine();
        re1.setMensagemCheckTrue("Gravado com sucesso!");
        re1.setMensagemCheckFalse("Validacoes falharam!");
        re1.setMensagemChecking("Checando validacoes!");
        re1.setClassOutputMesagem(NotificationsHelper.class);

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
                .addFato("mercadoria", new Product("merc 1"))
                .addClasseRule(mercadoriaRule)
                .addMetodoRule("validarNomeMercadoria")
                .buildRules();

        // RuleEngine 1 + 2 + 3
        re1.addRuleEngine(re2).addRuleEngine(re3);

        boolean res = re1.fireRules();
        System.out.println(">>> engine result " + res);

        Assert.assertTrue(res);

    }

}
