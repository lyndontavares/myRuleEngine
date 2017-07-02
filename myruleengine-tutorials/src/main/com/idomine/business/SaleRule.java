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
                .expressaoLogica(regra)
                .addMensagemInfo("1 Checando valor fatura")
                .addMensagemFalse("1 valor fatura deve ser maior ou igual a " + minimal);
 
    }

    @RuleCondition(prioridade=2)
    public Notification checkCustomer()
    {
        boolean regra = fatura.getCustomer()!= null;
        return new Notification()
                .expressaoLogica(regra)
                .addMensagemInfo("2 Checando Entidade fatura")
                .addMensagemFalse("2 Informe uma entidade para fatura.");
    }

    @RuleCondition(prioridade=3)
    public Notification checkDate()
    {
        boolean regra = fatura.getDate() != null;

        return new Notification()
                .expressaoLogica(regra)
                .addMensagemInfo("2 Checando Data fatura")
                .addMensagemFalse("3 Informe data emissao da fatura");
    }

    @RuleCondition(prioridade=4)
    public Notification checkOtherConditions()
    {
        Notification notificacao = new Notification();
        Notification regraUm = contition1();
        
        //criar metodos fluente para rule
        notificacao.setNotificationContext( regraUm.getNotificationContext() );
        notificacao.setMensagens(regraUm.getMensagens());
        notificacao.setResultado(regraUm.isResultado());
        
        if (notificacao.isResultado())
        {
            notificacao.setNotificationContext( contition1().getNotificationContext() );
            notificacao.getMensagens().addAll( contition2().getMensagens());
            notificacao.setResultado(contition2().isResultado());
        }
        
        return notificacao;
    }

    private Notification contition1()
    {
        boolean regra = true;
        return new Notification()
                .expressaoLogica(regra)
                .addMensagemInfo("checking contition 1")
                .addMensagemFalse("condition 1 fail")
                .addMensagemContext("condition1");
    }

    private Notification contition2()
    {
        boolean regra = true;
        return new Notification()
                .expressaoLogica(regra)
                .addMensagemInfo("checking condition 2")
                .addMensagemFalse("conditio 2 fail")
                .addMensagemContext("condition2");
    }

}
