package com.idomine.myruleengine;

public class MyRuleMethod
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

    public MyRuleMethod(String nome, Long prioridade)
    {
        this.nome = nome;
        this.prioridade = prioridade;
    }

    public MyRuleMethod(String nome)
    {
        this.nome = nome;
    }

    public MyRuleMethod()
    {
    }

    @Override
    public String toString()
    {
        return "RuleMethod [nome=" + nome + ", prioridade=" + prioridade + "]";
    }

}
