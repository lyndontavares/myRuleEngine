package com.idomine.ruleengine;

public class RuleMethod
{

    private String nome;
    private long prioridade;

    public String getNome()
    {
        return nome;
    }

    public Long getPrioridade()
    {
        return prioridade;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public void setPrioridade(Long prioridade)
    {
        this.prioridade = prioridade;
    }

    public RuleMethod(String nome, Long prioridade)
    {
        this.nome = nome;
        this.prioridade = prioridade;
    }

    public RuleMethod(String nome)
    {
        this.nome = nome;
    }

    public RuleMethod()
    {
    }

    @Override
    public String toString()
    {
        return "RuleMethod [nome=" + nome + ", prioridade=" + prioridade + "]";
    }

}
