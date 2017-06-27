package com.idomine.rules;

import com.idomine.model.Entidade;
import com.idomine.notification.Notificacao;
import com.idomine.ruleengine.interfaces.InjectFact;

public class EntidadeRule  
{
    @InjectFact(name="entidade")
    private Entidade entidade;

    public Notificacao validarNome()
    {
        boolean regra = entidade.getNome() != null;
        return new Notificacao()
                .expressaoLogica(regra)
                .addMensagemInfo("Nome entidade checado")
                .addMensagemFalse("Informe nome para entidade.");
    }

    public Notificacao validarEmail()
    {
        boolean regra = entidade.getEmail() != null;
        return new Notificacao()
                .expressaoLogica(regra)
                .addMensagemInfo("Email entidade checado")
                .addMensagemFalse("Informe email para entidade.");
    }
 

}
