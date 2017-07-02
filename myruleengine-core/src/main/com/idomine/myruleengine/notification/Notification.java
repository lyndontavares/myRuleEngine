/**
 * The MIT License
 *
 *  Copyright (c) 2017, Lyndon Tavares (integraldominio@gmail.com)
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
    private List<Message> messages;
    private long priority;
    private String notificationContext;

    public boolean isResultado()
    {
        return resultado;
    }

    public List<Message> getMessages()
    {
        return messages;
    }

    public void setMessages(List<Message> messages)
    {
        this.messages = messages;
    }

    public void setResultado(boolean resultado)
    {
        this.resultado = resultado;
    }

    public long getPriority()
    {
        return priority;
    }

    public void setPriority(long priority)
    {
        this.priority = priority;
    }

    public Notification(String mensagem, MessageType tipo)
    {
        addMessage(new Message(mensagem, tipo));
    }

    public Notification()
    {
        messages = new ArrayList<>();
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

    public Notification addMessage(Message mensagem)
    {
        messages.add(mensagem);
        return this;
    }

    public Notification addMessageContext(String mensagem)
    {
        messages.add(new Message(mensagem, MessageType.CONTEXT));
        return this;
    }

    public Notification addMessageTrue(String mensagem)
    {
        messages.add(new Message(mensagem, MessageType.EXPRESSAO_TRUE));
        return this;
    }

    public Notification addMessageFalse(String mensagem)
    {
        messages.add(new Message(mensagem, MessageType.EXPRESSAO_FALSE));
        return this;
    }

    public Notification addMessageInfo(String mensagem)
    {
        messages.add(new Message(mensagem, MessageType.INFO));
        return this;
    }

    public Notification addMessageWarn(String mensagem)
    {
        messages.add(new Message(mensagem, MessageType.ADVERTENCIA));
        return this;
    }

    public Notification addMessageError(String mensagem)
    {
        messages.add(new Message(mensagem, MessageType.ERROR));
        return this;
    }

    public Notification condition(boolean resultado)
    {
        this.resultado = resultado;
        return this;
    }

}
