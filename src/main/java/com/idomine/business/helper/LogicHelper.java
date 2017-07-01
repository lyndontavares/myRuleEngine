package com.idomine.business.helper;

import java.math.BigDecimal;


public final class LogicHelper
{
    private LogicHelper(){}
    
    // object

    public static boolean notNull(Object o)
    {
        return o != null;
    }

    public static boolean isNull(Object o)
    {
        return o == null;
    }

    // BigDecimal

    public static boolean notEquals( BigDecimal primeiroNumero, BigDecimal segundoNumero)
    {
        return !isEquals(primeiroNumero,segundoNumero);
    }
    
    public static boolean isEquals(BigDecimal primeiroNumero, BigDecimal segundoNumero)
    {
        return (notNull(primeiroNumero) && notNull(segundoNumero) && (primeiroNumero.compareTo(segundoNumero) == 0));
    }

    public static boolean isEquals(BigDecimal primeiroNumero, Long segundoNumero)
    {
        return (notNull(primeiroNumero) && notNull(segundoNumero)
                && (primeiroNumero.compareTo(new BigDecimal(segundoNumero)) == 0));
    }

    public static boolean isZero(BigDecimal numero)
    {
        return (numero != null) && (numero.doubleValue() == 0);
    }

    public static boolean moreThenZero(BigDecimal numero)
    {
        return (numero != null) && (numero.doubleValue() > 0);
    }

    public static boolean moreThenOrEqualsZero(BigDecimal numero)
    {
        return (numero != null) && (numero.doubleValue() >= 0);
    }

    public static boolean moreThenOrEquals(BigDecimal valor1, BigDecimal valor2)
    {
        return (valor1 != null) && (valor2 != null) && (valor1.compareTo(valor2) >= 0);
    }

}
