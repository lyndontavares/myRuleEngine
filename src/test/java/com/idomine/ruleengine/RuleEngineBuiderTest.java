/**
 * The MIT License
 *
 *  Copyright (c) 2017, Lyndon Tavares (integraldominio@gmail.com)
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */
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
import com.idomine.myruleengine.MyRuleEngine;
import com.idomine.model.Product;

public class RuleEngineBuiderTest
{

    @Test
    public void testeRuleEngine()
    {

        // facts
        Sale fatura = Sale.getFake();
        Person entidade = Person.getFake();
        fatura.setEntidade(entidade);

        // rules
        SaleRule faturaRule = new SaleRule();
        PersonRule entidadeRule = new PersonRule();
        ProductRule mercadoriaRule = new ProductRule();

        // RuleEngine 1
        MyRuleEngine re1 = new MyRuleEngine();
        re1.setMensagemCheckTrue("Gravado com sucesso!");
        re1.setMensagemCheckFalse("Validacoes falharam!");
        re1.setMensagemChecking("Checando validacoes!");
        re1.setClassOutputMesagem(NotificationsHelper.class);

        //RuleEngine 2
        MyRuleEngine re2 = MyRuleEngine
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
        MyRuleEngine re3 = MyRuleEngine
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
