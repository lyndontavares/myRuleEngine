package com.idomine.rules;

import static com.idomine.helper.ExpressaoLogicaHelper.maiorOuIgualQue;

import java.math.BigDecimal;

import com.idomine.model.Entidade;
import com.idomine.model.Fatura;
import com.idomine.ruleengine.Rule;
import com.idomine.ruleengine.interfaces.IFato;

public class FaturaRule extends Rule
{
    @IFato(name="fatura")
    public Fatura fatura;

    @IFato(name="entidade")
    private Entidade entidade;

    @IFato(name="valorMinimo")
    private BigDecimal valor;
    
    public boolean validarValor()
    {
        boolean regra =maiorOuIgualQue(fatura.getValor(),valor);
        return regra;
    }
    
    public boolean validarEntidade()
    {
        boolean regra=fatura.getEntidade()!=null;
        return regra;
    }

    public boolean validarData()
    {
        boolean regra=fatura.getEmissao()!=null;
        return regra;
    }
 
}
