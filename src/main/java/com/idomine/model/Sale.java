package com.idomine.model;

import java.math.BigDecimal;
import java.util.Date;

public class Sale
{

    private Long codigo;
    
    private Date emissao;
    
    private BigDecimal valor;
    
    private Person entidade;

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

    public Person getEntidade()
    {
        return entidade;
    }

    public void setEntidade(Person entidade)
    {
        this.entidade = entidade;
    }

    public static Sale getFake()
    {
        Sale fatura = new Sale();
        fatura.setCodigo(1000L);
        fatura.setValor(new BigDecimal(1000));
        fatura.setEmissao(new Date());
        return fatura;
    }
    
    
    
}
