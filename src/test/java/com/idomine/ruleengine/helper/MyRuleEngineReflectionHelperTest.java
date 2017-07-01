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
package com.idomine.ruleengine.helper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.idomine.business.SaleRule;
import com.idomine.model.Person;
import com.idomine.model.Sale;
import com.idomine.myruleengine.MyRuleFact;
import com.idomine.myruleengine.helper.MyRuleReflectionHelper;

public class MyRuleEngineReflectionHelperTest
{

    @Test
    public void testeAnotacao()
    {
        SaleRule faturaRule = new SaleRule();
        
        List<MyRuleFact> fatos = new ArrayList<>();
        fatos.add(new MyRuleFact("fatura", Sale.getFake()));
        fatos.add(new MyRuleFact("entidade",Person.getFake()));
        fatos.add(new MyRuleFact("valorMinimo",BigDecimal.TEN));

        MyRuleReflectionHelper.prepareFacts(faturaRule, fatos); 
         Assert.assertTrue(faturaRule.fatura.getCodigo() == 1);
        
    }

}
