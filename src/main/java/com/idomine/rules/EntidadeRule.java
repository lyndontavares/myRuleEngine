package com.idomine.rules;

import com.idomine.model.Entidade;
import com.idomine.ruleengine.Rule;
import com.idomine.ruleengine.interfaces.IFato;

public class EntidadeRule extends Rule
{
    @IFato(name="entidade")
    private Entidade entidade;

    public boolean validarNome()
    {
        boolean regra = entidade.getNome() != null;
       // addNotificacao(new Notificacao("Teste 1", MensagemTipo.INFO));
        return regra;
    }

    public boolean validarEmail()
    {
        boolean regra = entidade.getEmail() != null;
      //  addNotificacao(new Notificacao("Teste 2", MensagemTipo.INFO));
        return regra;
    }
 

}
