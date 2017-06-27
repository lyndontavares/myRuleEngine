package com.idomine.model;

public class Mercadoria
{
    private long codigo;
    private String nome;

    public Mercadoria(String string)
    {
        nome = string;
    }

    public long getCodigo()
    {
        return codigo;
    }

    public String getNome()
    {
        return nome;
    }

    public void setCodigo(long codigo)
    {
        this.codigo = codigo;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

}
