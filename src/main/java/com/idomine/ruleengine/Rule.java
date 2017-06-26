package com.idomine.ruleengine;

import java.util.List;
import java.util.stream.Collectors;

import com.idomine.notification.Notificacao;

public class Rule  
{
    private boolean lastResult;
    private List<Fato> fatos;
    private List<Notificacao> notificacoes;
    
    public Rule()
    {
        lastResult=false;
    }
    
    public void prepareFacts()
    {
    }

    public List<Fato> getFatos()
    {
        return fatos;
    }

    public void setFatos(List<Fato> fatos)
    {
        this.fatos = fatos;
    }

    public Object fato(final String nome)
    {
        return ((Fato) fatos.stream().filter(f->f.getNome().equals(nome)).collect(Collectors.toList()).get(0)).getValor();
    }

    public boolean isLastResult()
    {
        return lastResult;
    }

    public void setLastResult(boolean lastResult)
    {
        this.lastResult = lastResult;
    }

    @Override
    public String toString()
    {
        return "DolphinRule [lastResult=" + lastResult + ", fatos=" + fatos + "]";
    }

    public List<Notificacao> getNotificacoes()
    {
        return notificacoes;
    }

   public void addNotificacao( Notificacao notificacao)
   {
       notificacoes.add(notificacao);
   }
    
}
