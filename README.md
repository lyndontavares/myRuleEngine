# myRuleEngine

MyRule Engine is a Java rules engine inspired in EasyRule.

[![MIT license](http://img.shields.io/badge/license-MIT-brightgreen.svg?style=flat)](http://opensource.org/licenses/MIT)

## Core features

 * Lightweight library and easy to learn API
 * POJO based development with annotation programming model
 * Useful abstractions to define business rules and apply them easily with Java

## Example

##### First, define your rule..

```java

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
                .addMessageInfo("(2) Date checked")
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

```

##### Then, fire it!

```java

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


```

## License
myRuleEngine is released under the terms of the MIT license:

```
The MIT License (MIT)

Copyright (c) 2017, Lyndon Tavares (integraldominio@gmail.com)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
```
