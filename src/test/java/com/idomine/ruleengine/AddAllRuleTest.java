/**
 * The MIT License
 *
 *  Copyright (c) 2017, Mahmoud Ben Hassine (mahmoud.benhassine@icloud.com)
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
import com.idomine.business.SaleRule;
import com.idomine.model.Person;
import com.idomine.model.Sale;
import com.idomine.myruleengine.MyRuleEngine;

public class AddAllRuleTest
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

        // Engine 1
        MyRuleEngine re = MyRuleEngine
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
