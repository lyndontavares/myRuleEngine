package com.idomine.business;

import com.idomine.model.Product;
import com.idomine.myruleengine.annotations.InjectFact;
import com.idomine.myruleengine.notification.Notification;

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
