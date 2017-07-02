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
import com.idomine.business.SaleRule;
import com.idomine.model.Customer;
import com.idomine.model.Sale;
import com.idomine.myruleengine.MyRuleEngine;

public class RuleEngineBuiderTest
{

    @Test
    public void testeRuleEngine()
    {

        // facts
        Sale sale = Sale.getFake();
        Customer customer = Customer.getFake();
        sale.setCustomer(customer);

        // rules
        SaleRule saleRule = new SaleRule();
        PersonRule customerRule = new PersonRule();

        // RuleEngine 1
        MyRuleEngine re1 = new MyRuleEngine();
        re1.setMensagemCheckTrue("Success!");
        re1.setMensagemCheckFalse("Validations fails!");
        re1.setMensagemChecking("Checking rules...");
        
        // send messages to primefaces (see tutorial)
        re1.setClassOutputMesagem(NotificationsHelper.class);

        //RuleEngine 2
        MyRuleEngine re2 = MyRuleEngine
                .Builder()
                .addFato("customer", customer)
                .addFato("sale", sale)
                .addFato("minimal", new BigDecimal(11))

                .addClasseRule(saleRule)
                .addMetodoRule("checkTotal")
                .addMetodoRule("checkCustomer")
                .addMetodoRule("checkDate")
                .addMetodoRule("checkOtherConditions")

                .addNovoClasseRule(customerRule)
                .addMetodoRule("checkName")
                .addMetodoRule("checkEmail")
                .buildRules();

        // RuleEngine 1 + 2
        re1.addRuleEngine(re2);

        boolean res = re1.fireRules();
        System.out.println(">>> engine result " + res);

        Assert.assertTrue(res);

    }

}
