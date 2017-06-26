package com.idomine.notification;

public class Mensagem
{
    private String texto;
    private MensagemTipo tipo;
    
    public String getTexto()
    {
        return texto;
    }
    public MensagemTipo getTipo()
    {
        return tipo;
    }
    public void setTexto(String texto)
    {
        this.texto = texto;
    }
    public void setTipo(MensagemTipo tipo)
    {
        this.tipo = tipo;
    }
    public Mensagem(String texto, MensagemTipo tipo)
    {
        super();
        this.texto = texto;
        this.tipo = tipo;
    }

}


