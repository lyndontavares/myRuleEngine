package com.idomine.myruleengine.notification;

public class Message
{
    private String texto;
    private MessageType tipo;
    
    public String getTexto()
    {
        return texto;
    }
    public MessageType getTipo()
    {
        return tipo;
    }
    public void setTexto(String texto)
    {
        this.texto = texto;
    }
    public void setTipo(MessageType tipo)
    {
        this.tipo = tipo;
    }
    public Message(String texto, MessageType tipo)
    {
        this.texto = texto;
        this.tipo = tipo;
    }
    @Override
    public String toString()
    {
        return "<<"+getTipo()+">>"+getTexto();
    }

    
    
}


