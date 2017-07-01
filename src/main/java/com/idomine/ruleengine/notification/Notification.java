package com.idomine.ruleengine.notification;

import java.util.ArrayList;
import java.util.List;

public class Notification
{
    private boolean resultado;
    private List<Message> mensagens;
    private long prioridade;
    private String notificationContext;

    public boolean isResultado()
    {
        return resultado;
    }

    public List<Message> getMensagens()
    {
        return mensagens;
    }

    public void setResultado(boolean resultado)
    {
        this.resultado = resultado;
    }

    public void setMensagens(List<Message> mensagens)
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

    public Notification(String mensagem, MessageType tipo)
    {
        addMensagem(new Message(mensagem, tipo));
    }

    public Notification()
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

    public Notification addMensagem(Message mensagem)
    {
        mensagens.add(mensagem);
        return this;
    }

    public Notification addMensagemContext(String mensagem)
    {
        mensagens.add(new Message(mensagem, MessageType.CONTEXT));
        return this;
    }

    public Notification addMensagemTrue(String mensagem)
    {
        mensagens.add(new Message(mensagem, MessageType.EXPRESSAO_TRUE));
        return this;
    }

    public Notification addMensagemFalse(String mensagem)
    {
        mensagens.add(new Message(mensagem, MessageType.EXPRESSAO_FALSE));
        return this;
    }

    public Notification addMensagemInfo(String mensagem)
    {
        mensagens.add(new Message(mensagem, MessageType.INFO));
        return this;
    }

    public Notification addMensagemAdvertencia(String mensagem)
    {
        mensagens.add(new Message(mensagem, MessageType.ADVERTENCIA));
        return this;
    }

    public Notification addMensagemErro(String mensagem)
    {
        mensagens.add(new Message(mensagem, MessageType.ERROR));
        return this;
    }

    public Notification expressaoLogica(boolean resultado)
    {
        this.resultado = resultado;
        return this;
    }

}
