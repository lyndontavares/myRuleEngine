package com.idomine.rules;

import com.idomine.model.Mercadoria;
import com.idomine.ruleengine.interfaces.InjectFact;
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
