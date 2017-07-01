package com.idomine.business;

import com.idomine.model.Product;
import com.idomine.ruleengine.annotations.InjectFact;
import com.idomine.ruleengine.notification.Notification;

public class ProductRule
{

    @InjectFact(name="mercadoria")
    private Product mercadoria;
    
    public Notification validarNomeMercadoria()
    {
        boolean regra=mercadoria.getNome()!=null;
        return new Notification()
                .expressaoLogica(regra)
                .addMensagemInfo("mercadoria checada")
                .addMensagemFalse("Informe nome mercadoria");
    }
    
    
}
