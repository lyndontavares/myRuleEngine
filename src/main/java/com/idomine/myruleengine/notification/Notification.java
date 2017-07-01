/**
 * The MIT License
 *
 *  Copyright (c) 2017, Mahmoud Ben Hassine (mahmoud.benhassine@icloud.com)
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */
package com.idomine.myruleengine.notification;

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
