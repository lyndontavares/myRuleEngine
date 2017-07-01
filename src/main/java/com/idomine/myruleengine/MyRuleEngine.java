
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

package com.idomine.myruleengine;

import static com.idomine.myruleengine.exceptions.ExceptionHelper.*;
import static com.idomine.myruleengine.helper.MyRuleJavaClassHelper.isJavaMethodName;
import static com.idomine.myruleengine.helper.MyRuleReflectionHelper.*;

import java.util.ArrayList;
import java.util.List;

import com.idomine.myruleengine.annotations.NotifyCustom;
import com.idomine.myruleengine.annotations.NotifyError;
import com.idomine.myruleengine.annotations.NotifyInformation;
import com.idomine.myruleengine.annotations.NotifyWarning;
import com.idomine.myruleengine.helper.MyRuleReflectionHelper;
import com.idomine.myruleengine.notification.Message;
import com.idomine.myruleengine.notification.MessageType;
import com.idomine.myruleengine.notification.Notification;

public class MyRuleEngine
{
    private boolean result;
    private boolean notifications = false;
    private String mensagemChecking;
    private String mensagemCheckTrue;
    private String mensagemCheckFalse;
    private List<MyRuleFact> fatos;
    private List<MyRuleModel> ruleModel;
    private Class<?> classOutputMesagem;

    public MyRuleEngine() 
    {
        fatos = new ArrayList<>();
        ruleModel = new ArrayList<>();
    }

    public boolean isResult()
    {
        return result;
    }

    public void setNotifications(boolean notifications)
    {
        this.notifications = notifications;
    }

    public boolean isNotifications()
    {
        return notifications;
    }

    public Class<?> getClassOutputMesagem()
    {
        return classOutputMesagem;
    }

    public void setClassOutputMesagem(Class<?> classOutputMesagem)
    {
        this.classOutputMesagem = classOutputMesagem;
    }

    public String getMensagemChecking()
    {
        return mensagemChecking;
    }

    public void setMensagemChecking(String mensagemChecking)
    {
        this.mensagemChecking = mensagemChecking;
    }

    public String getMensagemCheckTrue()
    {
        return mensagemCheckTrue;
    }

    public void setMensagemCheckTrue(String mensagemCheckTrue)
    {
        this.mensagemCheckTrue = mensagemCheckTrue;
    }

    public String getMensagemCheckFalse()
    {
        return mensagemCheckFalse;
    }

    public void setMensagemCheckFalse(String mensagemCheckFalse)
    {
        this.mensagemCheckFalse = mensagemCheckFalse;
    }

    public List<MyRuleModel> getRuleModel()
    {
        return ruleModel;
    }

    public void setRuleModel(List<MyRuleModel> ruleModel)
    {
        this.ruleModel = ruleModel;
    }

    public List<MyRuleFact> getFatos()
    {
        return fatos;
    }

    public void setFatos(List<MyRuleFact> fatos)
    {
        this.fatos = fatos;
    }

    // add ruleEngine

    public MyRuleEngine addRuleEngine(MyRuleEngine ruleEngine)
    {
        if (!ruleEngine.getFatos().isEmpty())
            this.getFatos().addAll(ruleEngine.getFatos());
        if (!ruleEngine.getRuleModel().isEmpty())
            this.getRuleModel().addAll(ruleEngine.getRuleModel());
        return this;
    }

    // check

    public boolean fireRules()
    {
        setNotifications(true);
        return runRules();
    }

    public boolean checkRules()
    {
        setNotifications(false);
        return runRules();
    }

    private boolean runRules()
    {
        showMensagemChecking();

        result = false;

        for (MyRuleModel ruleModel : ruleModel)
        {
            MyRuleReflectionHelper.prepareFacts(ruleModel.getRule(), fatos);

            if (ruleModel.getMetodoRule().get(0).getNome().equals("@all"))
            {
                result = checarNotificacaoExecutantoAllMetodos(ruleModel.getRule());
            }
            else
            {
                result = checarNotificacaoExecutandoMedodo(ruleModel);
            }
            if (!result)
            {
                break;
            }
        }

        showMensagemCheck();
        return result;
    }

    // mensagemChecking

