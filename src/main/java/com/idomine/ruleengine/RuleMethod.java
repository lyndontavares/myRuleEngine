package com.idomine.ruleengine;

public class RuleMethod
{

    private String nome;
    private long prioridade;

    public String getNome()
    {
        return nome;
    }

    public long getPrioridade()
    {
        return prioridade;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public void setPrioridade(long prioridade)
    {
        this.prioridade = prioridade;
    }

    public RuleMethod(String nome, long prioridade)
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

}