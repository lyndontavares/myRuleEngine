package com.idomine.ruleengine.notification;

import java.util.ArrayList;
import java.util.List;

public class Notificacao
{
    private boolean resultado;
    private List<Mensagem> mensagens;
    private long prioridade;
    private String notificationContext;

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

    public void setPrioridade(long prioridade)
    {
        this.prioridade = prioridade;
    }

    public Notificacao(String mensagem, MensagemTipo tipo)
    {
        addMensagem(new Mensagem(mensagem, tipo));
    }

    public Notificacao()
    {
        mensagens = new ArrayList<>();
    }

    public String getNotificationContext()
    {
        return notificationContext;
    }

    public void setNotificationContext(String notificationContext)
    {
        this.notificationContext = notificationContext;
    }

    // fluente

    public Notificacao addMensagem(Mensagem mensagem)
    {
        mensagens.add(mensagem);
        return this;
    }

    public Notificacao addMensagemContext(String mensagem)
    {
        mensagens.add(new Mensagem(mensagem, MensagemTipo.CONTEXT));
        return this;
    }

    public Notificacao addMensagemTrue(String mensagem)
    {
        mensagens.add(new Mensagem(mensagem, MensagemTipo.EXPRESSAO_TRUE));
        return this;
    }

    public Notificacao addMensagemFalse(String mensagem)
    {
        mensagens.add(new Mensagem(mensagem, MensagemTipo.EXPRESSAO_FALSE));
        return this;
    }

    public Notificacao addMensagemInfo(String mensagem)
    {
        mensagens.add(new Mensagem(mensagem, MensagemTipo.INFO));
        return this;
    }

    public Notificacao addMensagemAdvertencia(String mensagem)
    {
        mensagens.add(new Mensagem(mensagem, MensagemTipo.ADVERTENCIA));
        return this;
    }

    public Notificacao addMensagemErro(String mensagem)
    {
        mensagens.add(new Mensagem(mensagem, MensagemTipo.ERROR));
        return this;
    }

    public Notificacao expressaoLogica(boolean resultado)
    {
        this.resultado = resultado;
        return this;
    }

}
