package com.idomine.ruleengine;

import java.util.ArrayList;
import java.util.List;

import com.idomine.ruleengine.helper.RuleEngineHelper;

public class RuleEngine
{
    boolean result;
    private List<Fato> fatos;
    private List<RuleModel> ruleModel;

    public List<RuleModel> getRuleModel()
    {
        return ruleModel;
    }

    public void setRuleModel(List<RuleModel> ruleModel)
    {
        this.ruleModel = ruleModel;
    }

    public List<Fato> getFatos()
    {
        return fatos;
    }

    public void setFatos(List<Fato> fatos)
    {
        this.fatos = fatos;
    }

    //

    public boolean check()
    {
        result = false;

        for (RuleModel ruleModel : ruleModel)
        {
            RuleEngineHelper.prepareFacts(ruleModel.getRule(), fatos);

            for (String metodoRule : ruleModel.getMetodoRule())
            {
                result = RuleEngineHelper.execute(ruleModel.getRule(), metodoRule);
                ruleModel.getRule().setLastResult(result);

                if (!result)
                    break;
            }
            if (!result)
                break;
        }
        callNotifications();
        return result;
    }

    // Notifications

    private void callNotifications()
    {
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

        InformeRule addClasseRule(Rule rule);
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

        InformeNovoRule addNovoClasseRule(Rule rule);

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
        List<Fato> fatos;
        List<RuleModel> ruleModels;
        RuleModel ruleModel;
        Rule rule;
        List<String> metodoRule;

        public Builder()
        {
            ruleModels = new ArrayList<>();
            fatos = new ArrayList<>();
        }

        @Override
        public InformeFato addFato(String nomeFato, Object objeto)
        {
            Fato fato = new Fato(nomeFato, objeto);
            fatos.add(fato);
            return this;
        }

        @Override
        public InformeRule addClasseRule(Rule rule)
        {
            iniciarRule(rule);
            return this;
        }

        @Override
        public InformeMetodo addMetodoRule(String nomeMetodo)
        {
            metodoRule.add(nomeMetodo);
            return this;
        }

        @Override
        public InformeNovoRule addNovoClasseRule(Rule rule)
        {
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

        private void iniciarRule(Rule rule)
        {
            this.rule = rule;
            this.rule.setFatos(new ArrayList<>());
            metodoRule = new ArrayList<>();
        }

    }

}
