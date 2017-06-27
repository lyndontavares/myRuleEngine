package com.idomine.model.rules;

import com.idomine.model.Mercadoria;
import com.idomine.ruleengine.annotations.InjectFact;
import com.idomine.ruleengine.notification.Notificacao;

public class MercadoriaRule
{

    @InjectFact(name="mercadoria")
    private Mercadoria mercadoria;
    
    public Notificacao validarNomeMercadoria()
    {
        boolean regra=mercadoria.getNome()!=null;
        return new Notificacao()
                .expressaoLogica(regra)
                .addMensagemInfo("mercadoria checada")
                .addMensagemFalse("Informe nome mercadoria");
    }
    
    
}
