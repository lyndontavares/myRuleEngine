package com.idomine.helper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;


public final class ExpressaoLogicaHelper
{
    private ExpressaoLogicaHelper(){}
    
    // object

    public static boolean diferenteDeNulo(Object o)
    {
        return naoNulo(o);
    }

    public static boolean naoNulo(Object o)
    {
        return o != null;
    }

    public static boolean nulo(Object o)
    {
        return o == null;
    }

    // Long
    public static boolean iguais(long numeroIni, long numeroFim)
    {
        return numeroIni==numeroFim;
    }
    
    public static boolean iguais(Long numeroIni, Long numeroFim)
    {
        return numeroIni.equals(numeroFim);
    }

    public static boolean igualZero(Long numero)
    {
        return numero.doubleValue() == 0;
    }

    public static boolean igualAZero(long numero)
    {
        return numero == 0;
    }

    public static boolean maiorQueZero(Long numero)
    {
        return numero > 0;
    }

    public static boolean menorQueZero(Long numero)
    {
        return numero < 0;
    }

    public static boolean menorOuIgualZero(Long numero)
    {
        return numero <= 0;
    }

    // BigDecimal

    public static boolean diferentes( BigDecimal primeiroNumero, BigDecimal segundoNumero)
    {
        return !iguais(primeiroNumero,segundoNumero);
    }
    
    public static boolean iguais(BigDecimal primeiroNumero, BigDecimal segundoNumero)
    {
        return (naoNulo(primeiroNumero) && naoNulo(segundoNumero) && (primeiroNumero.compareTo(segundoNumero) == 0));
    }

    public static boolean iguais(BigDecimal primeiroNumero, Long segundoNumero)
    {
        return (naoNulo(primeiroNumero) && naoNulo(segundoNumero)
                && (primeiroNumero.compareTo(new BigDecimal(segundoNumero)) == 0));
    }

    public static boolean igualZero(BigDecimal numero)
    {
        return (numero != null) && (numero.doubleValue() == 0);
    }

    public static boolean maiorQueZero(BigDecimal numero)
    {
        return (numero != null) && (numero.doubleValue() > 0);
    }

    public static boolean maiorOuIgualZero(BigDecimal numero)
    {
        return (numero != null) && (numero.doubleValue() >= 0);
    }

    public static boolean maiorOuIgualZero(Long numero)
    {
        return (numero != null) && (numero.doubleValue() >= 0);
    }

    public static boolean menorQueZero(BigDecimal numero)
    {
        return (numero != null) && (numero.doubleValue() < 0);
    }

    public static boolean menorOuIgualZero(BigDecimal numero)
    {
        return (numero != null) && (numero.doubleValue() <= 0);
    }

    public static boolean menorOuIgualAZero(BigDecimal numero)
    {
        return (numero != null) && (numero.doubleValue() <= 0);
    }

    public static boolean menorOuIgualAZero(Long numero)
    {
        return (numero != null) && (numero.doubleValue() <= 0);
    }

    public static boolean igual(BigDecimal valor1, BigDecimal valor2)
    {
        return (valor1 != null) && (valor2 != null) && (valor1.compareTo(valor2) == 0);
    }

    public static boolean maiorQue(BigDecimal valor1, BigDecimal valor2)
    {
        return (valor1 != null) && (valor2 != null) && (valor1.compareTo(valor2) == 1);
    }

    public static boolean maiorOuIgualQue(BigDecimal valor1, BigDecimal valor2)
    {
        return (valor1 != null) && (valor2 != null) && (valor1.compareTo(valor2) >= 0);
    }

    public static boolean menorQue(BigDecimal valor1, BigDecimal valor2)
    {
        return (valor1 != null) && (valor2 != null) && (valor1.compareTo(valor2) == -1);
    }

    public static boolean menorOuIgualQue(BigDecimal valor1, BigDecimal valor2)
    {
        return (valor1 != null) && (valor2 != null) && (valor1.compareTo(valor2) <= 0);
    }

    public static boolean diferente(BigDecimal valor1, BigDecimal valor2)
    {
        return (valor1 != null) && (valor2 != null) && (valor1.compareTo(valor2) != 0);
    }

    // String

    public static boolean maiorQueVazio(String string)
    {
        return (string != null) &&  !"".equals(string.trim()); 
    }

    public static boolean igualVazio(String string)
    {
        return (string != null) && (string.trim().equals(""));
    }

    public static boolean vazioOuNulo(String string)
    {
        return (string == null) || (string.trim().equals(""));
    }
    
    
    
    public static boolean tamanhoStringEntre(String string, long minimo, long maximo )
    {
        String s = nulo( string ) ? "" :string.trim();
        return s.length()>=minimo && s.length()<=maximo;
    }

    // Date

    public static boolean maiorOuIgualQueHoje(Date data)
    {
        return (data != null) && (data.compareTo(new Date()) >= 0);
    }

    public static boolean maiorQueHoje(Date data)
    {
        return (data != null) && (data.compareTo(new Date()) > 0);
    }

    public static boolean igualAHoje(Date data)
    {
        return (data != null) && (data.compareTo(new Date()) == 0);
    }

    public static BigDecimal converterPercentualAValor(BigDecimal percentual, BigDecimal valorTotal){
        BigDecimal retorno = BigDecimal.ZERO;
        if(naoNulo(percentual) && maiorQueZero(percentual) && naoNulo(valorTotal) && maiorQueZero(valorTotal)){
            retorno = valorTotal.multiply(percentual.divide(new BigDecimal(100), 2, RoundingMode.HALF_UP));
        }
        return retorno;
    }
    
    public static BigDecimal converterValorAPercentual(BigDecimal valor, BigDecimal valorTotal){
        BigDecimal retorno = BigDecimal.ZERO;
        if(naoNulo(valor) && maiorQueZero(valor) && naoNulo(valorTotal) && maiorQueZero(valorTotal)){
            retorno = valor.multiply(new BigDecimal(100)).divide(valorTotal, 2, RoundingMode.HALF_UP);
        }
        return retorno;
    }
}
