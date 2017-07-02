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
package com.idomine.business;

import static com.idomine.business.helper.LogicHelper.moreThenOrEquals;

import java.math.BigDecimal;

import com.idomine.model.Customer;
import com.idomine.model.Sale;
import com.idomine.myruleengine.annotations.InjectFact;
import com.idomine.myruleengine.annotations.RuleCondition;
import com.idomine.myruleengine.notification.Notification;

public class SaleRule
{
    @InjectFact(name = "sale")
    public Sale fatura;

    @InjectFact(name = "customer")
    private Customer customer;

    @InjectFact(name = "minimal")
    private BigDecimal minimal;

    @RuleCondition(prioridade=1)
    public Notification checkTotal()
    {
        boolean regra = moreThenOrEquals(fatura.getTotal(), minimal);
        return new Notification()
                .condition(regra)
                .addMessageTrue("(1) Total checked")
                .addMessageFalse("(1) Total must be greaterthan equals " + minimal);
 
    }

    @RuleCondition(prioridade=2)
    public Notification checkCustomer()
    {
        boolean regra = fatura.getCustomer()!= null;
        return new Notification()
                .condition(regra)
                .addMessageInfo("(2) Customer checked")
                .addMessageFalse("(2) Customer dont be null");
    }

    @RuleCondition(prioridade=3)
    public Notification checkDate()
    {
        boolean regra = fatura.getDate() != null;

        return new Notification()
                .condition(regra)
                .addMessageInfo("(3) Date checked")
                .addMessageFalse("(3) Date dont be null");
    }

    @RuleCondition(prioridade=4)
    public Notification checkOtherConditions()
    {
        Notification notification = new Notification();
        Notification regraUm = contition1();
        
        notification.setNotificationContext( regraUm.getNotificationContext() );
        notification.setMessages(regraUm.getMessages());
        notification.setResultado(regraUm.isResultado());
        
        if (notification.isResultado())
        {
            notification.setNotificationContext( contition1().getNotificationContext() );
            notification.getMessages().addAll( contition2().getMessages());
            notification.setResultado(contition2().isResultado());
        }
        
        return notification;
    }

    private Notification contition1()
    {
        boolean regra = true;
        return new Notification()
                .condition(regra)
                .addMessageInfo("checking contition 1")
                .addMessageFalse("condition 1 fail")
                .addMessageContext("condition1");
    }

    private Notification contition2()
    {
        boolean regra = true;
        return new Notification()
                .condition(regra)
                .addMessageInfo("checking condition 2")
                .addMessageFalse("conditio 2 fail")
                .addMessageContext("condition2");
    }

}