    private void showMensagemChecking()
    {
        showNotificacao(new Message(mensagemChecking, MessageType.INFO));
    }

    // mensagemCheckTrue mensagemCheckFalse

    private void showMensagemCheck()
    {

        if (result && mensagemCheckTrue != null)
        {
            showNotificacao(new Message(mensagemCheckTrue, MessageType.INFO));
        }
        else if (!result && mensagemCheckFalse != null)
        {
            showNotificacao(new Message(mensagemCheckFalse, MessageType.ERROR));
        }

    }

    // Notification

    private boolean checarNotificacaoExecutandoMedodo(MyRuleModel ruleModel)
    {
        List<MyRuleMethod> metodos = ruleModel.getMetodoRule();
        checkNull(metodos, ruleModel.getRule().getClass().getName());
        metodos=MyRuleReflectionHelper.ordenarPorPrioridade(ruleModel.getRule(), metodos );
        
        for (MyRuleMethod metodoRule : metodos)
        {
            result = checarNotificacao(MyRuleReflectionHelper.execute(ruleModel.getRule(), metodoRule.getNome()));
            if (!result)
            {
                break;
            }
        }
        return result;
    }

    private boolean checarNotificacaoExecutantoAllMetodos(Object rule)
    {
        List<MyRuleMethod> metodos = MyRuleReflectionHelper.metodosNotificaveis(rule);
        checkNull(metodos, rule.getClass().getName());
        metodos=MyRuleReflectionHelper.ordenarPorPrioridade(rule, metodos);

        boolean result = false;
        for (MyRuleMethod metodo : metodos)
        {
            result = checarNotificacao(MyRuleReflectionHelper.execute(rule, metodo.getNome()));
            if (!result)
            {
                break;
            }
        }
        return result;
    }

    private boolean checarNotificacao(Object notificacao)
    {
        if (notificacao.getClass().equals(Notification.class))
        {
            result = ((Notification) notificacao).isResultado();
            List<Message> mensagens = ((Notification) notificacao).getMensagens();
            showNoticacoes(mensagens, result);
        }
        else
        {
            myRuleException("MetodoRule deve retornar Notificaticacao");
        }
        return result;
    }

    // show notificacoes

    private void showNotificacao(Message m)
    {
        if (isNotifications())
        {
            if (classOutputMesagem != null)
            {
                if (m.getTipo().equals(MessageType.INFO) || m.getTipo().equals(MessageType.EXPRESSAO_TRUE))
                {
                    MyRuleReflectionHelper.executeNotificacao(classOutputMesagem, m.getTexto(), NotifyInformation.class);
                }
                else if (m.getTipo().equals(MessageType.ADVERTENCIA))
                {
                    MyRuleReflectionHelper.executeNotificacao(classOutputMesagem, m.getTexto(), NotifyWarning.class);
                }
                else if (m.getTipo().equals(MessageType.ERROR) || m.getTipo().equals(MessageType.EXPRESSAO_FALSE))
                {
                    MyRuleReflectionHelper.executeNotificacao(classOutputMesagem, m.getTexto(), NotifyError.class);
                }
                else if ( !isResult() && m.getTipo().equals(MessageType.CONTEXT))
                {
                    MyRuleReflectionHelper.executeNotificacao(classOutputMesagem, m.getTexto(), NotifyCustom.class);
                }
            }
            else
            {
                System.out.println("<<" + m.getTipo() + ">> " + m.getTexto());
            }
        }
    }

    private void showNoticacoes(List<Message> mensagens, boolean retorno)
    {
        if (isNotifications())
            for (Message m : mensagens)
            {
                if ((retorno && !m.getTipo().equals(MessageType.EXPRESSAO_FALSE)) ||
                        (!retorno && !m.getTipo().equals(MessageType.EXPRESSAO_TRUE)))
                {
                    showNotificacao(m);
                }
            }
    }

    // step 1

    public static InformeFato Builder()
    {
        return new Builder();
    }

    // step 2

    public interface InformeFato
    {
        InformeFato addFato(String nomeFato, Object objeto);

        InformeRule addClasseRule(Object rule);
    }

    // step 3

    public interface InformeRule
    {
        InformeAllRule addAllMetodoRule();

