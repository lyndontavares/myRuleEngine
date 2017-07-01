package com.idomine.ruleengine;

import static com.idomine.ruleengine.helper.MyRuleReflectionHelper.*;
import static com.idomine.ruleengine.exceptions.ExceptionHelper.checkNull;
import static com.idomine.ruleengine.exceptions.ExceptionHelper.myRuleException;
import static com.idomine.ruleengine.exceptions.ExceptionHelper.*;
import static com.idomine.ruleengine.helper.MyRuleJavaClassHelper.isJavaMethodName;

import java.util.ArrayList;
import java.util.List;

import com.idomine.ruleengine.annotations.NotifyCustom;
import com.idomine.ruleengine.annotations.NotifyError;
import com.idomine.ruleengine.annotations.NotifyInformation;
import com.idomine.ruleengine.annotations.NotifyWarning;
import com.idomine.ruleengine.helper.MyRuleReflectionHelper;
import com.idomine.ruleengine.notification.Message;
import com.idomine.ruleengine.notification.MessageType;
import com.idomine.ruleengine.notification.Notification;

public class RuleEngine
{
    private boolean result;
    private boolean notifications = false;
    private String mensagemChecking;
    private String mensagemCheckTrue;
    private String mensagemCheckFalse;
    private List<RuleFact> fatos;
    private List<RuleModel> ruleModel;
    private Class<?> classOutputMesagem;

    public RuleEngine()
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

    public List<RuleModel> getRuleModel()
    {
        return ruleModel;
    }

    public void setRuleModel(List<RuleModel> ruleModel)
    {
        this.ruleModel = ruleModel;
    }

    public List<RuleFact> getFatos()
    {
        return fatos;
    }

    public void setFatos(List<RuleFact> fatos)
    {
        this.fatos = fatos;
    }

    // add ruleEngine

    public RuleEngine addRuleEngine(RuleEngine ruleEngine)
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

        for (RuleModel ruleModel : ruleModel)
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

    private boolean checarNotificacaoExecutandoMedodo(RuleModel ruleModel)
    {
        List<RuleMethod> metodos = ruleModel.getMetodoRule();
        checkNull(metodos, ruleModel.getRule().getClass().getName());
        metodos=MyRuleReflectionHelper.ordenarPorPrioridade(ruleModel.getRule(), metodos );
        
        for (RuleMethod metodoRule : metodos)
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
        List<RuleMethod> metodos = MyRuleReflectionHelper.metodosNotificaveis(rule);
        checkNull(metodos, rule.getClass().getName());
        metodos=MyRuleReflectionHelper.ordenarPorPrioridade(rule, metodos);

        boolean result = false;
        for (RuleMethod metodo : metodos)
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

        RuleEngine buildRules();
    }

    // step 5

    public interface InformeMetodo
    {
        InformeMetodo addMetodoRule(String nomeMetodo);

        InformeNovoRule addNovoClasseRule(Object rule);

        RuleEngine buildRules();

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
        List<RuleFact> fatos;
        List<RuleModel> ruleModels;
        RuleModel ruleModel;
        Object rule;
        List<RuleMethod> metodoRule;

        public Builder()
        {
            ruleModels = new ArrayList<>();
            fatos = new ArrayList<>();
        }

        @Override
        public InformeFato addFato(String nomeFato, Object objeto)
        {
            verificarNomeValido(nomeFato);
            RuleFact fato = new RuleFact(nomeFato, objeto);
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
            metodoRule.add(new RuleMethod("@all"));
            return this;
        }

        @Override
        public InformeMetodo addMetodoRule(String nomeMetodo)
        {
            verificarNomeValido(nomeMetodo);
            verificarNomeMetodoRepetido(nomeMetodo);
            verificarMetodoNotificavel(nomeMetodo);
            metodoRule.add(new RuleMethod(nomeMetodo));
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
        public RuleEngine buildRules()
        {
            adicionarRuleModel();
            RuleEngine re = new RuleEngine();
            re.setRuleModel(ruleModels);
            re.setFatos(fatos);
            return re;
        }

        @Override
        public boolean fireRules()
        {
            RuleEngine re = new RuleEngine();
            re.setRuleModel(ruleModels);
            re.setFatos(fatos);
            return re.fireRules();
        }

        @Override
        public boolean checkRules()
        {
            RuleEngine re = new RuleEngine();
            re.setRuleModel(ruleModels);
            re.setFatos(fatos);
            return re.checkRules();
        }

        private void adicionarRuleModel()
        {
            if (rule != null)
            {
                RuleModel ruleModel = new RuleModel();
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
