package com.idomine.business;

import com.idomine.model.Person;
import com.idomine.myruleengine.annotations.InjectFact;
import com.idomine.myruleengine.notification.Notification;

public class PersonRule  
{
    @InjectFact(name="entidade")
    private Person entidade;

    public Notification validarNome()
    {
        boolean regra = entidade.getNome() != null;
        return new Notification()
                .expressaoLogica(regra)
                .addMensagemInfo("6 Nome entidade checado")
                .addMensagemFalse("6 Informe nome para entidade.");
    }

    public Notification validarEmail()
    {
        boolean regra = entidade.getEmail() != null;
        return new Notification()
                .expressaoLogica(regra)
                .addMensagemInfo("7 Email entidade checado")
                .addMensagemFalse("7 Informe email para entidade.");
    }
 

}