        InformeMetodo addMetodoRule(String nomeMetodo);
    }

    // step 4

    public interface InformeAllRule
    {
        InformeNovoRule addNovoClasseRule(Object rule);

        MyRuleEngine buildRules();
    }

    // step 5

    public interface InformeMetodo
    {
        InformeMetodo addMetodoRule(String nomeMetodo);

        InformeNovoRule addNovoClasseRule(Object rule);

        MyRuleEngine buildRules();

        boolean fireRules();

        boolean checkRules();
    }

    // step 6

    public interface InformeNovoRule
    {
        InformeAllRule addAllMetodoRule();

        InformeMetodo addMetodoRule(String nomeMetodo);
    }

    // step 7

    public interface BuildRules
    {
        boolean fireRules();

        boolean checkRules();
    }

    // builder

    public static class Builder
            implements InformeFato, InformeRule, InformeMetodo, InformeNovoRule, BuildRules, InformeAllRule
    {
        List<MyRuleFact> fatos;
        List<MyRuleModel> ruleModels;
        MyRuleModel ruleModel;
        Object rule;
        List<MyRuleMethod> metodoRule;

        public Builder()
        {
            ruleModels = new ArrayList<>();
            fatos = new ArrayList<>();
        }

        @Override
        public InformeFato addFato(String nomeFato, Object objeto)
        {
            verificarNomeValido(nomeFato);
            MyRuleFact fato = new MyRuleFact(nomeFato, objeto);
            fatos.add(fato);
            return this;
        }

        @Override
        public InformeRule addClasseRule(Object rule)
        {
            checkNull(rule, "Rule nao pode ser null!");
            iniciarRule(rule);
            return this;
        }

        @Override
        public InformeAllRule addAllMetodoRule()
        {
            metodoRule.add(new MyRuleMethod("@all"));
            return this;
        }

        @Override
        public InformeMetodo addMetodoRule(String nomeMetodo)
        {
            verificarNomeValido(nomeMetodo);
            verificarNomeMetodoRepetido(nomeMetodo);
            verificarMetodoNotificavel(nomeMetodo);
            metodoRule.add(new MyRuleMethod(nomeMetodo));
            return this;
        }

        @Override
        public InformeNovoRule addNovoClasseRule(Object rule)
        {
            checkNull(rule, "Rule nao pode ser null!");
            adicionarRuleModel();
            iniciarRule(rule);
            return this;
        }

        @Override
        public MyRuleEngine buildRules()
        {
            adicionarRuleModel();
            MyRuleEngine re = new MyRuleEngine();
            re.setRuleModel(ruleModels);
            re.setFatos(fatos);
            return re;
        }

        @Override
        public boolean fireRules()
        {
            MyRuleEngine re = new MyRuleEngine();
            re.setRuleModel(ruleModels);
            re.setFatos(fatos);
            return re.fireRules();
        }

        @Override
        public boolean checkRules()
        {
            MyRuleEngine re = new MyRuleEngine();
            re.setRuleModel(ruleModels);
            re.setFatos(fatos);
            return re.checkRules();
        }

        private void adicionarRuleModel()
        {
            if (rule != null)
            {
                MyRuleModel ruleModel = new MyRuleModel();
                ruleModel.setRule(rule);
                ruleModel.setMetodoRule(metodoRule);
                ruleModels.add(ruleModel);
            }
        }

        private void iniciarRule(Object rule)
        {
            this.rule = rule;
            metodoRule = new ArrayList<>();
        }

        private void verificarNomeValido(String nomeMetodo)
        {
            if (nomeMetodo == null || !isJavaMethodName(nomeMetodo))
            {
                myRuleMethodNameException(nomeMetodo);
            }
        }

        private void verificarNomeMetodoRepetido(String nomeMetodo)
        {
            if (metodoRule.indexOf((Object) nomeMetodo) > -1)
            {
                myRuleMethodNameRepetitionException(nomeMetodo);
            }
        }

        private void verificarMetodoNotificavel(String nomeMetodo)
        {
            checkIsTrue(metodoNotificavel(rule, nomeMetodo),
                    "Metodo [" + nomeMetodo + "] nao declarado  em " + rule.getClass());
        }

    }

}
