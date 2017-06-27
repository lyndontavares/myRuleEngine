package com.idomine.rules;

import com.idomine.model.Mercadoria;
import com.idomine.notification.Notificacao;
import com.idomine.ruleengine.interfaces.InjectFact;

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
