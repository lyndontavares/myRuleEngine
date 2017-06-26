package com.idomine.notification;

import java.util.ArrayList;
import java.util.List;

public class Notificacao
{
    private boolean resultado;
    private List<Mensagem> mensagens;
    private long prioridade;
    private String elementoToFocus;

    public boolean isResultado()
    {
        return resultado;
    }

    public List<Mensagem> getMensagens()
    {
        return mensagens;
    }

    public void setResultado(boolean resultado)
    {
        this.resultado = resultado;
    }

    public void setMensagens(List<Mensagem> mensagens)
    {
        this.mensagens = mensagens;
    }

    public long getPrioridade()
    {
        return prioridade;
    }

    public String getElementoToFocus()
    {
        return elementoToFocus;
    }

    public void setPrioridade(long prioridade)
    {
        this.prioridade = prioridade;
    }

    public void setElementoToFocus(String elementoToFocus)
    {
        this.elementoToFocus = elementoToFocus;
    }

    public Notificacao(String mensagem, MensagemTipo tipo)
    {
        addMensagem(new Mensagem(mensagem,tipo));
    }

    public Notificacao()
    {
        mensagens = new ArrayList<>();
    }

    public Notificacao addMensagem(Mensagem mensagem)
    {
        mensagens.add(mensagem);
        return this;
    }

}
