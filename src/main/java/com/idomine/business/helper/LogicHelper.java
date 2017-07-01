/**
 * The MIT License
 *
 *  Copyright (c) 2017, Mahmoud Ben Hassine (mahmoud.benhassine@icloud.com)
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */
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
