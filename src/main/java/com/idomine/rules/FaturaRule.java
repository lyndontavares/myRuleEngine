package com.idomine.rules;

import static com.idomine.helper.ExpressaoLogicaHelper.maiorOuIgualQue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.idomine.model.Entidade;
import com.idomine.model.Fatura;
import com.idomine.notification.Notificacao;
import com.idomine.ruleengine.interfaces.IFato;

public class FaturaRule
{
    @IFato(name = "fatura")
    public Fatura fatura;

    @IFato(name = "entidade")
    private Entidade entidade;

    @IFato(name = "valorMinimo")
    private BigDecimal valor;

    public Notificacao validarValor()
    {
        boolean regra = maiorOuIgualQue(fatura.getValor(), valor);
        return new Notificacao()
                .expressaoLogica(regra)
                .addMensagemInfo("Checando valor fatura")
                .addMensagemInfo("Valor fatura dentro do limite de credito!")
                .addMensagemTrue("Valor fatura passou na checagem")
                .addMensagemFalse("valor fatura deve ser maior ou igual a " + valor);

    }

    public Notificacao validarEntidade()
    {
        boolean regra = fatura.getEntidade() != null;
        return new Notificacao()
                .expressaoLogica(regra)
                .addMensagemInfo("Entidade fatura checado")
                .addMensagemFalse("Informe uma entidade para fatura.");
    }

    public Notificacao validarData()
    {
        boolean regra = fatura.getEmissao() != null;

        return new Notificacao()
                .expressaoLogica(regra)
                .addMensagemFalse("Informe data emissao da fatura");
    }

    public List<Notificacao> validarListaRegras()
    {
        List<Notificacao> notificacoes = new ArrayList<>();
        Notificacao regraUm = regraUm();
        notificacoes.add(regraUm);
        if (regraUm.isResultado())
            notificacoes.add(regraDois());
        return notificacoes;
    }

    private Notificacao regraUm()
    {
        boolean regra = false;
        return new Notificacao()
                .expressaoLogica(regra)
                .addMensagemInfo("regra 1 checada")
                .addMensagemFalse("Regra 1 da fatura falhou");
    }

    private Notificacao regraDois()
    {
        boolean regra = true;
        return new Notificacao()
                .expressaoLogica(regra)
                .addMensagemInfo("Regra 2 checada")
                .addMensagemFalse("Regra 2 da fatura falhou");
    }

}
