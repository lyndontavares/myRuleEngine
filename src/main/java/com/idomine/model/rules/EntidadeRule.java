package com.idomine.model.rules;

import com.idomine.model.Entidade;
import com.idomine.ruleengine.annotations.InjectFact;
import com.idomine.ruleengine.notification.Notificacao;

public class EntidadeRule  
{
    @InjectFact(name="entidade")
    private Entidade entidade;

    public Notificacao validarNome()
    {
        boolean regra = entidade.getNome() != null;
        return new Notificacao()
                .expressaoLogica(regra)
                .addMensagemInfo("6 Nome entidade checado")
                .addMensagemFalse("6 Informe nome para entidade.");
    }

    public Notificacao validarEmail()
    {
        boolean regra = entidade.getEmail() != null;
        return new Notificacao()
                .expressaoLogica(regra)
                .addMensagemInfo("7 Email entidade checado")
                .addMensagemFalse("7 Informe email para entidade.");
    }
 

}
