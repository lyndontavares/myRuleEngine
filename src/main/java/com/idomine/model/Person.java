package com.idomine.model;

import java.util.List;

public class Person
{

    private Long codigo;
    
    private String nome;
    
    private String email;

    private List<Person> contatos;

    public Long getCodigo()
    {
        return codigo;
    }

    public void setCodigo(Long codigo)
    {
        this.codigo = codigo;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public List<Person> getContatos()
    {
        return contatos;
    }

    public void setContatos(List<Person> contatos)
    {
        this.contatos = contatos;
    }

    public static Person getFake()
    {
        Person entidade = new Person();
        entidade.setNome("Lyndon Tavares");
        entidade.setEmail("lyndon.tavares@datapar.com.py");
        return entidade;
    }
    
    
    
}
