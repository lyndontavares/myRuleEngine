package com.idomine.model;

import java.math.BigDecimal;
import java.util.Date;

public class Fatura
{

    private Long codigo;
    
    private Date emissao;
    
    private BigDecimal valor;
    
    private Entidade entidade;

    public Long getCodigo()
    {
        return codigo;
    }

    public void setCodigo(Long codigo)
    {
        this.codigo = codigo;
    }

    public Date getEmissao()
    {
        return emissao;
    }

    public void setEmissao(Date emissao)
    {
        this.emissao = emissao;
    }

    public BigDecimal getValor()
    {
        return valor;
    }

    public void setValor(BigDecimal valor)
    {
        this.valor = valor;
    }

    public Entidade getEntidade()
    {
        return entidade;
    }

    public void setEntidade(Entidade entidade)
    {
        this.entidade = entidade;
    }

    public static Fatura getFake()
    {
        Fatura fatura = new Fatura();
        fatura.setCodigo(1000L);
        fatura.setValor(new BigDecimal(1000));
        fatura.setEmissao(new Date());
        return fatura;
    }
    
    
    
}
