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
package com.idomine.business;

import static com.idomine.business.helper.LogicHelper.moreThenOrEquals;

import java.math.BigDecimal;

import com.idomine.model.Person;
import com.idomine.model.Sale;
import com.idomine.myruleengine.annotations.InjectFact;
import com.idomine.myruleengine.annotations.RuleCondition;
import com.idomine.myruleengine.notification.Notification;

public class SaleRule
{
    @InjectFact(name = "fatura")
    public Sale fatura;

    @InjectFact(name = "entidade")
    private Person entidade;

    @InjectFact(name = "valorMinimo")
    private BigDecimal valor;

    @RuleCondition(prioridade=1)
    public Notification validarValor()
    {
        boolean regra = moreThenOrEquals(fatura.getValor(), valor);
        return new Notification()
                .expressaoLogica(regra)
                .addMensagemInfo("1 Checando valor fatura")
                //.addMensagemInfo("1 Valor fatura dentro do limite de credito!")
                //.addMensagemAdvertencia("Teste msg warning!")
                //.addMensagemErro("1 Teste msg erro!")
                //.addMensagemTrue("1 Valor fatura passou na checagem")
                .addMensagemFalse("1 valor fatura deve ser maior ou igual a " + valor);
 
    }

    @RuleCondition(prioridade=2)
    public Notification validarEntidade()
    {
        boolean regra = fatura.getEntidade() != null;
        return new Notification()
                .expressaoLogica(regra)
                .addMensagemInfo("2 Checando Entidade fatura")
                .addMensagemFalse("2 Informe uma entidade para fatura.");
    }

    @RuleCondition(prioridade=3)
    public Notification validarData()
    {
        boolean regra = fatura.getEmissao() != null;

        return new Notification()
                .expressaoLogica(regra)
                .addMensagemInfo("2 Checando Data fatura")
                .addMensagemFalse("3 Informe data emissao da fatura");
    }

    @RuleCondition(prioridade=4)
    public Notification validarListaRegras()
    {
        Notification notificacao = new Notification();
        Notification regraUm = regraUm();
        
        //criar metodos fluente para rule
        notificacao.setNotificationContext( regraUm.getNotificationContext() );
        notificacao.setMensagens(regraUm.getMensagens());
        notificacao.setResultado(regraUm.isResultado());
        
        if (notificacao.isResultado())
        {
            notificacao.setNotificationContext( regraDois().getNotificationContext() );
            notificacao.getMensagens().addAll( regraDois().getMensagens());
            notificacao.setResultado(regraDois().isResultado());
        }
        
        return notificacao;
    }

    private Notification regraUm()
    {
        boolean regra = true;
        return new Notification()
                .expressaoLogica(regra)
                .addMensagemInfo("4 Regra 1 checada")
                .addMensagemFalse("4 Regra 1 da fatura falhou");
    }

    private Notification regraDois()
    {
        boolean regra = true;
        return new Notification()
                .expressaoLogica(regra)
                .addMensagemInfo("5 Regra 2 checada")
                .addMensagemFalse("5 Regra 2 da fatura falhou")
                .addMensagemContext("regradois");
    }

}
