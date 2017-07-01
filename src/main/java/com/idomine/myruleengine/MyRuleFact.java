package com.idomine.myruleengine;

public class MyRuleFact
{

    private String nome;
    private Object valor;
    
    public String getNome()
    {
        return nome;
    }
    public void setNome(String nome)
    {
        this.nome = nome;
    }
    public Object getValor()
    {
        return valor;
    }
    public void setValor(Object valor)
    {
        this.valor = valor;
    }
    public MyRuleFact(String nome, Object valor)
    {
        this.nome = nome;
        this.valor = valor;
    }
    public MyRuleFact()
    {
    }
    
}
