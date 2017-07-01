package com.idomine.model;

public class Product
{
    private long codigo;
    private String nome;

    public Product(String string)
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
