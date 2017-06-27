package com.idomine.ruleengine;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;

import com.idomine.ruleengine.annotations.NotificacaoInfo;
import com.idomine.ruleengine.annotations.NotificacaoWarn;
import com.idomine.ruleengine.helper.RuleEngineHelper;
import com.idomine.ruleengine.notification.Mensagem;
import com.idomine.ruleengine.notification.MensagemTipo;
import com.idomine.ruleengine.notification.Notificacao;

public class RuleEngine
{
    boolean result;
    private String mensagemChecking;
    private String mensagemCheckTrue;
    private String mensagemCheckFalse;
    private List<RuleFact> fatos;
    private List<RuleModel> ruleModel;
    private Class<?> classOutputMesagem;

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

    public boolean check()
    {
        showMensagemChecking();

        result = false;

        for (RuleModel ruleModel : ruleModel)
        {
            RuleEngineHelper.prepareFacts(ruleModel.getRule(), fatos);

            for (String metodoRule : ruleModel.getMetodoRule())
            {
                result = checarNotificacao(RuleEngineHelper.execute(ruleModel.getRule(), metodoRule));
                if (!result)
                    break;
            }
            if (!result)
                break;
        }

        showMensagemCheck();
        return result;
    }

    // mensagemChecking

    private void showMensagemChecking()
    {
        if (mensagemChecking != null)
        {
            showNotificacao(new Mensagem(mensagemChecking, MensagemTipo.INFO));
        }
    }

    // mensagemCheckTrue mensagemCheckFalse

    private void showMensagemCheck()
    {
        if (result && mensagemCheckTrue != null)
        {
            showNotificacao(new Mensagem(mensagemCheckTrue, MensagemTipo.INFO));
        }
        else if (!result && mensagemCheckFalse != null)
        {
            showNotificacao(new Mensagem(mensagemCheckFalse, MensagemTipo.ERRO));
        }
    }

    // Notification

    private boolean checarNotificacao(Object result)
    {
        boolean retorno = false;

        if (result.getClass().equals(ArrayList.class))
        {
            if (!((ArrayList<?>) result).isEmpty())
            {
                if (((ArrayList<?>) result).get(0).getClass().equals(Notificacao.class))
                {
                    @SuppressWarnings("unchecked")
                    List<Notificacao> nots = ((ArrayList<Notificacao>) result);

                    for (Notificacao notificacao : nots)
                    {
                        retorno = notificacao.isResultado();
                        List<Mensagem> mensagens = notificacao.getMensagens();
                        showNoticacoes(mensagens, retorno);
                        if (!retorno)
                            break;
                    }
                }
            }
        }
        else if (result.getClass().equals(Notificacao.class))
        {
            retorno = ((Notificacao) result).isResultado();
            List<Mensagem> mensagens = ((Notificacao) result).getMensagens();
            showNoticacoes(mensagens, retorno);

        }
        else if (result.getClass().equals(Boolean.class))
        {
            retorno = (boolean) result;
        }
        else
        {
            Validate.isTrue(false, ">>>RuleEngine: MetodoRule deve retornar Boolean ou Notification");
        }
        return retorno;
    }

    // show notificacoes

    private void showNotificacao(Mensagem m)
    {
        if (classOutputMesagem != null)
        {
            if ( m.getTipo().equals(MensagemTipo.INFO) || m.getTipo().equals(MensagemTipo.EXPRESSAO_TRUE) )
            {
                RuleEngineHelper. executeNotificacao(classOutputMesagem,m.getTexto(),NotificacaoInfo.class);    
            }
            else if ( m.getTipo().equals(MensagemTipo.ADVERTENCIA))
            {
                RuleEngineHelper. executeNotificacao(classOutputMesagem,m.getTexto(),NotificacaoWarn.class);
            }
            else if ( m.getTipo().equals(MensagemTipo.ERRO) || m.getTipo().equals(MensagemTipo.EXPRESSAO_FALSE))
            {
                RuleEngineHelper. executeNotificacao(classOutputMesagem,m.getTexto(),NotificacaoWarn.class);
            }
        }
        else
        {
            System.out.println("<<" + m.getTipo() + ">> " + m.getTexto());
        }
    }

    private void showNoticacoes(List<Mensagem> mensagens, boolean retorno)
    {
        for (Mensagem m : mensagens)
        {
            if ((retorno && !m.getTipo().equals(MensagemTipo.EXPRESSAO_FALSE)) ||
                    (!retorno && !m.getTipo().equals(MensagemTipo.EXPRESSAO_TRUE)))
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
        InformeMetodo addMetodoRule(String nomeMetodo);
    }

    // step 4

    public interface InformeMetodo
    {
        InformeMetodo addMetodoRule(String nomeMetodo);

        InformeNovoRule addNovoClasseRule(Object rule);

        RuleEngine buildRules();

        boolean fireRules();
    }

    // step 5

    public interface InformeNovoRule
    {
        InformeMetodo addMetodoRule(String nomeMetodo);
    }

    // step 6

    public interface BuildRules
    {
        boolean fireRules();
    }

    // builder

    public static class Builder implements InformeFato, InformeRule, InformeMetodo, InformeNovoRule, BuildRules
    {
        List<RuleFact> fatos;
        List<RuleModel> ruleModels;
        RuleModel ruleModel;
        Object rule;
        List<String> metodoRule;

        public Builder()
        {
            ruleModels = new ArrayList<>();
            fatos = new ArrayList<>();
        }

        @Override
        public InformeFato addFato(String nomeFato, Object objeto)
        {
            RuleFact fato = new RuleFact(nomeFato, objeto);
            fatos.add(fato);
            return this;
        }

        @Override
        public InformeRule addClasseRule(Object rule)
        {
            Validate.notNull(rule, ">>>RuleEngine: Rule n�o pode ser null!");
            iniciarRule(rule);
            return this;
        }

        @Override
        public InformeMetodo addMetodoRule(String nomeMetodo)
        {
            Validate.notNull(nomeMetodo, ">>>RuleEngine: nomeMetodo n�o pode ser null!");
            metodoRule.add(nomeMetodo);
            return this;
        }

        @Override
        public InformeNovoRule addNovoClasseRule(Object rule)
        {
            Validate.notNull(rule, ">>>RuleEngine: Rule n�o pode ser null!");
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
            return re.check();
        }

        private void adicionarRuleModel()
        {
            RuleModel ruleModel = new RuleModel();
            ruleModel.setRule(rule);
            ruleModel.setMetodoRule(metodoRule);
            ruleModels.add(ruleModel);
        }

        private void iniciarRule(Object rule)
        {
            this.rule = rule;
            metodoRule = new ArrayList<>();
        }

    }

}
